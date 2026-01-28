package ComSci.task;

public class Task {
    protected final String description;
    protected boolean isDone;

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
