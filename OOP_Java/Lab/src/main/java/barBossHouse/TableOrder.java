package barBossHouse;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableOrder implements Order {
    private Customer customer;
    private MenuItem[] items;
    private int size;
    private LocalDateTime dateTime;

    private static final int DEFAULT_CAPACITY = 16;
    private static final int DEFAULT_SIZE = 0;

    /**
     * Конструктор, не принимающий параметров, инициирующий массив из 16 элементов (сами элементы
     * имеют значение null).
     */
    public TableOrder() {
        this(DEFAULT_CAPACITY, new Customer());
        size = DEFAULT_SIZE;
        dateTime = LocalDateTime.now();
    }

    /**
     * Конструктор, принимающий целое число – емкость массива, инициирующий массив указанным числом
     * элементов (сами элементы имеют значение null).
     */
    public TableOrder(int capacity, Customer customer) {
        if (capacity < 0)
            throw new NegativeSizeException("Array's capacity cannot be negative");
        if (capacity != 0) {
            items = new MenuItem[capacity];
        } else
            items = new MenuItem[DEFAULT_CAPACITY];
        this.customer = customer;
        size = DEFAULT_SIZE;
        dateTime = LocalDateTime.now();
    }

    /**
     * Конструктор, принимающий массив блюд.
     */
    public TableOrder(MenuItem[] items, Customer customer) {
        this.items = new MenuItem[items.length];
        this.customer = customer;
        System.arraycopy(items, 0, this.items, 0, items.length);
        size = items.length;
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
        return Arrays.asList(items).contains(o);
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return new Iterator<MenuItem>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public MenuItem next() {
                if (hasNext())
                    return items[currentIndex++];
                throw new NoSuchElementException("There's no such element");
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size, MenuItem[].class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * Метод, добавляющий блюдо в заказ (принимает ссылку на экземпляр класса MenuItem). Пока этот метод
     * возвращает истину после выполнения операции добавления элемента.
     */
    @Override
    public boolean add(MenuItem item) {
        checkLawless(item);
        if (size == items.length) {
            increaseCapacity();
        }
        items[size++] = item;
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
        if (c.size() + size() > items.length) {
            increaseCapacity();
        }
        System.arraycopy(items, index, items, index + c.size(), c.size());
        for (MenuItem item : c)
            items[index++] = item;
        return true;
    }

    private void increaseCapacity() {
        MenuItem[] newItems = new MenuItem[items.length * 2];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        return removeAll(notContainsInCollection, c) > 0;
    }

    @Override
    public void clear() {
        IntStream.range(0, size).forEach(i -> items[i] = null);
        size = 0;
    }

    /**
     * Метод, удаляющий блюдо из заказа по его названию (принимает название блюда в качестве
     * параметра). Если блюд с заданным названием несколько, удаляется первое найденное. Возвращает
     * логическое значение (true, если элемент был удален).
     */
    private BiPredicate<String, MenuItem> areNamesEqual = (name, menuItem) -> name.equals(menuItem.getName());
    private BiPredicate<Object, MenuItem> areItemsEqual = Object::equals;
    private BiPredicate<Object, Integer> areObjectsEqual = (obj, i) -> obj.equals(items[i]);

    @Override
    public boolean remove(String itemName) {
        return remove(areNamesEqual, itemName);
    }

    @Override
    public boolean remove(Object object) {
        return remove(areItemsEqual, object);
    }

    private <T> boolean remove(BiPredicate<T, MenuItem> biPredicate, T object) {
        for (int i = 0; i < size; i++) {
            if (biPredicate.test(object, items[i])) {
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
    public int removeAll(String itemName) {
        return removeAll(areNamesEqual, itemName);
    }

    @Override
    public int removeAll(MenuItem item) {
        return removeAll(areItemsEqual, item);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return removeAll(containsInCollection, c) > 0;
    }

    private BiPredicate<Collection<?>, MenuItem> containsInCollection = Collection::contains;
    private BiPredicate<Collection<?>, MenuItem> notContainsInCollection = (c, item) -> !c.contains(item);


    private <T> int removeAll(BiPredicate<T, MenuItem> biPredicate, T object) {
        int removedItemsCount = 0;
        MenuItem[] newItems = new MenuItem[items.length];
        for (int i = 0; i < size; i++) {
            if (biPredicate.test(object, items[i])) {
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
        return itemQuantity(areNamesEqual, itemName);
    }

    @Override
    public int itemQuantity(MenuItem item) {
        return itemQuantity(areItemsEqual, item);
    }

    private <T> int itemQuantity(BiPredicate<T, MenuItem> biPredicate, T object) {
        int itemQuantity = 0;
        for (int i = 0; i < size; i++) {
            biPredicate.test(object, items[i]);
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
        return IntStream.range(0, count).anyMatch(i -> names[i].equals(names[count]));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TableOrder: ").append(size).append("\n");
        IntStream.range(0, size).forEach(i -> sb.append(items[i].toString()).append("\n"));
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return Order.super.isEqual(obj);
    }

    @Override
    public int hashCode() {
        return customer.hashCode() ^ Integer.hashCode(size) ^ dateTime.hashCode() ^ Arrays.deepHashCode(items);
    }

    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dateTime.toEpochSecond(ZoneOffset.UTC)).append("\n").append(customer.toFileString()).append(size).append("\n");
        IntStream.range(0, size).forEach(i -> sb.append(items[i].toFileString()));
        return sb.toString();
    }

    @Override
    public MenuItem get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        checkIndex(index);
        MenuItem oldItem = get(index);
        items[index] = element;
        return oldItem;
    }

    @Override
    public void add(int index, MenuItem element) throws UnlawfulActionException {
        checkIndex(index);
        checkLawless(element);
        if (size == items.length)
            increaseCapacity();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
    }

    @Override
    public MenuItem remove(int index) {
        checkIndex(index);
        MenuItem removedItem = items[index];
        shiftArray(index);
        size--;
        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        return IntStream.range(0, size).filter(i -> items[i].equals(o)).findFirst().orElse(-1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public ListIterator<MenuItem> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        return new ListIterator<MenuItem>() {
            int currentIndex = index;
            MenuItem lastReturned = null;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public MenuItem next() {
                if (hasNext()) {
                    lastReturned = items[currentIndex++];
                    return lastReturned;
                }
                throw new NoSuchElementException("There's no such element");
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public MenuItem previous() {
                if (hasPrevious()) {
                    lastReturned = items[currentIndex--];
                    return lastReturned;
                }
                throw new NoSuchElementException("There's no such element");
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
                if (lastReturned == null)
                    throw new IllegalStateException("You can't remove item");
                lastReturned = null;
                shiftArray(currentIndex);
            }

            @Override
            public void set(MenuItem item) {
                if (lastReturned == null)
                    throw new IllegalStateException("You can't set the value for item");
                items[currentIndex] = item;
            }

            @Override
            public void add(MenuItem item) {
                lastReturned = null;
                TableOrder.this.add(currentIndex, item);
            }
        };
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex, toIndex);
        return Arrays.stream(items, fromIndex, toIndex).collect(Collectors.toCollection(TableOrder::new));
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
}
