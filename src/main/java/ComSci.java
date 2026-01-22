
import java.util.Scanner;

public class ComSci {
    private final UI ui;
    private final Scanner scanner;

    public ComSci() {
        this.ui = new UI();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        ui.greeting();

        while (true) {
            String input = scanner.nextLine();

            if ("bye".equals(input)) {
                ui.showBye();
                break;
            }

            ui.echo(input);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new ComSci().run();
    }
}
