package barBossHouse;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

public class InternetOrdersManager implements OrdersManager {
    private QueueNode head;
    private QueueNode tail;
    private int size;

    public InternetOrdersManager() {
        head = null;
        tail = null;
        size = 0;
    }

    public InternetOrdersManager(Order[] orders) throws AlreadyAddedException {
        head = null;
        tail = null;
        size = 0;
        for (int i = 0; i < orders.length; i++) {
            add(orders[i]);
        }
    }

    public boolean add(Order order) throws AlreadyAddedException {
        checkDuplicates(order);
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

    private void checkDuplicates(Order order) throws AlreadyAddedException {
        QueueNode currentNode = head;
        while (Objects.nonNull(currentNode)) {
            if (currentNode.getValue().getCustomer().equals(order.getCustomer()))
                throw new AlreadyAddedException("This order is already exists");
            currentNode = currentNode.getNext();
        }
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

    @Override
    public Order[] getOrders() {
        Order[] orders = new Order[size];
        QueueNode currentNode = head;
        for (int i = 0; i < size; i++) {
            orders[i] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        return orders;
    }

    @Override
    public int ordersCostSummary() {
        int ordersCostSummary = 0;
        QueueNode currentNode = head;
        while (currentNode != null) {
            ordersCostSummary += currentNode.getValue().costTotal();
            currentNode = currentNode.getNext();
        }
        return ordersCostSummary;
    }

    @Override
    public int itemsQuantity(String itemName) {
        return itemsQuantity(order -> order.itemQuantity(itemName));
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        return itemsQuantity(order -> order.itemQuantity(item));
    }

    private int itemsQuantity(Function<Order, Integer> function) {
        int itemsQuantity = 0;
        QueueNode currentNode = head;
        while (currentNode != null) {
            itemsQuantity += function.apply(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        return itemsQuantity;
    }

    @Override
    public int ordersQuantity() {
        return size;
    }

    @Override
    public int ordersQuantity(LocalDate localDate) {
        return size;
    }

    private BiPredicate<LocalDate, Order> areDatesEqual = (date, order) -> date.equals(order.getDateTime().toLocalDate());
    private BiPredicate<Customer, Order> areCustomersEqual = (customer, order) -> customer.equals(order.getCustomer());

    @Override
    public List<Order> getOrders(LocalDate localDate) {
        return getOrders(areDatesEqual, localDate);
    }

    @Override
    public List<Order> getOrders(Customer customer) {
        return getOrders(areCustomersEqual, customer);
    }

    private <T> List<Order> getOrders(BiPredicate<T, Order> biPredicate, T object) {
        List<Order> orders = new LinkedList<>();
        QueueNode currentNode = head;
        while (currentNode != null) {
            if (biPredicate.test(object, currentNode.getValue()))
                orders.add(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        return orders;
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
