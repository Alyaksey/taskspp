package barBossHouse;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

public class InternetOrdersManager implements OrdersManager, Deque<Order> {
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
        this.addAll(Arrays.asList(orders));
    }

    @Override
    public void addFirst(Order order) {
        checkNull(order);
        QueueNode newNode = new QueueNode(order);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    private void checkNull(Order order) {
        if (order == null)
            throw new NullPointerException("You cannot add null element");
    }

    @Override
    public void addLast(Order order) {
        checkNull(order);
        QueueNode newNode = new QueueNode(order);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public boolean offerFirst(Order order) {
        try {
            addFirst(order);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    @Override
    public boolean offerLast(Order order) {
        try {
            addLast(order);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    @Override
    public Order removeFirst() {
        checkEmptiness();
        QueueNode headNode = head;
        head = head.getNext();
        head.setPrev(null);
        if (isEmpty())
            tail = null;
        size--;
        return headNode.getValue();
    }

    private void checkEmptiness() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
    }

    @Override
    public Order removeLast() {
        checkEmptiness();
        QueueNode tailNode = tail;
        tail = tail.getPrev();
        tail.setNext(null);
        if (isEmpty())
            head = null;
        else
            tail.getPrev().setNext(tail);
        size--;
        return tailNode.getValue();
    }

    @Override
    public Order pollFirst() {
        if (isEmpty())
            return null;
        return removeFirst();
    }

    @Override
    public Order pollLast() {
        if (isEmpty())
            return null;
        return removeLast();
    }

    @Override
    public Order getFirst() {
        checkEmptiness();
        return peekFirst();
    }

    @Override
    public Order getLast() {
        checkEmptiness();
        return peekLast();
    }

    @Override
    public Order peekFirst() {
        if (isEmpty())
            return null;
        return head.getValue();
    }

    @Override
    public Order peekLast() {
        if (isEmpty())
            return null;
        return tail.getValue();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        QueueNode node = tail;
        while (node != null) {
            if (node.getValue().equals(o)) {
                remove(o);
                return true;
            }
            node = node.getPrev();
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        for (Order order : this) {
            if (order.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            QueueNode currentNode = head;

            @Override
            public boolean hasNext() {
                return (currentNode.getNext() != null);
            }

            @Override
            public Order next() {
                if (hasNext()) {
                    Order order = currentNode.getValue();
                    currentNode = currentNode.getNext();
                    return order;
                }
                throw new NoSuchElementException("No such element");
            }
        };
    }

    @Override
    public Object[] toArray() {
        Order[] orders = new Order[size];
        QueueNode currentNode = head;
        for (int i = 0; i < size; i++) {
            orders[i] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        return orders;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        Order[] items = (Order[]) toArray();
        if (a.length < size)
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public boolean add(Order order) {
        try {
            checkDuplicates(order);
        } catch (AlreadyAddedException e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean remove(Object o) {
        QueueNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.getValue().equals(o)) {
                if (currentNode.getPrev() != null) {
                    currentNode.getPrev().setNext(currentNode.getNext());
                    if (currentNode.next == null) {
                        tail = currentNode.getPrev();
                        tail.setNext(null);
                    }
                } else {
                    head = head.getNext();
                    head.setPrev(null);
                    if (isEmpty())
                        tail = null;
                }
                size--;
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return c.stream().allMatch(this::remove);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.stream().filter(order -> !c.contains(order)).allMatch(this::remove);
    }

    @Override
    public void clear() {
        this.forEach(this::remove);
    }

    @Override
    public boolean offer(Order order) {
        try {
            checkEmptiness();
            add(order);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
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
        return removeFirst();
    }

    @Override
    public Order poll() {
        if (isEmpty())
            return null;
        Order removedOrder = head.getValue();
        removeFirst();
        return removedOrder;
    }

    @Override
    public Order element() {
        return getFirst();
    }

    @Override
    public Order peek() {
        return getFirst();
    }

    @Override
    public void push(Order order) {
        addFirst(order);
    }

    @Override
    public Order pop() {
        return removeFirst();
    }

    @Override
    public Iterator<Order> descendingIterator() {
        return new Iterator<Order>() {
            QueueNode currentNode = tail;
            @Override
            public boolean hasNext() {
                return currentNode.getPrev() != null;
            }

            @Override
            public Order next() {
                if (hasNext()) {
                    Order order = currentNode.getValue();
                    currentNode = currentNode.getPrev() ;
                    return order;
                }
                throw new NoSuchElementException("No such element");
            }
        };
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
    public int ordersQuantity(LocalDate localDate) {
        return (int) this.stream()
                .filter(order -> localDate.equals(order.getDateTime().toLocalDate()))
                .count();
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
