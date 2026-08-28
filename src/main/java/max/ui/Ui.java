package max.ui;
import java.time.format.DateTimeFormatter;
import max.task.Task;
import java.util.List;

public class Ui {
    public static String tabSpace = "    ";
    private static String banner = "  _____ _____  ___  ___\n" 
                 + " /     \\\\__  \\ \\  \\/  /\n" 
                 + "|  Y Y  \\/ __ \\_>    < \n"
                 + "|__|_|  (____  /__/\\_ \\\n" 
                 + "      \\/     \\/      \\/\n";

    private static String greetings = tabSpace + "Sup! I'm Max.\n"
                                         + tabSpace + "What do you need?";
    private static String bars = tabSpace + "________________________________________";
    private static String bye = tabSpace + "See ya later!\n";
    public void showLine() {
        System.out.println(bars);
    }

    public void showError(String msg) {
        System.out.println(msg); 
    }
    public void showBanner() {
        System.out.println(banner);
        System.out.println(greetings);
        System.out.println(bars);
 
    }
    
    public void showTaskDeleted(Task task, int remainingCount) {
        System.out.println(tabSpace + "Okay! I've deleted this task from the list");
        System.out.println(tabSpace + task);
        System.out.println(tabSpace + "Now you have " + remainingCount + " in the list");
    }

    public void showTaskMarkedDone(Task task) {
        System.out.println(tabSpace + "Nice! I've marked this task as done");
        System.out.println(tabSpace + task);
    }

    public void showTaskMarkedNotDone(Task task) {
        System.out.println(tabSpace + "Ok! I've marked this task as not done");
        System.out.println(tabSpace + task);
    }

    public void showAllTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(tabSpace + "there is currently no task");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tabSpace + (i + 1) + "." + tasks.get(i));
        }
    }

    public void showTasksOn(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(tabSpace + task);
        }
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(tabSpace + "Task added:\n" + tabSpace + task);
        System.out.println(tabSpace + "Now you have " + totalTasks + " in the list");
    }

    public void showGoodBye() {
        System.out.println(bye);
    }

} 
