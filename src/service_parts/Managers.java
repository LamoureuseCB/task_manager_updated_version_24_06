package service_parts;

import in_memory_classes.InMemoryHistoryManager;
import in_memory_classes.InMemoryTaskManager;
import interfaces.HistoryManager;
import interfaces.TaskManager;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}