package ComSci.ui;

import ComSci.task.Task;
import java.util.List;

/**
 * The UI class for user interface
 */
public class UI {
    // user interface to initiate basic interactions: greetings and farewell
    private static final String LINE =
            "————————————————————————————————";

    /**
     * some standard interactions with the user inputs
     * @param found
     * @return
     */
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

    /**
     * print messages
     * @param s
     */
    public void echo(String s) {
        System.out.println(
                "\n"
                + s + "\n"
        );
    }

    /**
     * farewell
     */
    public void showBye() {
        System.out.println("\n"
                        + "Bye. Hope to see you again soon!\n"
        );
    }
}
