package ComSci.main;

import ComSci.exception.ComSciException;
import ComSci.storage.Storage;
import ComSci.task.Task;
import ComSci.task.ToDo;
import ComSci.task.Event;
import ComSci.task.Deadline;
import ComSci.task.TaskList;
import ComSci.ui.UI;
import ComSci.util.DateTimeUtil;

public class Parser {
    // This class will handle the command input for the programme

    private final UI ui;
    private final TaskList taskList;
    private final Storage storage;

    public Parser(UI ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }


    /**
     * possible input:
     * bye: greets goodbye from ui class and terminate
     * list: asks the tasklist to show tasks
     * mark: asks the Task class to mark done
     * unmark: asks the Task class to mark undone
     * todo/deadline/event: creates instances for the tasks
     * delete: asks the tasklist to delete
     */
    public boolean handleCommand(String input) throws ComSciException {
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
            // 2 scenarios: no index or index out of range
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
            // 2 scenarios: no index or index out of range
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

            // when the todo task has no details
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
            String rest = input.substring(9).trim();
            String[] parts = rest.split(" /by ", 2);
            String desc = parts[0].trim();
            String byStr = parts.length < 2 ? "" : parts[1].trim();

            // when the deadline task has no date
            if (byStr.isEmpty()) {
                throw new ComSciException("Bro! What time are you talking about?");
            }

            java.time.LocalDateTime by = DateTimeUtil.parseUserDateTime(byStr);
            Deadline d = new Deadline(desc, by);

            // when the deadline task has no details
            if (parts[0].isEmpty()) {
                throw new ComSciException(
                        "Bro! Why u never do anything?"
                );
            }


            taskList.add(d);
            storage.save(taskList);

            ui.echo("Got it. I've saved this task:\n"
                    + "  " + d.toDisplayString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
            return true;
        }

        if (input.startsWith("event")) {

            // when the event task has no dates specified
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

            java.time.LocalDateTime fromDt = DateTimeUtil.parseUserDateTime(from);
            java.time.LocalDateTime toDt = DateTimeUtil.parseUserDateTime(to);

            Event e = new Event(desc, fromDt, toDt);

            taskList.add(e);
            storage.save(taskList);
            ui.echo("Got it. I've saved this task:\n"
                    + "  " + e.toDisplayString() + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
            return true;
        }

        if (input.startsWith("delete")) {

            // error when there is no numer input or number out of range
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

}
