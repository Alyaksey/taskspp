package barBossHouse;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface OrdersManager extends Collection<Order> {
    int itemsQuantity(String itemName);

    int itemsQuantity(MenuItem item);

    int ordersCostSummary();

    int ordersQuantity(LocalDate localDate);

    List<Order> getOrders(LocalDate localDate);

    List<Order> getOrders(Customer customer);
}
