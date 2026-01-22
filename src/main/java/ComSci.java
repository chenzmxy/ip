import java.util.Scanner;

public class ComSci {
    private final UI ui = new UI();
    private final TaskList taskList = new TaskList();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        ui.greeting();

        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = scanner.nextLine().trim();
                isRunning = handleCommand(input);
            } catch (ComSciException e) {
                ui.echo(e.getMessage());   // show error
            }
        }

        scanner.close();

    }

    private boolean handleCommand(String input) throws ComSciException{


        if (input.equals("bye")) {
            ui.showBye();
            return false;
        }

        if (input.equals("list")) {
            ui.echo(taskList.getFormattedList());
            return true;
        }

        if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task task = taskList.get(index);
            task.markDone();
            ui.echo(
                    "Nice! I've marked this task as done:\n" +
                            task.toDisplayString()
            );
            return true;
        }

        if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = taskList.get(index);
            task.unmark();
            ui.echo(
                    "OK, I've marked this task as not done yet:\n" +
                            task.toDisplayString()
            );
            return true;
        }

        if (input.startsWith("todo")) {
            if (input.equals("todo")) {
                throw new ComSciException(
                        "Bro! Why never do anything one!"
                );
            }
            String desc = input.substring(5).trim();
            ToDo todo = new ToDo(desc);
            taskList.add(todo);
            ui.echo("Got it. I've added this task:\n"
                    + "  " + todo.toDisplayString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
            return true;
        }

        if (input.startsWith("deadline")) {
            if (!input.contains(" /by ")) {
                throw new ComSciException(
                        "Bro! What time are you talking about?"
                );
            }
            String rest = input.substring(9).trim();
            String[] parts = rest.split(" /by ", 2);
            String desc = parts[0].trim();
            String by = parts.length < 2 ? "" : parts[1].trim();

            if (parts[0].isEmpty()) {
                throw new ComSciException(
                        "Bro! Why u never do anything?"
                );
            }

            Deadline d = new Deadline(desc, by);
            taskList.add(d);


            ui.echo("Got it. I've added this task:\n"
                    + "  " + d.toDisplayString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
            return true;
        }

        if (input.startsWith("event")) {

            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new ComSciException(
                        "Bro! This event starts when/ends when?"
                );
            }

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
            return true;
        }

        // Default: add task
        throw new ComSciException(
                "Sorry, Idk what you talking about."
        );
    }

    public static void main(String[] args) {
        new ComSci().run();
    }
}
