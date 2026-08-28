package max.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import max.task.Task;
import max.data.TaskType;
import max.data.Status;
import max.task.Deadline;
import max.task.Event;
import max.task.Todo;
import max.data.TaskList;

public class Storage {
  private String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public void save(List<Task> tasks) throws IOException {
    StringBuilder res = new StringBuilder();
    for (Task task: tasks) {
      res.append(task.getItemString()).append("\n");
    }
    try (FileWriter fw = new FileWriter(filePath)) {
      fw.write(res.toString());
    }
  }

  public ArrayList<Task> load() throws FileNotFoundException {
    ArrayList<Task> tasks = new ArrayList<>();
    File f = new File(filePath);
    if (!f.exists()) {
      return tasks;
    }

    Scanner s = new Scanner(f);
    while (s.hasNext()) {
      String line = s.nextLine();
      String[] data = line.split(Pattern.quote(" | "));
      TaskType type = TaskType.fromSymbol(data[0]);
      
      switch (type) {
        case TODO:
          tasks.add(new Todo(data[2], Status.fromSymbol(data[1])));
          break;
        case DEADLINE:
          LocalDate date = LocalDate.parse(data[3]);
          tasks.add(new Deadline(data[2], Status.fromSymbol(data[1]), date));
          break;
        case EVENT:
          LocalDate start = LocalDate.parse(data[3]);
          LocalDate end = LocalDate.parse(data[4]);
          tasks.add(new Event(data[2], Status.fromSymbol(data[1]), start, end));
          break;
        default:
          break;
      }
    }
    s.close();
    return tasks;
  }
}
