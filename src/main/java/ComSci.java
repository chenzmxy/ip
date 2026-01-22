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

            // Default: add task
            taskList.add(input);
            ui.echo("added: " + input);
        }
    }

    public static void main(String[] args) {
        new ComSci().run();
    }
}
