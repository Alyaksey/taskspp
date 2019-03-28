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
                return index < size;
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
        int removedItemsCount = removeAll(containsInCollection, c);
        return removedItemsCount > 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int removedItemsCount = removeAll(notContainsInCollection, c);
        return removedItemsCount > 0;
    }

    @Override
    public void clear() {
        this.forEach(this::remove);
    }

    private BiPredicate<String, MenuItem> areNamesEqual = (name, item) -> name.equals(item.getName());
    private BiPredicate<Object, MenuItem> areItemsEqual = Object::equals;
    private BiPredicate<Collection<?>, MenuItem> containsInCollection = Collection::contains;
    private BiPredicate<Collection<?>, MenuItem> notContainsInCollection = (c, item) -> !c.contains(item);

    @Override

    public boolean remove(String itemName) {
        return remove(areNamesEqual, itemName);
    }

    @Override
    public boolean remove(Object obj) {
        return remove(areItemsEqual, obj);
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
        return isEqual(obj);
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
        return getNode(index).getValue();
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        checkIndex(index);
        ListNode node = getNode(index);
        MenuItem replacedItem = node.getValue();
        node.setValue(element);
        return replacedItem;
    }

    @Override
    public void add(int index, MenuItem element) {
        checkIndex(index);
        ListNode previousNode = getNode(index - 1);
        ListNode currentNode = previousNode.getNext();
        ListNode newNode = new ListNode(element);
        if (currentNode == head) {
            newNode.setNext(currentNode);
            head = newNode;
        } else if (currentNode == tail) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            previousNode.setNext(newNode);
            newNode.setNext(currentNode);
        }
        size++;
    }

    @Override
    public MenuItem remove(int index) {
        ListNode node = getNode(index);
        MenuItem item = node.getValue();
        remove(item);
        return node.getValue();
    }

    @Override
    public int indexOf(Object o) {
        int indexOf = 0;
        for (MenuItem item : this) {
            if (item.equals(o)) {
                return indexOf;
            }
            indexOf++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndexO = 0;
        int lastIndex = 0;
        for (MenuItem item : this) {
            if (item.equals(o))
                lastIndex = lastIndexO;
            lastIndexO++;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<MenuItem> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        checkIndex(index);
        return new ListIterator<MenuItem>() {
            int currentIndex = index;
            ListNode lastReturned = null;
            ListNode previousNode = getNode(index - 1);
            ListNode currentNode = previousNode.getNext();

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public MenuItem next() {
                if (hasNext()) {
                    lastReturned = currentNode;
                    currentNode = currentNode.getNext();
                    previousNode = previousNode.getNext();
                    currentIndex++;
                    return lastReturned.getValue();
                }
                throw new NoSuchElementException("No such element");
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException("This operation is not supported");
            }

            @Override
            public MenuItem previous() {
                throw new UnsupportedOperationException("This operation is not supported");
            }

            @Override
            public int nextIndex() {
                if (hasNext())
                    return currentIndex + 1;
                throw new NoSuchElementException("No such index");
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("This operation is not supported");
            }

            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException("You can't remove item");
                }
                lastReturned = null;
                removeNode(currentNode, previousNode);
            }

            @Override
            public void set(MenuItem item) {
                if (lastReturned == null)
                    throw new IllegalStateException("You can't set the value for item");
                currentNode.setValue(item);
            }

            @Override
            public void add(MenuItem item) {
                lastReturned = null;
                InternetOrder.this.add(currentIndex, item);
            }
        };
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex, toIndex);
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

    private void checkBounds(int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("Wrong arguments");
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("Index is out of bounds;");
        if (toIndex > size)
            throw new IndexOutOfBoundsException("Index is out of bounds");
    }

    private void checkLawless(MenuItem item) {
        if (item instanceof Drink) {
            Drink drink = (Drink) item;
            if (drink.isAlcoholicDrink()) {
                if (customer.getAge() < 18)
                    throw new UnlawfulActionException("You're too young");
                if (LocalDateTime.now().getHour() > 22)
                    throw new UnlawfulActionException("It's too late");
            }
        }
    }

    private ListNode getNode(int index) {
        checkIndex(index);
        int i = 0;
        ListNode currentNode = head;
        //todo for i
        while (currentNode != null) {
            if (i == index) {
                break;
            }
            currentNode = currentNode.getNext();
            i++;
        }
        return currentNode;
    }
}
