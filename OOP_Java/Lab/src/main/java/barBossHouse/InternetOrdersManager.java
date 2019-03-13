package barBossHouse;

import java.util.function.Function;

public class InternetOrdersManager implements OrdersManager {
    private QueueNode head;
    private QueueNode tail;
    private int size;

    public InternetOrdersManager() {
        head = new QueueNode();
        tail = new QueueNode();
        size = 0;
    }

    public InternetOrdersManager(MenuItem[] items) {
        head = new QueueNode();
        tail = new QueueNode();
        size = 0;
    }

    public boolean add(Order order) {
        QueueNode node = new QueueNode(order);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        size++;
        return true;
    }

    public Order order() {
        return head.value;
    }

    public Order remove() {
        if (head == null)
            return null;
        else {
            Order order = head.getValue();
            if (size == 1) {
                head = null;
                tail = null;
                size--;
            } else {
                head = head.getNext();
                head.setPrev(null);
                size--;
                if (size == 1)
                    tail.setPrev(null);
            }
            return order;
        }
    }

    public Order[] getOrders() {
        Order[] orders = new Order[size];
        QueueNode currentNode = head;
        for (int i = 0; i < size; i++) {
            orders[i] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        return orders;
    }
    public int ordersCostSummary() {
        int ordersCostSummary = 0;
        QueueNode currentNode = head;
        while (currentNode != null){
            ordersCostSummary += currentNode.getValue().costTotal();
            currentNode = currentNode.getNext();
        }
        return ordersCostSummary;
    }

    public int itemsQuantity(String itemName) {
        return itemsQuantity(order -> order.itemQuantity(itemName));
    }

    public int itemsQuantity(MenuItem item) {
        return itemsQuantity(order -> order.itemQuantity(item));
    }
    private  int itemsQuantity(Function<Order,Integer> function){
        int itemsQuantity = 0;
        QueueNode currentNode = head;
        while (currentNode != null){
            itemsQuantity += function.apply(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        return itemsQuantity;
    }

    public int ordersQuantity() {
        return size;
    }

    private class QueueNode {
        private QueueNode next;
        private QueueNode prev;
        private Order value;

        public QueueNode() {
        }

        public QueueNode(Order value) {
            this.value = value;
        }

        public QueueNode getNext() {
            return next;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }

        public QueueNode getPrev() {
            return prev;
        }

        public void setPrev(QueueNode prev) {
            this.prev = prev;
        }

        public Order getValue() {
            return value;
        }

        public void setValue(Order value) {
            this.value = value;
        }
    }
}
