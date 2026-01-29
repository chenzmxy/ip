package ComSci.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Task> findByKeyword(String keyword) {
        String needle = keyword.trim().toLowerCase();
        List<Task> results = new ArrayList<>();

        for (Task t : tasks) { // assuming your internal list is called tasks
            String hay = t.getDescription().toLowerCase();
            if (hay.contains(needle)) {
                results.add(t);
            }
        }
        return results;
    }

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
