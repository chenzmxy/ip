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

    /**
     * Processes user commands in a continuous loop until the program is terminated.
     * Commands are read from user input, parsed, and handled by the parser.
     * The method halts when the parser signals termination, such as when the "bye" command is issued.
     * - Catches and handles custom {@code ComSciException} instances when command processing encounters errors.
     * - Displays error messages to the user through the UI.
     */
//    private void processCommands() {
//        boolean isRunning = true;
//        while (isRunning) {
//            try {
//                String input = readInput();
//                isRunning = parser.handleCommand(input);
//            } catch (ComSciException e) {
//                handleError(e);
//            }
//        }
//    }
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
