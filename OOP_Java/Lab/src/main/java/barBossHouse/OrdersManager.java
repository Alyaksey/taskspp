package barBossHouse;

import java.time.LocalDate;
import java.util.List;

public interface OrdersManager {
    int itemsQuantity(String itemName);
    int itemsQuantity(MenuItem item);
    Order[] getOrders();
    int ordersCostSummary();
    int ordersQuantity();
    int ordersQuantity(LocalDate localDate);
    List<Order> getOrders(LocalDate localDate);
    List<Order> getOrders(Customer customer);
}
