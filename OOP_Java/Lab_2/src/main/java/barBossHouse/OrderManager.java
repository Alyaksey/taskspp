package barBossHouse;

import java.util.Objects;
import java.util.function.Predicate;

public class OrderManager {
    private Order[] orders;

    /**
     * Конструктор, принимающий один параметр – число столиков, инициализирующий массив
     * соответствующим числом элементов.
     */
    public OrderManager(int tableCount) {
        orders = new Order[tableCount];
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
    public void addDish(Dish dish, int tableNumber) {
        orders[tableNumber].add(dish);
    }

    /**
     * Метод освобождения столика (по сути устанавливает значение соответствующего элемента
     * массива в null). В качестве параметра принимает номер столика.
     */
    public void removeOrder(int tableNumber) {
        orders[tableNumber] = null;
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
        Order[] orders = new Order[tablesCount(Objects::nonNull)];
        int j = 0;
        for (int i = 0; i < this.orders.length; i++) {
            if (this.orders[i] != null)
                orders[j++] = this.orders[i];
        }
        return orders;
    }

    /**
     * Метод, возвращающий суммарную стоимость имеющихся на данный момент заказов.
     */
    public double orderCostSummary() {
        double cost = 0.0;
        Order[] orders = getOrders();
        for (int i = 0; i < orders.length; i++) {
                cost += orders[i].costTotal();
        }
        return cost;
    }

    /**
     * Метод, возвращающий общее среди всех занятых столиков количество заказанных порций
     * заданного блюда по его имени. Принимает имя блюда в качестве параметра.
     */
    public int dishQuantity(String dishName) {
        int count = 0;
        Order[] orders = getOrders();
        for (int i = 0; i < orders.length; i++) {
            count += orders[i].dishQuantity(dishName);
        }
        return count;
    }

    private int tablesCount(Predicate<Order> predicate) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (predicate.test(orders[i]))
                count++;
        }
        return count;
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
