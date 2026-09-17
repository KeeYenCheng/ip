package max.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import max.task.Deadline;
import max.task.Task;
import max.task.Todo;

public class StorageTest {

    @Test
    public void saveAndLoadDescriptionWithPipe_preservesTask() throws Exception {
        Path file = Files.createTempFile("max-storage-test", ".txt");
        Storage storage = new Storage(file.toString());
        List<Task> tasks = List.of(
                new Todo("plan | implement"),
                new Deadline("review | submit", LocalDate.of(2026, 9, 17)));

        storage.save(tasks);

        List<Task> loaded = storage.load();
        assertEquals("plan | implement", loaded.get(0).getDescription());
        assertEquals("review | submit", loaded.get(1).getDescription());
    }
}
