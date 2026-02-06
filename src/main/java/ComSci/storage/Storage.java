package ComSci.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ComSci.exception.ComSciException;
import ComSci.task.Deadline;
import ComSci.task.Event;
import ComSci.task.Task;
import ComSci.task.TaskList;
import ComSci.task.ToDo;



/**
 * This class is to save the list from previous chat
 */
public class Storage {
    // relative path from project root (OS-independent)
    private static final Path FILE_PATH = Paths.get("data", "comsci.txt");

    /**
     * saves the list generated from the chat
     * @param taskList
     */
    public void save(TaskList taskList) {
        try {
            Files.createDirectories(FILE_PATH.getParent()); // handle folder not exist yet

            List<String> lines = new ArrayList<>();
            for (Task t : taskList.asUnmodifiableList()) {
                lines.add(t.toStorageString());
            }

            Files.write(FILE_PATH, lines); // CREATE + TRUNCATE by default
        } catch (IOException e) {
            throw new ComSciException("Bro! I cannot save your tasks: " + e.getMessage());
        }
    }

    /**
     * loads the saved list
     * @param taskList
     */
    public void loadInto(TaskList taskList) {
        if (!Files.exists(FILE_PATH)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                Task t = parseLine(line);
                if (t != null) {
                    taskList.add(t);
                }
                // stretch goal (corruption): if null, we just skip the bad line
            }
        } catch (IOException e) {
            throw new ComSciException("Bro! I cannot load your tasks: " + e.getMessage());
        }
    }

    /**
     * Format examples:
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2pm | Aug 6th 4pm
     */
    private Task parseLine(String line) {
        if (line == null) {
            return null;
        }
        line = line.trim();
        if (line.isEmpty()) {
            return null;
        }

        // split by | with optional spaces around it
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0].trim();
        String doneStr = parts[1].trim();

        int done;
        try {
            done = Integer.parseInt(doneStr);
        } catch (NumberFormatException e) {
            return null;
        }

        boolean isDone = (done == 1);

        try {
            switch (type) {
            case "T": {
                String desc = parts[2];
                ToDo t = new ToDo(desc);
                t.setDone(isDone);
                return t;
            }
            case "D": {
                if (parts.length < 4) {
                    return null;
                }
                String desc = parts[2];
                java.time.LocalDateTime by = java.time.LocalDateTime.parse(parts[3].trim());
                Deadline d = new Deadline(desc, by);

                d.setDone(isDone);
                return d;
            }
            case "E": {
                if (parts.length < 5) {
                    return null;
                }
                String desc = parts[2];
                java.time.LocalDateTime from = java.time.LocalDateTime.parse(parts[3].trim());
                java.time.LocalDateTime to = java.time.LocalDateTime.parse(parts[4].trim());
                Event e = new Event(desc, from, to);
                e.setDone(isDone);
                return e;
            }
            default:
                return null;
            }
        } catch (Exception ex) {
            // corruption safety net
            return null;
        }
    }
}
