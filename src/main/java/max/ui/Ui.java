package max.ui;
import max.task.Task;
import java.util.List;


public class Ui {
    public static String tabSpace = "        ";
    private static String banner = "    _____ _____  ___    ___\n" 
                                 + " /       \\\\__  \\ \\  \\/  /\n" 
                                 + "|  Y Y  \\/ __ \\_>      < \n"
                                 + "|__|_|  (____  /__/\\_ \\\n" 
                                 + "            \\/         \\/          \\/\n";
    private static String greetings = tabSpace + "Sup! I'm Max.\n"
                                         + tabSpace + "What do you need?";
    private static String bars = tabSpace + "________________________________________";
    private static String bye = tabSpace + "See ya later!\n";

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
        System.out.println(bars);
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
        System.out.println(msg); 
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
        System.out.println(bars);
 
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
        System.out.println(tabSpace + "Okay! I've deleted this task from the list");
        System.out.println(tabSpace + task);
        System.out.println(tabSpace + "Now you have " + remainingCount + " in the list");
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
        System.out.println(tabSpace + "Nice! I've marked this task as done");
        System.out.println(tabSpace + task);
    }

    /**
     * Display the task that was marked not done.
     *
     * @param task Task that was to be marked not done.
     */
    public void showTaskMarkedNotDone(Task task) {
        System.out.println(tabSpace + "Ok! I've marked this task as not done");
        System.out.println(tabSpace + task);
    }

    /**
     * Display all the task in a list.
     *
     * @param tasks list of task to be displayed 
     *
     */
    public void showAllTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(tabSpace + "there is currently no task");
            return;
                }
                 for (Task task : tasks) {
            System.out.println(tabSpace + task);
            }

     }
        
         /**
     * Show task on specified date.
     *
     * @param  tasks list of task to be displayed.
     *
     */
    public void showTasksOn(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(tabSpace + task);
            }
        }
    /**
     * Show task added.
     *
     * @param task task to be added.
     * @param totalTasks number of task in total 
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(tabSpace + "Task added:\n" + tabSpace + task);
        System.out.println(tabSpace + "Now you have " + totalTasks + " in the list");
    }

    /**
     * Display good bye message.
     *
     */
    public void showGoodBye() {
        System.out.println(bye);
    }


    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(tabSpace + "No matching tasks found in your list."); 
            return;
        }
        System.out.println(tabSpace + "Here are the matching tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tabSpace + (i+1) + "." + tasks.get(i)); 
        }
    }
} 
