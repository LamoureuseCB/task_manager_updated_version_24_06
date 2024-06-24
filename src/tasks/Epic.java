package tasks;

import service_parts.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<SubTask> subTaskList;

    public Epic(int id, String title, String description) {
        super(id, title, description);
        this.subTaskList = new ArrayList<>();
    }

    public void initStatus() {
        if (subTaskList.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }
        Status epicStatus = Status.NEW;
        for (SubTask subTask : subTaskList) {
            Status subTaskStatus = subTask.getStatus();
            if (subTaskStatus == Status.IN_PROGRESS) {
                epicStatus = Status.IN_PROGRESS;
                break;
            } else if (subTaskStatus == Status.DONE) {
                epicStatus = Status.DONE;
            } else {
                epicStatus = Status.NEW;
            }
        }
        setStatus(epicStatus);
    }

    public void remove(SubTask subTask) {
        subTaskList.remove(subTask);
    }

    public void add(SubTask subTask) {
        subTaskList.add(subTask);
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }
}
