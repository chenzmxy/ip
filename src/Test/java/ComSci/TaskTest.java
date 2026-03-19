package ComSci;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ComSci.task.Task;

class TaskTest {

    @Test
    void testMarkDone() {
        Task task = new Task("Test task");
        assertFalse(task.getDescription().isEmpty());
        task.markDone();
        assertTrue(task.toStorageString().startsWith("T | 1"));
    }

    @Test
    void testMarkDoneTwice() {
        Task task = new Task("Test task");
        task.markDone();
        task.markDone();
        assertTrue(task.toStorageString().startsWith("T | 1"));
    }

    @Test
    void testUnmark() {
        Task task = new Task("Test task");
        task.markDone();
        task.unmark();
        assertTrue(task.toStorageString().startsWith("T | 0"));
    }

    @Test
    void testSetDescription() {
        Task task = new Task("Original description");
        task.setDescription("Updated description");
        assertEquals("Updated description", task.getDescription());
    }

    @Test
    void testToStorageString() {
        Task task = new Task("My task");
        assertEquals("T | 0 | My task", task.toStorageString());
    }

    @Test
    void testToStorageStringDone() {
        Task task = new Task("My task");
        task.markDone();
        assertEquals("T | 1 | My task", task.toStorageString());
    }

    @Test
    void testToDisplayString() {
        Task task = new Task("My task");
        assertEquals("[ ] My task", task.toDisplayString());
    }

    @Test
    void testToDisplayStringDone() {
        Task task = new Task("My task");
        task.markDone();
        assertEquals("[X] My task", task.toDisplayString());
    }
}
