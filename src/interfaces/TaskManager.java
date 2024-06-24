package interfaces;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void removeTask(Task task);

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubTask(SubTask subtask);

    List<SubTask> getEpicSubtasks(Epic epic);

    void updateStatusEpic(Epic epic);

    void updateTask(Task task, int id);

    void updateEpic(Epic epic, int id);

    void updateSubtask(SubTask subtask, int id);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubtaskById(int id);

    List<Task> getTasks();

    List<SubTask> getSubtasks(Epic epic);

    List<Epic> getEpics();

    void deleteTaskById(int id);

    void deleteEpicById(int id);

    void deleteSubTaskById(int id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    List<Task> getHistory();
}
