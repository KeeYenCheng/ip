package max.data;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import max.maxexception.InvalidTaskIDException;
import max.task.Task;  

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int i) throws InvalidTaskIDException {
        if (i <= 0 || i > tasks.size()) {
            throw new InvalidTaskIDException();
        }
        return tasks.remove(i - 1);
    }

    public Task setDone(int i) throws InvalidTaskIDException {
        if (i <= 0 || i > tasks.size()) {
            throw new InvalidTaskIDException();
        }
        Task t = tasks.get(i - 1);
        t.markAsDone();
        return t;
    }

    public Task setNotDone(int i) throws InvalidTaskIDException {
        if (i <= 0 || i > tasks.size()) {
            throw new InvalidTaskIDException();
        }
        Task t = tasks.get(i - 1);
        t.notDone();
        return t;
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTask() {
        return tasks;
    }

    public List<Task> getTasksOn(LocalDate date) {
        return tasks.stream().filter(task -> task.isOn(date))
                                                    .collect(Collectors.toList());
    }
   
    public List<Task> find(String keyword) {
        return tasks.stream()
                    .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
    }
}
