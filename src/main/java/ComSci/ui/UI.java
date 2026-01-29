package ComSci.ui;

import ComSci.task.Task;
import java.util.List;

public class UI {
    // user interface to initiate basic interactions: greetings and farewell
    private static final String LINE =
            "————————————————————————————————";
    public void greeting() {
        System.out.println(LINE + "\n"
                        + "Hello! I'm ComSci\n"
                        + "What can I do for you?\n"
                        + LINE
        );
    }

    public String formatFoundTasks(List<Task> found) {
        if (found.isEmpty()) {
            return "Here are the matching tasks in your list:\n(no matches found)";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < found.size(); i++) {
            sb.append(i + 1)
                    .append(".")
                    .append(found.get(i).toDisplayString())
                    .append("\n");
        }
        return sb.toString().trim();
    }

    public void echo(String s) {
        System.out.println(
                LINE + "\n"
                + s + "\n"
                + LINE
        );
    }

    public void showBye() {
        System.out.println(LINE + "\n"
                        + "Bye. Hope to see you again soon!\n"
                        + LINE
        );
    }
}
