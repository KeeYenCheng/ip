package max.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import max.data.Status;
import max.data.TaskType;
import max.maxexception.InvalidDateRangeException;
import max.maxexception.MaxException;

public class TaskTest {

    @Test
    public void taskState_mutation_updatesStatus() {
        Todo todo = new Todo("read chapter");
        assertEquals("read chapter", todo.getDescription());
        assertEquals("[ ]", todo.getItemString().split(" \\| ", 3)[1]);

        todo.markAsDone();
        assertTrue(todo.getItemString().contains(" | [X] | read chapter"));

        todo.notDone();
        assertTrue(todo.getItemString().contains(" | [ ] | read chapter"));
    }

    @Test
    public void todo_toString_usesTaskTypeAndStatus() {
        Todo todo = new Todo("buy groceries", Status.DONE);
        assertEquals("[T] [X] buy groceries", todo.toString());
    }

    @Test
    public void deadline_fieldsAndStrings_areFormatted() {
        LocalDate dueDate = LocalDate.of(2026, 9, 17);
        Deadline deadline = new Deadline("submit report", dueDate);

        assertEquals(dueDate, deadline.getDate());
        assertTrue(deadline.isOn(dueDate));
        assertFalse(deadline.isOn(dueDate.plusDays(1)));
        assertEquals("[D] | [ ] | submit report | 2026-09-17", deadline.getItemString());
        assertEquals("[D] [ ] submit report(by: Sep 17 2026)", deadline.toString());
    }

    @Test
    public void event_validRangeAndDates_areTracked() throws MaxException {
        LocalDate start = LocalDate.of(2026, 9, 17);
        LocalDate end = LocalDate.of(2026, 9, 19);
        Event event = new Event("camp", start, end);

        assertTrue(event.isOn(start));
        assertTrue(event.isOn(end));
        assertTrue(event.isOn(start.plusDays(1)));
        assertFalse(event.isOn(start.minusDays(1)));
        assertEquals("[E] | [ ] | camp | 2026-09-17 | 2026-09-19", event.getItemString());
        assertEquals("[E] [ ] camp(from: Sep 17 2026 to: Sep 19 2026)", event.toString());
    }

    @Test
    public void event_invalidRange_throwsException() {
        LocalDate start = LocalDate.of(2026, 9, 20);
        LocalDate end = LocalDate.of(2026, 9, 19);

        assertThrows(InvalidDateRangeException.class, () -> new Event("camp", start, end));
        assertThrows(InvalidDateRangeException.class, () -> new Event("camp", Status.NOT_DONE, start, end));
        assertThrows(InvalidDateRangeException.class, () -> new Event("camp", null, end));
    }

    @Test
    public void taskTypeAndStatus_valueParsingWorks() {
        assertEquals(TaskType.TODO, TaskType.fromSymbol("[T]"));
        assertEquals(TaskType.DEADLINE, TaskType.fromSymbol("[D]"));
        assertEquals(TaskType.EVENT, TaskType.fromSymbol("[E]"));
        assertThrows(IllegalArgumentException.class, () -> TaskType.fromSymbol("[X]"));

        assertEquals(Status.DONE, Status.fromSymbol("[X]"));
        assertEquals(Status.NOT_DONE, Status.fromSymbol("[ ]"));
        assertThrows(IllegalArgumentException.class, () -> Status.fromSymbol("[T]"));
    }
}
