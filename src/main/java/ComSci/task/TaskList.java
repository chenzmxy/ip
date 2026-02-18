package ComSci.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ComSci.exception.ComSciException;

/**
 * The task list class: use to manage the list of tasks
 */
public class TaskList {

    // tasklist class to store a list of tasks and to manage adding/deleting tasks
    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public List<Task> asUnmodifiableList() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * finds the name of the task by its keyword
     * using stream to:
     * 1. look for every task description
     * 2. determine if these descriptions contain the keyword
     * 3. filter out those who do not have the keyword
     * 4. put the rest into a list and return
     * @param keyword
     * @return
     */
    public List<Task> findByKeyword(String keyword) {
        return tasks.stream().filter(t -> t.getDescription().toLowerCase().contains(
                keyword.trim().toLowerCase()
        )).toList();
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param index The zero-based index of the task to be deleted.
     * @return The task that was removed from the task list.
     * @throws ComSciException If the specified index is out of bounds.
     */
    public Task delete(int index) {
        assert index >= 0 && index < tasks.size() : "Bro! Your index is gonna fly off the map!";
        return tasks.remove(index);
    }

    /**
     * Generates a formatted string representation of the task list.
     * If the task list is empty, it returns a message indicating no tasks.
     * Each task in the list is formatted and numbered sequentially.
     *
     * @return A string that contains a numbered list of tasks or a message indicating the list is empty.
     */
    public String getFormattedList() {

        // handle the base case (when there are no elements in the list)
        if (tasks.isEmpty()) {
            return "Here are the tasks in your list:\n(no tasks yet)";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(".")
                    .append(tasks.get(i).toDisplayString())
                    .append("\n");
        }
        return sb.toString().trim();
    }
}
