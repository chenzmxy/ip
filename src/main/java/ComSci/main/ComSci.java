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
    }
    private String readInput() {
        return scanner.nextLine().trim();
    }

    private void handleError(ComSciException e) {
        ui.echo(e.getMessage());
    }


    /**
     * Generates a response for the user's chat message.
     * @param input
     */
    public String getResponse(String input) {
        try {
            // Use Parser to process the input and return appropriate response
            return parser.handleCommand(input);
        } catch (ComSciException e) {
            // Return error message if a ComSciException is encountered
            return e.getMessage();
        }

    }


    public static void main(String[] args) {
        new ComSci().run();
    }
}
