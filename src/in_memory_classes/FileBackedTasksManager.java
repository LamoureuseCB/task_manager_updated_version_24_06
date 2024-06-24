package in_memory_classes;

import interfaces.HistoryManager;
import service_parts.ManagerLoadException;
import service_parts.ManagerSaveException;
import service_parts.Status;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private static final String FIRST_LINE = "id, type, name, status, description, epic";
    private File file;

    public FileBackedTasksManager(File file) {
        this.file = file;
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();
    }

    @Override
    public void removeTask(Task task) {
        super.removeTask(task);
        save();

    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubTask(SubTask subtask) {
        super.createSubTask(subtask);
        save();
    }

    @Override
    public void updateStatusEpic(Epic epic) {
        super.updateStatusEpic(epic);
        save();
    }

    @Override
    public void updateTask(Task task, int id) {
        super.updateTask(task, id);
        save();
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        super.updateEpic(epic, id);
        save();
    }

    @Override
    public void updateSubtask(SubTask subtask, int id) {
        super.updateSubtask(subtask, id);
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void deleteSubTaskById(int id) {
        super.deleteSubTaskById(id);
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public List getTasks() {
        return super.getTasks();
    }

    @Override
    public List getSubtasks(Epic epic) {
        return super.getSubtasks(epic);
    }

    @Override
    public List getEpics() {
        return super.getEpics();
    }


    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(FIRST_LINE);
            writer.newLine();

            for (Task task : tasksMap.values()) {
                writer.write(task.toString());
                writer.newLine();
            }
            for (Epic epic : epicsMap.values()) {
                writer.write(epic.toString());
                writer.newLine();
            }

            for (SubTask subTask : subtasksMap.values()) {
                writer.write(subTask.toString());
                writer.newLine();
            }
            writer.write(historyManager.getHistory().toString();
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка записи файла", e);
        }
    }


    @Override
    public String toString(Task task) {
        return String.format("%d, %s, %s, %s, %s, %s",
                task.getId());
        task.getTitle().toUpperCase();
        task.getStatus();
        task.getDescription();
//        как прописать эпик????
    }

    public void fromString(String sentence) {
        String[] words = sentence.split(",");
        int id = Integer.parseInt(words[0]);
        String type = words[1];
        String name = words[2];
        Status status = Status.valueOf(words[3]);
        String description = words[4];
//        Epic epic = words[5]; конфликт типов епик и строки???valueOf???
        switch (type) {
            case "TASK":
                return new Task(id, type, name, status, description, epic);
            case "EPIC":
                return new Epic(id, type, name, status, description, epic);
            case "SUBTASK":
                return new SubTask(id, type, name, status, description, epic);
        }
    }

    static String historyToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        for (Task task : manager.getHistory()) {
            sb.append(task.getId());
            sb.append("\n");
        }
        return sb.toString();
    }

    static List<Integer> historyFromString(String sentence) {
        List<Integer> history = new ArrayList<>();
        if (!sentence.isEmpty()) {
            for (String id : sentence.split(",")) {
                history.add(Integer.parseInt(id));
            }
        }
        return history;
    }

    private FileBackedTasksManager loadFile() throws ManagerLoadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && !line.equals(FIRST_LINE)) {
                    if (line.startsWith("TASK") || line.startsWith("EPIC") || line.startsWith("SUBTASK")) {
                        Task task = fromString(line);
                        addTask(task);
                    }
                }
            }
            catch(IOException e){
                throw new ManagerLoadException("Ошибка загрузки", e);

            }
        }

        public static FileBackedTasksManager loadFromFile (File file)throw ManagerLoadException {
            FileBackedTasksManager fBTmanager = new FileBackedTasksManager(file);
            fBTmanager.loadFile();
            return fBTmanager;
        }
    }

    static void main(String[] args) {
        File file = new File("src/tasks.csv");
        FileBackedTasksManager taskManager = new FileBackedTasksManager(file);

        Task task1 = new Task(1, "Task 1", "hdjgdfgh", Status.NEW);
        Task task2 = new Task(2, "Task 2", "kajshdksjha", Status.IN_PROGRESS);
        Epic epic1 = new Epic(3, "Epic 1", "sdfhdsjkf", Status.NEW);

        SubTask subTask1 = new SubTask(4, "SubTask 1", "kayfuaiyai", Status.NEW, 3);
        SubTask subTask2 = new SubTask(5, "SubTask 2", "eworuewou", Status.NEW, 3);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createEpic(epic1);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);

        taskManager.save();

        FileBackedTasksManager loadedTaskManager = null;
        try {
            loadedTaskManager = FileBackedTasksManager.loadFromFile(file);
        } catch (ManagerLoadException e) {
            System.out.println("Ошибка загрузки задач: " + e.getMessage());
        }

        if (loadedTaskManager != null) {
            System.out.println("Tasks:");
            for (Task task : loadedTaskManager.getTasks()) {
                System.out.println(task);
            }

            System.out.println("Epics:");
            for (Epic epic : loadedTaskManager.getEpics()) {
                System.out.println(epic);
            }

            System.out.println("SubTasks:");
            for (SubTask subTask : loadedTaskManager.getSubtasks(epic1)) {
                System.out.println(subTask);
            }
        }
    }
}