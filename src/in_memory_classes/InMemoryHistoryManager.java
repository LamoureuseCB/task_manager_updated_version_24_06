package in_memory_classes;

import interfaces.HistoryManager;
import service_parts.CustomLinkedList;
import service_parts.Node;
import tasks.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private Node<Task> head;
    private Node<Task> tail;
    private static final int HISTORY_SIZE = 10;
    private final Map<Integer, Node<Task>> historyMap = new HashMap<>();
    private final CustomLinkedList historyList = new CustomLinkedList();

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        if (historyMap.containsKey(task.getId())) {
            historyList.removeNode(historyMap.get(task.getId()));
        }
        historyList.linkLast(task);
        historyMap.put(task.getId(), historyList.tail);
        if (historyList.size > HISTORY_SIZE) {
            historyMap.remove(historyList.head.task.getId());
            historyList.removeNode(historyList.head);
        }
    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            historyList.removeNode(historyMap.get(id));
            historyMap.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyList.getTasks();
    }
}


