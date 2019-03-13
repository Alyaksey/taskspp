package barBossHouse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TableOrdersManager implements OrdersManager {
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
     * Метод добавления заказа столику. В качестве параметров принимает номер столика и ссылку на
     * заказ.
     */
    public void add(Order order, int tableNumber) throws AlreadyAddedException {
        if (Objects.nonNull(orders[tableNumber]))
            throw new AlreadyAddedException("This table is busy");
        orders[tableNumber] = order;
    }

    /**
     * Метод получения заказа столика по его номеру. В качестве параметра принимает номер столика.
     */
    public Order getOrder(int tableNumber) {
        return orders[tableNumber];
    }

    /**
     * Метод добавления блюда к заказу. В качестве параметра принимает номер столика и блюдо.
     */
    public void addItem(MenuItem item, int tableNumber) {
        orders[tableNumber].add(item);
    }

    /**
     * Метод освобождения столика (по сути устанавливает значение соответствующего элемента
     * массива в null). В качестве параметра принимает номер столика.
     */
    public void remove(int tableNumber) {
        orders[tableNumber] = null;
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

    public int removeAll(Order order) {
        int removedOrdersCount = 0;
        for (int i = 0; i < orders.length; i++) {
            if (order.equals(orders[i])) {
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
     * Метод, возвращающий массив имеющихся на данный момент заказов
     */
    public Order[] getOrders() {
        Order[] tableOrders = new TableOrder[tablesCount(Objects::nonNull)];
        int j = 0;
        for (int i = 0; i < this.orders.length; i++) {
            if (this.orders[i] != null)
                tableOrders[j++] = this.orders[i];
        }
        return tableOrders;
    }

    /**
     * Метод, возвращающий суммарную стоимость имеющихся на данный момент заказов.
     */
    public int ordersCostSummary() {
        int orderCostSummary = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null)
                orderCostSummary += orders[i].costTotal();
        }
        return orderCostSummary;
    }

    /**
     * Метод, возвращающий общее среди всех занятых столиков количество заказанных порций
     * заданного блюда по его имени. Принимает имя блюда в качестве параметра.
     */
    public int itemsQuantity(String itemName) {
        return itemsQuantity(order -> order.itemQuantity(itemName));
    }

    public int itemsQuantity(MenuItem item) {
        return itemsQuantity(order -> order.itemQuantity(item));
    }

    private int itemsQuantity(Function<Order, Integer> function) {
        int itemsQuantity = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null)
                itemsQuantity += function.apply(orders[i]);
        }
        return itemsQuantity;
    }

    public int ordersQuantity() {
        return getOrders().length;
    }

    @Override
    public int ordersQuantity(LocalDate localDate) {
        return (int)Arrays.stream(orders)
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
        return Arrays.stream(orders).filter(Objects::nonNull)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private int tablesCount(Predicate<Order> predicate) {
        int tablesCount = 0;
        for (int i = 0; i < orders.length; i++) {
            if (predicate.test(orders[i]))
                tablesCount++;
        }
        return tablesCount;
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
}
