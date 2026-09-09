package max.ui; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

import max.maxexception.MaxException;
import max.maxexception.UnknownCommandException;

import max.storage.Storage;
import max.command.Parser;
import max.task.Deadline;
import max.task.Task;
import max.task.Event;
import max.task.Todo;

import max.data.TaskList;


public class Max {

    private static Ui ui = new Ui();
    private static Storage storage = new Storage("src/data/Max.txt");
    private static TaskList tasks;  

    /**
     * Creates a command processor and loads the saved task list.
     */
    public Max() {
        if (tasks == null) {
            try {
                tasks = new TaskList(storage.load());
            } catch (FileNotFoundException e) {
                tasks = new TaskList();
            }
        }
        assert tasks != null : "Max must have a task list after initialization";
    }

    /**
     * Processes a command for the graphical interface.
     *
     * @param command command entered by the user
     * @return a short status message for the conversation
     */
    public String getResponse(String command) {
        try {
            processCommand(command);
            storage.save(tasks.getAllTask());
            return ui.getLastResponse();
        } catch (MaxException | IOException e) {
            ui.showError(e.getMessage());
            return ui.getLastResponse();
        }
    }



  /**
   * Echo action base on response.
   *
   * @param response action to be done by the bot.
   * @return true if further action can be done else false.
   *
   * @example
   * ```
   * Write me later
   * ```
   */
    public static boolean processCommand(String response) throws MaxException {
        if (tasks == null) {
            new Max();
        }
        assert tasks != null : "Commands require an initialized task list";
        String command = Parser.getCommandWord(response);

        switch (command) {
            case "BYE":
                ui.showGoodBye();
                return false;
            case "list":
                ui.showAllTasks(tasks.getAllTask());
                return true;
            case "todo":
                return handleTodo(response);
            case "deadline":
                return handleDeadline(response);
            case "event":
                return handleEvent(response);
            case "mark":
                return handleMark(response);
            case "unmark":
                return handleUnmark(response);
            case "delete":
                return handleDelete(response);
            case "on":
                return handleTasksOn(response);
            case "find":
                return handleFind(response);
            default:
                throw new UnknownCommandException();
        }
   }

    private static boolean handleTodo(String response) throws MaxException {
        String args = Parser.getArguments(response);
        Todo newTodo = new Todo(args);
        tasks.add(newTodo);
        ui.showTaskAdded(newTodo, tasks.size());
        return true;
    }

    private static boolean handleDeadline(String response) throws MaxException {
        String args = Parser.getArguments(response);
        String[] ddl = Parser.parseDeadline(args);
        LocalDate date = Parser.parseDate(ddl[1]);
        Deadline deadline = new Deadline(ddl[0], date);
        tasks.add(deadline);
        ui.showTaskAdded(deadline, tasks.size());
        return true;
    }

    private static boolean handleEvent(String response) throws MaxException {
        String args = Parser.getArguments(response);
        String[] evt = Parser.parseEvent(args);
        LocalDate start = Parser.parseDate(evt[1]);
        LocalDate end = Parser.parseDate(evt[2]);
        Event event = new Event(evt[0], start, end);
        tasks.add(event);
        ui.showTaskAdded(event, tasks.size());
        return true;
    }

    private static boolean handleMark(String response) throws MaxException {
        int index = Parser.parseIndex(Parser.getArguments(response));
        Task task = tasks.setDone(index);
        ui.showTaskMarkedDone(task);
        return true;
    }

    private static boolean handleUnmark(String response) throws MaxException {
        int index = Parser.parseIndex(Parser.getArguments(response));
        Task task = tasks.setNotDone(index);
        ui.showTaskMarkedNotDone(task);
        return true;
    }

    private static boolean handleDelete(String response) throws MaxException {
        int index = Parser.parseIndex(Parser.getArguments(response));
        Task removed = tasks.delete(index);
        ui.showTaskDeleted(removed, tasks.size());
        return true;
    }

    private static boolean handleTasksOn(String response) throws MaxException {
        LocalDate date = Parser.parseDate(Parser.getArguments(response));
        ui.showTasksOn(tasks.getTasksOn(date));
        return true;
    }

    private static boolean handleFind(String response) throws MaxException {
        List<Task> matches = tasks.find(Parser.getArguments(response));
        ui.showMatchingTasks(matches);
        return true;
    }
    
    public static void main(String... args) {
        ui.showBanner();

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }

        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        while (repeat) {
            String response = scanner.nextLine();
            ui.showLine();
            
            try {
                repeat = processCommand(response);
            } catch (MaxException e) {
                ui.showError(e.getMessage());
                ui.showLine();
                continue;
            }

            ui.showLine();

            try {
                storage.save(tasks.getAllTask());
            } catch (IOException e) {
                ui.showError("Could not save: " + e.getMessage());
            }
        }
        scanner.close();
    }


 }
