package max.ui;
import max.task.Task; import java.util.List;
import java.util.stream.Collectors;

public class Ui {
    private String lastResponse = "";
    private static String banner = "  _____  _____ ___  ___\n" 
                                 + " /     \\\\__  \\ \\  \\/  /\n" 
                                 + "|  Y Y  \\/ __ \\_>    < \n"
                                 + "|__|_|  (____  /__/\\_ \\\n" 
                                 + "      \\/     \\/      \\/\n";
    private static String greetings = "Sup! I'm Max.\n"
                                         + "What do you need?";
    private static String bar = "________________________________________";
    private static String bye =  "See ya later!\n";

    /**
     * Print line separator.
     *
     * Print line to separate commands of user and the bot 
     * 
     * @example
     *
     * ```
     * ui.showline();
     * 
     * prints 
     * ________________________________________
     * ```
     */
    public void showLine() {
        System.out.println(bar);
    }

    /**
     * Display error message.
     *
     * @param msg String : error message to be displayed.
     *
     * @example
     * ```
     * ui.showError("Invalid date");
     * prints: 
     *  Invalid date 
     * ```
     */

    public void showError(String msg) {
        showResponse(msg);
    }

    /**
     * Display the title of the bot.
     *
     * @example
     * ```
     * ui.showBanner();
     * ```
     */
    public void showBanner() {
        System.out.println(banner);
        System.out.println(greetings);
        System.out.println(bar);
 
    }

    /**
     * Returns the greeting shown when Max starts.
     *
     * @return Max's greeting
     */
    public String getGreetings() {
        return greetings;
    }

    /**
     * Display deleted task message.
     *
     * @param task Task : the task deleted.
     * @param remainingCount int : number of task left after deletion 
     * @return Type and description of the returned object.
     *
     * @example
     * ```
    * 
     * ```
     */
    public void showTaskDeleted(Task task, int remainingCount) {
        showResponse("Okay! I've deleted this task from the list\n"
                + task + "\n"
                + "Now you have " + remainingCount + " in the list");
    }

    /**
     * Display the details of the task marked done.
     *
     * @param task task marked as done.
     *
     * @example
     * ```
     * Task task = new Todo("read book");
     * Ui().showTaskMarkedDone(task);
     *
     * Display:
     * [T][X] read book 
     * ```
     */
    public void showTaskMarkedDone(Task task) {
        showResponse("Nice! I've marked this task as done\n" + task);
    }

    /**
     * Display the task that was marked not done.
     *
     * @param task Task that was to be marked not done.
     */
    public void showTaskMarkedNotDone(Task task) {
        showResponse("Ok! I've marked this task as not done\n" + task);
    }

    /**
     * Display all the task in a list.
     *
     * @param tasks list of task to be displayed 
     *
     */
    public void showAllTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showResponse( "there is currently no task");
            return;
        }
        
        String response = "These are the current task\n" 
                            + tasks.stream()
                                .map(Task::toString)
                                .collect(Collectors.joining("\n", "", "\n"));

        showResponse(response.stripTrailing());

    }

    /**
     * Displays the task list after sorting deadline tasks chronologically.
     *
     * @param tasks tasks to be displayed
     */
    public void showSortedTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showResponse("there is currently no task");
            return;
        }

        String response = "Tasks sorted by deadline:\n"
                + tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        showResponse(response);
    }

    /**
     * Show task on specified date.
     *
     * @param  tasks list of task to be displayed.
     *
     */
    public void showTasksOn(List<Task> tasks) {
        String response = tasks.stream().map(Task::toString).collect((Collectors.joining("\n", "", "\n")));
        showResponse(response.stripTrailing());
    }
    /**
     * Show task added.
     *
     * @param task task to be added.
     * @param totalTasks number of task in total 
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showResponse("Task added:\n" + task + "\n"
                +  "Now you have " + totalTasks + " in the list");
    }

    /**
     * Display good BYE message.
     *
     */
    public void showGoodBye() {
        showResponse(bye);
    }


    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showResponse("No matching tasks found in your list.");
            return;
        }
        String response = "Here are the matching tasks in your list \n" +
                            tasks.stream().map(Task::toString).collect(Collectors.joining("\n", "", "\n"));
        showResponse(response.toString());
    }

    /**
     * Returns the most recent response displayed by this UI.
     *
     * @return the most recent displayed response
     */
    public String getLastResponse() {
        return lastResponse;
    }

    private void showResponse(String response) {
        lastResponse = response;
        System.out.println(response);
    }
} 
