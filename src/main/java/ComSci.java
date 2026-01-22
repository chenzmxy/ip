
import java.util.Scanner;

public class ComSci {
    private final UI ui;
    private final Scanner scanner;
    private final TaskList tasks;

    public ComSci() {
        this.ui = new UI();
        this.scanner = new Scanner(System.in);
        this.tasks = new TaskList();
    }

    public void run() {
        ui.greeting();

        while (true) {
            String input = scanner.nextLine().trim();

            if ("bye".equals(input)) {
                ui.showBye();
                break;
            }

            if (input.equals("list")) {
                ui.echo(tasks.getFormattedList());
                continue;
            }

            tasks.add(input);
            ui.echo("added: " + input);

        }

        scanner.close();
    }

    public static void main(String[] args) {
        new ComSci().run();
    }
}
