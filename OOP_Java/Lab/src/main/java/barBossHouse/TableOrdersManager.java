package barBossHouse;

import java.util.Objects;
import java.util.function.Predicate;

public class TableOrdersManager {
    private Order[] orders;

    /**
     * Конструктор, принимающий один параметр – число столиков, инициализирующий массив
     * соответствующим числом элементов.
     */
    public TableOrdersManager(int tableCount) {
        orders = new TableOrder[tableCount];
    }

    /**
     * Метод добавления заказа столику. В качестве параметров принимает номер столика и ссылку на
     * заказ.
     */
    public void add(Order order, int tableNumber) {
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
                orders[i] = null;
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
    public int freeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null)
                return i;
        }
        return -1;
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
    public double orderCostSummary() {
        double orderCostSummary = 0.0;
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
        int itemsQuantity = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null)
                itemsQuantity += orders[i].itemQuantity(itemName);
        }
        return itemsQuantity;
    }

    public int itemsQuantity(MenuItem item) {
        int itemsQuantity = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null)
                itemsQuantity += orders[i].itemQuantity(item);
        }
        return itemsQuantity;
    }

    public int ordersQuantity() {
        return tablesCount(Objects::nonNull);
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
