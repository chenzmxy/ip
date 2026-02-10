package ComSci.task;

import java.time.LocalDateTime;

/**
 * the event class
 */
public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * constructor method
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert from.isBefore(to) : "Bro! Are you time traveler? The time wrong already!";
        this.from = from;
        this.to = to;
    }

    @Override
    public String toDisplayString() {
        return "[E]" + super.toDisplayString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from + " | " + to;
    }
}
