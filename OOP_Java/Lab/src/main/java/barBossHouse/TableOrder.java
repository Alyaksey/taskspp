package barBossHouse;

public class TableOrder implements Order {
    private Customer customer;
    private MenuItem[] items;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;
    private static final int DEFAULT_SIZE = 0;

    /**
     * Конструктор, не принимающий параметров, инициирующий массив из 16 элементов (сами элементы
     * имеют значение null).
     */
    public TableOrder() {
        this(DEFAULT_CAPACITY, Customer.MATURE_UNKNOWN_CUSTOMER);
        size = DEFAULT_SIZE;
    }

    /**
     * Конструктор, принимающий целое число – емкость массива, инициирующий массив указанным числом
     * элементов (сами элементы имеют значение null).
     */
    public TableOrder(int capacity, Customer customer) {
        if (capacity != 0) {
            items = new MenuItem[capacity];
        } else
            items = new MenuItem[DEFAULT_CAPACITY];
        this.customer = customer;
        size = DEFAULT_SIZE;
    }

    /**
     * Конструктор, принимающий массив блюд.
     */
    public TableOrder(MenuItem[] items, Customer customer) {
        this.items = new MenuItem[items.length];
        this.customer = customer;
        System.arraycopy(items, 0, this.items, 0, items.length);
        size = items.length;
    }

    /**
     * Метод, добавляющий блюдо в заказ (принимает ссылку на экземпляр класса MenuItem). Пока этот метод
     * возвращает истину после выполнения операции добавления элемента.
     */
    @Override
    public boolean add(MenuItem item) {
        if (size == items.length) {
            MenuItem[] newItems = new MenuItem[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
        items[size++] = item;
        return true;
    }

    /**
     * Метод, удаляющий блюдо из заказа по его названию (принимает название блюда в качестве
     * параметра). Если блюд с заданным названием несколько, удаляется первое найденное. Возвращает
     * логическое значение (true, если элемент был удален).
     */
    @Override
    public boolean remove(String itemName) {
        for (int i = 0; i < size; i++) {
            if (itemName.equals(items[i].getName())) {
                shiftArray(i);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(MenuItem item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(items[i])) {
                shiftArray(i);
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, удаляющий все блюда с заданным именем (принимает название блюда в качестве
     * параметра). Возвращает число удаленных элементов.
     */
    @Override
    public int removeAll(String menuItemName) {
        int removedItemsCount = 0;
        MenuItem[] newItems = new MenuItem[items.length];
        for (int i = 0; i < size; i++) {
            if (menuItemName.equals(items[i].getName())) {
                items[i] = null;
                removedItemsCount++;
            }
            if (items[i] != null)
                newItems[i - removedItemsCount] = items[i];
        }
        items = newItems;
        size -= removedItemsCount;
        return removedItemsCount;
    }

    @Override
    public int removeAll(MenuItem menuItem) {
        int removedItemsCount = 0;
        MenuItem[] newItems = new MenuItem[items.length];
        for (int i = 0; i < size; i++) {
            if (menuItem.equals(items[i])) {
                items[i] = null;
                removedItemsCount++;
            }
            if (items[i] != null)
                newItems[i - removedItemsCount] = items[i];
        }
        items = newItems;
        size -= removedItemsCount;
        return removedItemsCount;
    }

    /**
     * Метод, возвращающий общее число блюд (повторяющиеся блюда тоже считаются) в заказе.
     */
    @Override
    public int itemsQuantity() {
        return size;
    }

    /**
     * Метод, возвращающий массив блюд (значений null в массиве быть не должно).
     */
    @Override
    public MenuItem[] getItems() {
        MenuItem[] newItems = new MenuItem[size];
        System.arraycopy(items, 0, newItems, 0, size);
        return newItems;
    }

    /**
     * Метод, возвращающий общую стоимость заказа.
     */
    @Override
    public double costTotal() {
        double costTotal = 0.0;
        for (int i = 0; i < size; i++)
            costTotal += items[i].getCost();
        return costTotal;
    }

    /**
     * Метод, возвращающий число заказанных блюд (принимает название блюда в качестве параметра)
     */
    @Override
    public int itemQuantity(String itemName) {
        int itemQuantity = 0;
        for (int i = 0; i < size; i++) {
            if (itemName.equals(items[i].getName())) {
                itemQuantity++;
            }
        }
        return itemQuantity;
    }

    @Override
    public int itemQuantity(MenuItem item) {
        int itemQuantity = 0;
        for (int i = 0; i < size; i++) {
            if (item.equals(items[i]))
                itemQuantity++;
        }
        return itemQuantity;
    }

    /**
     * Метод, возвращающий массив названий заказанных блюд (без повторов).
     */
    @Override
    public String[] itemsNames() {
        String[] names = new String[size];
        int uniqueNamesCount = 0;
        for (int i = 0; i < size; i++) {
            names[uniqueNamesCount] = items[i].getName();
            if (!containsDuplicates(names, uniqueNamesCount))
                uniqueNamesCount++;
        }
        String[] uniqueNames = new String[uniqueNamesCount];
        System.arraycopy(names, 0, uniqueNames, 0, uniqueNamesCount);
        return uniqueNames;
    }

    /**
     * Метод, возвращающий массив блюд, отсортированный по убыванию цены.
     */
    @Override
    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] sortedItems = getItems();
        mergeSort(sortedItems);
        return sortedItems;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private void shiftArray(int index) {
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[size - 1] = null;
    }

    private boolean containsDuplicates(String[] names, int count) {
        for (int i = 0; i < count; i++) {
            if (names[i].equals(names[count]))
                return true;
        }
        return false;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TableOrder: ").append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(items[i].toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TableOrder tableOrder = (TableOrder) obj;
        if (customer.equals(tableOrder.customer) && size == tableOrder.size) {
            String[] itemsNames = itemsNames();
            for (int i = 0; i < itemsNames.length; i++) {
                if (itemQuantity(itemsNames[i]) != tableOrder.itemQuantity(itemsNames[i]))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = customer.hashCode() ^ Integer.hashCode(size);
        for (int i = 0; i < size; i++) {
            hashCode ^= items[i].hashCode();
        }
        return hashCode;
    }

}