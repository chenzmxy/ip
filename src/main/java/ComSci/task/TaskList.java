package ComSci.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
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


    public String getFormattedList() {
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
