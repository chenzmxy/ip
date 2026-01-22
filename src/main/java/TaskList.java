import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<String> items = new ArrayList<>();

    public void add(String item) {
        items.add(item);
    }

    public String getFormattedList() {
        if (items.isEmpty()) {
            return "(no items yet)";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(i + 1).append(". ").append(items.get(i));
            if (i < items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
