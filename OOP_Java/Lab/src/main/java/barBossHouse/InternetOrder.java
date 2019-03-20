package barBossHouse;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiPredicate;

public class InternetOrder implements Order {
    private int size;
    private ListNode head;
    private ListNode tail;
    private Customer customer;
    private LocalDateTime dateTime;

    public InternetOrder(MenuItem[] items, Customer customer) {
        size = 0;
        head = null;
        tail = null;
        this.customer = customer;
        this.addAll(Arrays.asList(items));
        dateTime = LocalDateTime.now();
    }

    public InternetOrder() {
        size = 0;
        head = null;
        tail = null;
        customer = new Customer();
        dateTime = LocalDateTime.now();
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (MenuItem item : this) {
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterator<MenuItem> iterator() {
        return new Iterator<MenuItem>() {
            ListNode currentNode = head;
            int index = 0;

            @Override
            public boolean hasNext() {
                return currentNode.getNext() != null;
            }

            @Override
            public MenuItem next() {
                if (hasNext()) {
                    MenuItem item = currentNode.getValue();
                    currentNode = currentNode.getNext();
                    index++;
                    return item;
                }
                return currentNode.getValue();
            }
        };
    }

    @Override
    public Object[] toArray() {
        return getItems();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        T[] items = (T[]) toArray();
        if (a.length < size)
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(MenuItem item) {
        checkLawless(item);
        ListNode node = new ListNode(item);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return this.stream().filter(item -> item.equals(o)).findFirst().filter(this::remove).isPresent();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        checkIndex(index);
        for (MenuItem item : c)
            add(index++, item);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object obj : c) {
            if (remove(obj))
                modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (MenuItem item : this) {
            if (!c.contains(item)) {
                remove(item);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        this.forEach(this::remove);
    }

    private BiPredicate<String, MenuItem> areNamesEqual = (name, item) -> name.equals(item.getName());
    private BiPredicate<MenuItem, MenuItem> areItemsEqual = MenuItem::equals;

    @Override
    public boolean remove(String itemName) {
        return remove(areNamesEqual, itemName);
    }

    @Override
    public boolean remove(MenuItem item) {
        return remove(areItemsEqual, item);
    }

    private <T> boolean remove(BiPredicate<T, MenuItem> biPredicate, T object) {
        ListNode currentNode = head;
        ListNode previousNode = null;
        while (currentNode != null) {
            if (biPredicate.test(object, currentNode.getValue())) {
                removeNode(currentNode, previousNode);
                size--;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public int removeAll(String itemName) {
        return removeAll(areNamesEqual, itemName);
    }

    @Override
    public int removeAll(MenuItem item) {
        return removeAll(areItemsEqual, item);
    }

    private <T> int removeAll(BiPredicate<T, MenuItem> biPredicate, T object) {
        ListNode currentNode = head;
        ListNode previousNode = null;
        int removedItemsCount = 0;
        while (currentNode != null) {
            if (biPredicate.test(object, currentNode.getValue())) {
                removeNode(currentNode, previousNode);
                size--;
                removedItemsCount++;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return removedItemsCount;
    }

    @Override
    public int itemsQuantity() {
        return size;
    }

    @Override
    public MenuItem[] getItems() {
        MenuItem[] items = new MenuItem[size];
        ListNode currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.getValue() != null)
                items[i] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        return items;
    }

    @Override
    public double costTotal() {
        double costTotal = 0.0;
        ListNode currentNode = head;
        while (currentNode != null) {
            costTotal += currentNode.getValue().getCost();
            currentNode = currentNode.getNext();
        }
        return costTotal;
    }

    @Override
    public int itemQuantity(String itemName) {
        return itemQuantity(areNamesEqual, itemName);
    }

    @Override
    public int itemQuantity(MenuItem item) {
        return itemQuantity(areItemsEqual, item);
    }

    private <T> int itemQuantity(BiPredicate<T, MenuItem> biPredicate, T object) {
        int itemQuantity = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            if (biPredicate.test(object, currentNode.getValue()))
                itemQuantity++;
            currentNode = currentNode.getNext();
        }
        return itemQuantity;
    }

    @Override
    public String[] itemsNames() {
        String[] names = new String[size];
        ListNode currentNode = head;
        int uniqueNamesCount = 0;
        while (currentNode != null) {
            names[uniqueNamesCount] = currentNode.getValue().getName();
            currentNode = currentNode.getNext();
            if (!containsDuplicates(names, uniqueNamesCount))
                uniqueNamesCount++;
        }
        String[] uniqueNames = new String[uniqueNamesCount];
        System.arraycopy(names, 0, uniqueNames, 0, uniqueNamesCount);
        return uniqueNames;
    }

    @Override
    public MenuItem[] sortedItemsByCostDesc() {
        //todo можно вынести в интерфейс+
        return Order.super.sortedItemsByCostDesc();
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InternetOrder:\n").append(customer.toString()).append("\n").append(size).append("\n");
        ListNode currentNode = head;
        while (currentNode != null) {
            sb.append(currentNode.getValue().toString()).append("\n");
            currentNode = currentNode.getNext();
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        //todo можно вынести в интерфейс с проверкой типа Order+
        return Order.super.isEqual(obj);
    }

    @Override
    public int hashCode() {
        int hashCode = customer.hashCode() ^ Integer.hashCode(size) ^ dateTime.hashCode();
        ListNode currentNode = head;
        while (currentNode != null) {
            hashCode ^= currentNode.getValue().hashCode();
            currentNode = currentNode.getNext();
        }
        return hashCode;
    }

    @Override
    public MenuItem get(int index) {
        checkIndex(index);
        int i = 0;
        for (MenuItem item : this) {
            if (i == index)
                return item;
            i++;
        }
        return null;
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        checkIndex(index);
        ListNode currentNode = head;
        int i = 0;
        while (currentNode != null) {
            if (i == index) {
                currentNode.setValue(element);
                return currentNode.getValue();
            }
            i++;
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public void add(int index, MenuItem element) {
        checkIndex(index);
        ListNode currentNode = head;
        ListNode previousNode = null;
        ListNode newNode = new ListNode(element);
        int i = 0;
        while (currentNode != null) {
            if (i == index) {
                if (i == 0) {
                    newNode.setNext(currentNode);
                    head = newNode;
                } else if (i == size - 1) {
                    tail.setNext(newNode);
                    tail = newNode;
                } else {
                    previousNode.setNext(newNode);
                    newNode.setNext(currentNode);
                }
                size++;
                return;
            }
            i++;
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
    }

    @Override
    public MenuItem remove(int index) {
        int i = 0;
        for (MenuItem item : this) {
            if (index == i) {
                remove(item);
                return item;
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (MenuItem item : this) {
            if (item.equals(o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = 0;
        int lastIndex = 0;
        for (MenuItem item : this) {
            if (item.equals(o))
                lastIndex = i;
            i++;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<MenuItem> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        return new ListIterator<MenuItem>() {
            int currentIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public MenuItem next() {
                if (hasNext()) {
                    return get(currentIndex++);
                }
                return get(currentIndex);
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public MenuItem previous() {
                if (hasPrevious())
                    return get(currentIndex--);
                return get(currentIndex);
            }

            @Override
            public int nextIndex() {
                if (hasNext())
                    return currentIndex + 1;
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                if (hasPrevious())
                    return currentIndex - 1;
                return currentIndex;
            }

            @Override
            public void remove() {
                InternetOrder.this.remove(currentIndex);
            }

            @Override
            public void set(MenuItem item) {
                InternetOrder.this.set(currentIndex, item);
            }

            @Override
            public void add(MenuItem item) {
                InternetOrder.this.add(currentIndex, item);
            }
        };
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        int i = 0;
        List<MenuItem> items = new InternetOrder();
        for (MenuItem item : this) {
            if (i >= fromIndex && i <= toIndex) {
                items.add(item);
            }
            i++;
        }
        return items;
    }

    private void removeNode(ListNode currentNode, ListNode previousNode) {
        if (previousNode != null) {
            previousNode.setNext(currentNode.getNext());
            if (currentNode.getNext() == null)
                tail = previousNode;
        } else {
            head = head.getNext();
            if (head == null)
                tail = null;
        }
    }

    private boolean containsDuplicates(String[] names, int count) {
        for (int i = 0; i < count; i++) {
            if (names[i].equals(names[count]))
                return true;
        }
        return false;
    }

    private class ListNode {
        private ListNode next;
        private MenuItem value;

        public ListNode() {
        }

        public ListNode(MenuItem value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public MenuItem getValue() {
            return value;
        }

        public void setValue(MenuItem value) {
            this.value = value;
        }

    }

    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index is out of bounds");
    }
}
