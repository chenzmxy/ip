import java.util.Scanner;

public class ComSci {
    private final UI ui = new UI();
    private final TaskList taskList = new TaskList();
    private final Scanner scanner = new Scanner(System.in);
    private final Storage storage = new Storage();
    private final Parser parser = new Parser(ui, taskList, storage);


    public void run() {
        storage.loadInto(taskList);
        ui.greeting();

        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = scanner.nextLine().trim();
                isRunning = parser.handleCommand(input);
            } catch (ComSciException e) {
                ui.echo(e.getMessage());   // show error
            }
        }

        scanner.close();

    }



    public static void main(String[] args) {

        new ComSci().run();
    }
}
