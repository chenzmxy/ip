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
        assert description != null : "Bro! You do what sia?";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     *
     * Throws an assertion error if the task is already marked as done.
     */
    public void markDone() {
        assert !isDone : "Bro! You already marked this task as done!";
        isDone = true;
    }

    /**
     * Unmarks the task as not done.
     *
     * If the task is not already marked as done, an assertion error will be thrown.
     */
    public void unmark() {
        assert isDone : "Bro! The task not done!";
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
