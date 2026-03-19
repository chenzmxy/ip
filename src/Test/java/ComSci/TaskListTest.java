package ComSci;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ComSci.exception.ComSciException;
import ComSci.task.Task;
import ComSci.task.TaskList;

class TaskListTest {

    @Test
    void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
    }

    @Test
    void testAddDuplicate() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test task");
        taskList.add(task);
        Task duplicate = new Task("Test task");
        assertThrows(ComSciException.class, () -> taskList.add(duplicate));
    }

    @Test
    void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Task to delete");
        taskList.add(task);
        Task removed = taskList.delete(0);
        assertEquals("Task to delete", removed.getDescription());
        assertEquals(0, taskList.size());
    }

    @Test
    void testFindByKeyword() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("Buy groceries");
        Task task2 = new Task("Read book");
        taskList.add(task1);
        taskList.add(task2);
        var found = taskList.findByKeyword("book");
        assertEquals(1, found.size());
        assertTrue(found.get(0).getDescription().contains("book"));
    }

    @Test
    void testFindNoMatch() {
        TaskList taskList = new TaskList();
        Task task = new Task("Buy groceries");
        taskList.add(task);
        var found = taskList.findByKeyword("xyz");
        assertTrue(found.isEmpty());
    }

    @Test
    void testGetFormattedListEmpty() {
        TaskList taskList = new TaskList();
        String result = taskList.getFormattedList();
        assertTrue(result.contains("no tasks"));
    }

    @Test
    void testGetFormattedListWithTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("First task");
        Task task2 = new Task("Second task");
        taskList.add(task1);
        taskList.add(task2);
        String result = taskList.getFormattedList();
        assertTrue(result.contains("1."));
        assertTrue(result.contains("2."));
        assertTrue(result.contains("First task"));
        assertTrue(result.contains("Second task"));
    }

    @Test
    void testFindByKeywordCaseInsensitive() {
        TaskList taskList = new TaskList();
        Task task = new Task("BUY GROCERIES");
        taskList.add(task);
        var found = taskList.findByKeyword("buy");
        assertEquals(1, found.size());
    }
}
