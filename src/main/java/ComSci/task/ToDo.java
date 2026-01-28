package ComSci.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toDisplayString() {
        return "[T]" + super.toDisplayString();
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

}
