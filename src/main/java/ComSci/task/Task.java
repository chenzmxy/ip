package ComSci.task;

/**
 * the parent clas: Task
 */
public class Task {

    // base class for the ToDo, Deadline, Event classes
    protected final String description;
    protected boolean isDone;

    /**
     * constructor method
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String toDisplayString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toStorageString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
