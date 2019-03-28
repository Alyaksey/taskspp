package barBossHouse;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TableOrdersManager implements OrdersManager, List<Order> {
    private Order[] orders;

    /**
     * Конструктор, принимающий один параметр – число столиков, инициализирующий массив
     * соответствующим числом элементов.
     */
    public TableOrdersManager(int tableCount) {
        if (tableCount < 0)
            throw new NegativeSizeException("Array's capacity cannot be negative");
        orders = new TableOrder[tableCount];
    }

    /**
     * Метод добавления блюда к заказу. В качестве параметра принимает номер столика и блюдо.
     */
    public void addItem(MenuItem item, int tableNumber) {
        orders[tableNumber].add(item);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        checkIndex(index);
        if (c.size() + index > tablesCount(Objects::isNull)) {
            try {
                throw new NoFreeTableException("There are not enough of free tables");
            } catch (NoFreeTableException e) {
                e.printStackTrace();
            }
        }
        int[] freeTableNumbers = freeTableNumbers();
        int i = 0;
        while (freeTableNumbers[i] < index) {
            i++;
        }
        for (Order o : c) {
            orders[freeTableNumbers[i++]] = o;
        }
        return true;
    }

    @Override
    public Order get(int index) {
        checkIndex(index);
        if (Objects.isNull(orders[index]))
            throw new NullPointerException("There's no order to get");
        return orders[index];
    }

    @Override
    public Order set(int index, Order element) {
        checkIndex(index);
        Order replacedOrder = orders[index];
        orders[index] = element;
        return replacedOrder;
    }

    @Override
    public void add(int index, Order element) {
        checkIndex(index);
        if (Objects.nonNull(orders[index])) {
            try {
                throw new AlreadyAddedException("This table is busy");
            } catch (AlreadyAddedException e) {
                e.printStackTrace();
            }
        }
        orders[index] = element;
    }

    @Override
    public Order remove(int index) {
        checkIndex(index);
        if (Objects.isNull(orders[index]))
            throw new NullPointerException("This table is already free");
        Order removedItem = orders[index];
        orders[index] = null;
        return removedItem;
    }


    @Override
    public int indexOf(Object o) {
        return IntStream.range(0, orders.length)
                .filter(i -> Objects.nonNull(orders[i]))
                .filter(i -> orders[i].equals(o))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = orders.length; i >= 0; i--) {
            if (Objects.nonNull(orders[i]))
                if (orders[i].equals(o))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator<Order> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<Order> listIterator(int index) {
        return new ListIterator<Order>() {
            int currentIndex = index;
            Order lastReturned = null;

            @Override
            public boolean hasNext() {
                return currentIndex < orders.length;
            }

            @Override
            public Order next() {
                if (hasNext()) {
                    lastReturned = orders[currentIndex++];
                    return lastReturned;
                }
                throw new NoSuchElementException("There's no such element");
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public Order previous() {
                if (hasPrevious()) {
                    lastReturned = orders[currentIndex--];
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
                TableOrdersManager.this.remove(currentIndex);
            }

            @Override
            public void set(Order order) {
                if (lastReturned == null)
                    throw new IllegalStateException("You can't set the value for item");
                TableOrdersManager.this.set(currentIndex, order);
            }

            @Override
            public void add(Order order) {
                lastReturned = null;
                TableOrdersManager.this.add(currentIndex, order);
            }
        };
    }

    @Override
    public List<Order> subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex, toIndex);
        List<Order> subList = new TableOrdersManager(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            if (Objects.nonNull(orders[i]))
                subList.add(orders[i]);
        }
        return subList;
    }

    public int remove(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (order.equals(orders[i])) {
                remove(i);
                return i;
            }
        }
        return -1;
    }

    private BiPredicate<Object, Order> areEquals = Object::equals;

    public int removeAll(Order order) {
        return removeAll(areEquals, order);
    }

    private <T> int removeAll(BiPredicate<T, Order> biPredicate, T obj) {
        int removedOrdersCount = 0;
        for (int i = 0; i < orders.length; i++) {
            if (biPredicate.test(obj, orders[i])) {
                remove(i);
                removedOrdersCount++;
            }
        }
        if (removedOrdersCount > 0) return removedOrdersCount;
        return -1;
    }

    /**
     * Метод, возвращающий номер первого найденного свободного столика
     */
    public int freeTableNumber() throws NoFreeTableException {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null)
                return i;
        }
        throw new NoFreeTableException("There are no free tables");
    }

    /**
     * Метод, возвращающий массив номеров свободных столиков.
     */
    public int[] freeTableNumbers() {
        return getTableNumbers(Objects::isNull);
    }

    /**
     * Метод, возвращающий массив номеров занятых столиков.
     */
    public int[] busyTableNumbers() {
        return getTableNumbers(Objects::nonNull);
    }


    /**
     * Метод, возвращающий суммарную стоимость имеющихся на данный момент заказов.
     */
    @Override
    public int ordersCostSummary() {
        return Arrays.stream(orders).filter(Objects::nonNull).mapToInt(order -> (int) order.costTotal()).sum();
    }

    /**
     * Метод, возвращающий общее среди всех занятых столиков количество заказанных порций
     * заданного блюда по его имени. Принимает имя блюда в качестве параметра.
     */
    @Override
    public int itemsQuantity(String itemName) {
        return itemsQuantity(order -> order.itemQuantity(itemName));
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        return itemsQuantity(order -> order.itemQuantity(item));
    }

    private int itemsQuantity(Function<Order, Integer> function) {
        return Arrays.stream(orders).filter(Objects::nonNull).mapToInt(function::apply).sum();
    }


    @Override
    public int ordersQuantity(LocalDate localDate) {
        return (int) Arrays.stream(orders)
                .filter(Objects::nonNull)
                .filter(o -> localDate.equals(o.getDateTime().toLocalDate()))
                .count();
    }

    @Override
    public List<Order> getOrders(LocalDate localDate) {
        return getOrders(o -> localDate.equals(o.getDateTime().toLocalDate()));
    }

    @Override
    public List<Order> getOrders(Customer customer) {
        return getOrders(o -> customer.equals(o.getCustomer()));
    }

    private List<Order> getOrders(Predicate<Order> predicate) {
        return Arrays.stream(orders)
                .filter(Objects::nonNull)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private int tablesCount(Predicate<Order> predicate) {
        return (int) Arrays.stream(orders).filter(predicate::test).count();
    }

    private int[] getTableNumbers(Predicate<Order> predicate) {
        int count = tablesCount(predicate);
        int[] tableNumbers = new int[count];
        int j = 0;
        for (int i = 0; i < orders.length; i++) {
            if (predicate.test(orders[i])) {
                tableNumbers[j++] = i;
            }
        }
        return tableNumbers;
    }

    @Override
    public int size() {
        return tablesCount(Objects::nonNull);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.stream(orders).filter(Objects::nonNull).anyMatch(order -> order.equals(o));
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < orders.length;
            }

            @Override
            public Order next() {
                if (hasNext()) {
                    return orders[currentIndex++];
                }
                throw new NoSuchElementException("There's no such element");
            }
        };
    }

    @Override
    public Object[] toArray() {
        Order[] tableOrders = new TableOrder[tablesCount(Objects::nonNull)];
        int j = 0;
        for (Order order : this.orders) {
            if (order != null)
                tableOrders[j++] = order;
        }
        return tableOrders;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < orders.length)
            return (T[]) Arrays.copyOf(orders, orders.length - 1, a.getClass());
        System.arraycopy(orders, 0, a, 0, orders.length);
        if (a.length > orders.length)
            a[orders.length - 1] = null;
        return a;
    }

    @Override
    public boolean add(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (Objects.isNull(orders[i])) {
                orders[i] = order;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < orders.length; i++) {
            if (Objects.nonNull(orders[i])) {
                orders[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        if (c.size() > tablesCount(Objects::isNull)) {
            try {
                throw new NoFreeTableException("There are not enough of free tables");
            } catch (NoFreeTableException e) {
                e.printStackTrace();
            }
        }
        int i = 0;
        int[] freeTableNumbers = freeTableNumbers();
        for (Order o : c) {
            orders[freeTableNumbers[i++]] = o;
        }
        return true;
    }

    private BiPredicate<Collection<?>, Order> containsInCollection = Collection::contains;
    private BiPredicate<Collection<?>, Order> notContainsInCollection = (c, o) -> !c.contains(o);

    @Override
    public boolean removeAll(Collection<?> c) {
        return removeAll(containsInCollection, c) > 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeAll(notContainsInCollection, c) > 0;
    }

    @Override
    public void clear() {
        IntStream.range(0, orders.length).forEach(i -> orders[i] = null);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= orders.length)
            throw new IndexOutOfBoundsException("Index is out of bounds");
    }

    private void checkBounds(int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("Wrong arguments");
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("Index is out of bounds;");
        if (toIndex > orders.length)
            throw new IndexOutOfBoundsException("Index is out of bounds");
    }
}
