package ComSci.task;

import java.time.LocalDateTime;

/**
 * The deadline class
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * constructor
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert by.isAfter(LocalDateTime.now()) : "Bro! Your deadline passed already!";
        this.by = by;
    }

    @Override
    public String toDisplayString() {
        return "[D]" + super.toDisplayString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }
}

