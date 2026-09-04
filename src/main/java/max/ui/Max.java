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
        String command = Parser.getCommandWord(response);

        switch (command) {
            case "BYE":
                ui.showGoodBye();
                return false;
            case "list":
                ui.showAllTasks(tasks.getAllTask());
                return true;
            case "todo": {
                String args = Parser.getArguments(response);
                Todo newTodo = new Todo(args);
                tasks.add(newTodo);
                ui.showTaskAdded(newTodo, tasks.size());
                return true;
            }
            case "deadline": { 
                String args = Parser.getArguments(response);
                String[] ddl = Parser.parseDeadline(args);
                LocalDate date = Parser.parseDate(ddl[1]);
                Deadline deadline = new Deadline(ddl[0], date);
                tasks.add(deadline);
                ui.showTaskAdded(deadline, tasks.size());
                return true;
            }
            case "event": {
                String args = Parser.getArguments(response);
                String[] evt = Parser.parseEvent(args);
                LocalDate start = Parser.parseDate(evt[1]);
                LocalDate end = Parser.parseDate(evt[2]);
                Event event = new Event(evt[0], start, end);
                tasks.add(event);
                ui.showTaskAdded(event, tasks.size());
                return true;
            }
            case "mark": {
                String args = Parser.getArguments(response);
                int index = Parser.parseIndex(args);
                Task task = tasks.setDone(index);
                ui.showTaskMarkedDone(task);
                return true;
            }
            case "unmark": {
                String args = Parser.getArguments(response);
                int index = Parser.parseIndex(args);
                Task task = tasks.setNotDone(index);
                ui.showTaskMarkedNotDone(task);
                return true;
            }
            case "delete": {
                String args = Parser.getArguments(response);
                int index = Parser.parseIndex(args);
                Task removed = tasks.delete(index);
                ui.showTaskDeleted(removed, tasks.size());;
                return true;
            }
            case "on": {
                String args = Parser.getArguments(response);
                LocalDate date = Parser.parseDate(args);
                ui.showTasksOn(tasks.getTasksOn(date)); 
                return true;
            }
            case "find": {
                String args = Parser.getArguments(response);
                List<Task> matches = tasks.find(args);
                ui.showMatchingTasks(matches);
                return true;
            }
            default:
                throw new UnknownCommandException();
        }
   }
    
    public static void main(String[] args) {
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
