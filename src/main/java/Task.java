public class Task {
    private final String description;
    private boolean isDone;

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

    public String toDisplayString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
