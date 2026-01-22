import java.util.Scanner;

public class ComSci {
    private final UI ui = new UI();
    private final TaskList taskList = new TaskList();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        ui.greeting();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                ui.showBye();
                break;
            }

            if (input.equals("list")) {
                ui.echo(taskList.getFormattedList());
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task task = taskList.get(index);
                task.markDone();
                ui.echo(
                        "Nice! I've marked this task as done:\n" +
                                task.toDisplayString()
                );
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task task = taskList.get(index);
                task.unmark();
                ui.echo(
                        "OK, I've marked this task as not done yet:\n" +
                                task.toDisplayString()
                );
                continue;
            }

            if (input.startsWith("todo ")) {
                String desc = input.substring(5).trim();
                ToDo todo = new ToDo(desc);
                taskList.add(todo);
                ui.echo("Got it. I've added this task:\n"
                        + "  " + todo.toDisplayString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.");
                continue;
            }

            if (input.startsWith("deadline ")) {
                String rest = input.substring(9).trim();
                String[] parts = rest.split(" /by ", 2);
                String desc = parts[0].trim();
                String by = parts.length < 2 ? "" : parts[1].trim();

                Deadline d = new Deadline(desc, by);
                taskList.add(d);
                ui.echo("Got it. I've added this task:\n"
                        + "  " + d.toDisplayString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.");
                continue;
            }

            if (input.startsWith("event ")) {
                String rest = input.substring(6).trim();
                String[] a = rest.split(" /from ", 2);
                String desc = a[0].trim();

                String from = "";
                String to = "";
                if (a.length == 2) {
                    String[] b = a[1].split(" /to ", 2);
                    from = b[0].trim();
                    if (b.length == 2) {
                        to = b[1].trim();
                    }
                }
                Event e = new Event(desc, from, to);
                taskList.add(e);
                ui.echo("Got it. I've added this task:\n"
                        + "  " + e.toDisplayString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.");
                continue;
            }

            // Default: add task
            taskList.add(new ToDo(input));
            ui.echo("Got it. I've added this task:\n"
                    + "  " + taskList.get(taskList.size() - 1).toDisplayString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new ComSci().run();
    }
}
