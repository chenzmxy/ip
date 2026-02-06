package ComSci.main;

import java.util.Scanner;

import ComSci.exception.ComSciException;
import ComSci.storage.Storage;
import ComSci.task.TaskList;
import ComSci.ui.UI;



/**
 * The main ComSci class for the project
 */
public class ComSci {
    //base class of the programme

    private final UI ui = new UI();
    private final TaskList taskList = new TaskList();
    private final Scanner scanner = new Scanner(System.in);
    private final Storage storage = new Storage();
    private final Parser parser = new Parser(ui, taskList, storage);

    /**
     * runs the entire programme
     */
    public void run() {
        storage.loadInto(taskList);
        ui.greeting();

        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = scanner.nextLine().trim();
                isRunning = parser.handleCommand(input);
            } catch (ComSciException e) {
                ui.echo(e.getMessage());
                //show error
            }
        }

        scanner.close();

    }

    /**
     * Generates a response for the user's chat message.
     * @param input
     */
    public String getResponse(String input) {
        return "Hey bro! " + input;
    }


    public static void main(String[] args) {
        new ComSci().run();
    }
}
