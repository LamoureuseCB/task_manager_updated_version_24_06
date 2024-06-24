import in_memory_classes.InMemoryHistoryManager;
import in_memory_classes.InMemoryTaskManager;
import interfaces.HistoryManager;
import interfaces.TaskManager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new InMemoryTaskManager();
        HistoryManager historyManager = new InMemoryHistoryManager();

        Task task1 = new Task(1, "Task 1", "fddjfh");
        Task task2 = new Task(2, "Task 2", "fddjfh");
        Epic epic1 = new Epic(1, "Epic 1", "fddjfh");
        SubTask subtask1 = new SubTask(1, "SubTask1", "fddjfh", epic1);

    }
}




