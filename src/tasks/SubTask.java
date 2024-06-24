package tasks;

import service_parts.Status;

public class SubTask extends Task {
    private final Epic epic;

    public SubTask(int id, String title, String description, Epic epic) {
        super(id, title, description);
        this.epic = epic;
    }

    @Override
    public void setStatus(Status status) {
        super.setStatus(status);
        epic.initStatus();
    }

    public Epic getEpic() {
        return epic;
    }
}
