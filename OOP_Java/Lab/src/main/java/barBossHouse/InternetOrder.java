package barBossHouse;

import java.time.LocalDateTime;
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
        for (int i = 0; i < items.length; i++) {
            add(items[i]);
        }
        dateTime = LocalDateTime.now();
    }

    public InternetOrder() {
        size = 0;
        head = null;
        tail = null;
        customer = new Customer();
        dateTime = LocalDateTime.now();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean add(MenuItem item) {
        if (item instanceof Drink) {
            Drink drink = (Drink) item;
            if (drink.isAlcoholicDrink() && (customer.getAge() < 18 || dateTime.now().getHour() > 22))
                throw new UnlawfulActionException("You're not allowed to buy an alcohol");
        }
        ListNode node = new ListNode(item);
        if (head == null) {
            head = node;
            tail = node;
        } else
            tail.setNext(node);
        size++;
        return true;
    }

    private BiPredicate<String, MenuItem> areNamesEqual = (name, item) -> name.equals(item.getName());
    private BiPredicate<MenuItem, MenuItem> areItemsEqual = (firstItem, secondItem) -> firstItem.equals(secondItem);

    public boolean remove(String itemName) {
        return remove(areNamesEqual, itemName);
    }

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

    public int removeAll(String itemName) {
        return removeAll(areNamesEqual, itemName);
    }

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

    public int itemsQuantity() {
        return size;
    }

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

    public double costTotal() {
        double costTotal = 0.0;
        ListNode currentNode = head;
        while (currentNode != null) {
            costTotal += currentNode.getValue().getCost();
            currentNode = currentNode.getNext();
        }
        return costTotal;
    }

    public int itemQuantity(String itemName) {
        return itemQuantity(areNamesEqual, itemName);
    }

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

    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] sortedItems = getItems();
        mergeSort(sortedItems);
        return sortedItems;
    }

    public Customer getCustomer() {
        return customer;
    }

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
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        InternetOrder internetOrder = (InternetOrder) obj;
        if (customer.equals(internetOrder.customer) && size == internetOrder.size && dateTime.equals(internetOrder.dateTime)) {
            String[] itemsNames = itemsNames();
            for (int i = 0; i < itemsNames.length; i++) {
                if (itemQuantity(itemsNames[i]) != internetOrder.itemQuantity(itemsNames[i]))
                    return false;
            }
            return true;
        }
        return false;
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

    private void mergeSort(MenuItem[] items) {
        if (items.length < 2)
            return;
        int mid = items.length / 2;
        MenuItem[] leftArray = new MenuItem[mid];
        MenuItem[] rightArray = new MenuItem[items.length - mid];
        System.arraycopy(items, 0, leftArray, 0, leftArray.length);
        System.arraycopy(items, leftArray.length, rightArray, 0, rightArray.length);
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(items, leftArray, rightArray);
    }

    private void merge(MenuItem[] items, MenuItem[] leftArray, MenuItem[] rightArray) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].getCost() >= rightArray[j].getCost()) {
                items[k++] = leftArray[i++];
            } else {
                items[k++] = rightArray[j++];
            }
        }
        while (i < leftArray.length) {
            items[k++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            items[k++] = rightArray[j++];
        }
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
}
