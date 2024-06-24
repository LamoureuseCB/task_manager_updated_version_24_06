package in_memory_classes;

import interfaces.HistoryManager;
import interfaces.TaskManager;
import service_parts.Managers;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int currentId = 1;
    Map<Integer, Task> tasksMap = new HashMap<>();
    Map<Integer, Epic> epicsMap = new HashMap<>();
    Map<Integer, SubTask> subtasksMap = new HashMap<>();
    HistoryManager historyManager = Managers.getDefaultHistory();

    private int generateId() {
        return currentId++;
    }

    @Override
    public void addTask(Task task) {
        tasksMap.put(task.getId(), task);
    }

    @Override
    public void removeTask(Task task) {
        tasksMap.remove(task.getId());
    }

    @Override
    public void createTask(Task task) {
        task.setId(generateId());
        tasksMap.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        epicsMap.put(epic.getId(), epic);
    }

    @Override
    public void createSubTask(SubTask subtask) {
        subtasksMap.put(subtask.getId(), subtask);
    }

    @Override
    public List<SubTask> getEpicSubtasks(Epic epic) {
        return epic.getSubTaskList();
    }

    @Override
    public void updateStatusEpic(Epic epic) {
        epic.initStatus();
    }

    @Override
    public void updateTask(Task task, int id) {
        tasksMap.put(id, task);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        epicsMap.put(id, epic);
    }

    @Override
    public void updateSubtask(SubTask subtask, int id) {
        subtasksMap.put(id, subtask);
    }

    @Override
    public Task getTaskById(int id) {
        return tasksMap.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        return epicsMap.get(id);
    }

    @Override
    public SubTask getSubtaskById(int id) {
        return subtasksMap.get(id);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasksMap.values());
    }

    @Override
    public List<SubTask> getSubtasks(Epic epic) {
        return epic.getSubTaskList();
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epicsMap.values());
    }

    @Override
    public void deleteTaskById(int id) {
        tasksMap.remove(id);
    }

    @Override
    public void deleteEpicById(int id) {
        epicsMap.remove(id);
    }

    @Override
    public void deleteSubTaskById(int id) {
        subtasksMap.remove(id);
    }

    @Override
    public void deleteAllTasks() {
        tasksMap.clear();
    }

    @Override
    public void deleteAllEpics() {
        epicsMap.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasksMap.clear();
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}