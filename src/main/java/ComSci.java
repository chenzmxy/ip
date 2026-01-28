import java.util.Scanner;

public class ComSci {
    private final UI ui = new UI();
    private final TaskList taskList = new TaskList();
    private final Scanner scanner = new Scanner(System.in);
    private final Storage storage = new Storage();



    public void run() {
        storage.loadInto(taskList);
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
            int index;
            try {
                index = Integer.parseInt(input.substring(5).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new ComSciException("Bro! I need a number!. E.g. mark 2");
            }

            if (index < 0 || index >= taskList.size()) {
                throw new ComSciException("Bro! That task number is out of range!");
            }

            Task task = taskList.get(index);
            task.markDone();
            storage.save(taskList);

            ui.echo("Nice! I've marked this task as done:\n" + task.toDisplayString());
            return true;
        }


        if (input.startsWith("unmark ")) {
            int index;
            try {
                index = Integer.parseInt(input.substring(7).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new ComSciException("Bro! I need a number!. E.g. unmark 2");
            }

            if (index < 0 || index >= taskList.size()) {
                throw new ComSciException("Bro! That task number is out of range!");
            }

            Task task = taskList.get(index);
            task.unmark();
            storage.save(taskList);

            ui.echo("OK, I've marked this task as not done yet:\n" + task.toDisplayString());
            return true;
        }


        if (input.startsWith("todo")) {
            String desc = input.length() > 4 ? input.substring(4).trim() : "";

            if (desc.isEmpty()) {
                throw new ComSciException("Bro! Why never do anything one!");
            }
            ToDo todo = new ToDo(desc);
            taskList.add(todo);
            storage.save(taskList);
            ui.echo("Got it. I've saved this task:\n"
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
            storage.save(taskList);

            ui.echo("Got it. I've saved this task:\n"
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

            if (desc.isEmpty()) {
                throw new ComSciException("Bro! What's the event about?");
            }

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
            storage.save(taskList);
            ui.echo("Got it. I've saved this task:\n"
                    + "  " + e.toDisplayString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
            return true;
        }

        if (input.startsWith("delete")) {
            if (input.equals("delete")) {
                throw new ComSciException("Bro! What am I supposed to delete? E.g. delete 2");
            }

            int index;
            try {
                index = Integer.parseInt(input.substring(7).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new ComSciException("Bro! I need a number!. E.g. delete 2");
            }

            if (index < 0 || index >= taskList.size()) {
                throw new ComSciException("Bro! That task number is out of range!");
            }

            Task removed = taskList.remove(index);
            storage.save(taskList);

            ui.echo("Noted. I've removed this task:\n"
                    + "  " + removed.toDisplayString() + "\n"
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
