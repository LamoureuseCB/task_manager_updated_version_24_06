package service_parts;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList {
    public Node<Task> head;
    public Node<Task> tail;
    public int size = 0;
    private static final int MAX_SIZE = 10;

    public Node<Task> linkLast(Task task) {
        if (size >= MAX_SIZE) {
            removeNode(head);
        }

        Node<Task> newNode = new Node<>(tail, task, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return newNode;
    }

    public void removeNode(Node<Task> node) {
        if (node == null) {
            return;
        }
        Node<Task> prev = node.prev;
        Node<Task> next = node.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }
        size--;
    }

    public List<Task> getTasks() {
        List<Task> taskList = new ArrayList<>();
        Node<Task> current = head;
        while (current != null) {
            taskList.add(current.task);
            current = current.next;
        }
        return taskList;
    }
}
