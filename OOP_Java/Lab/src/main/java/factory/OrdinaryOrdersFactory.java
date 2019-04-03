package factory;

import barBossHouse.*;

public class OrdinaryOrdersFactory extends OrdersFactory {
    @Override
    public Order createTableOrder() {
        return new TableOrder();
    }

    @Override
    public Order createTableOrder(int capacity, Customer customer) {
        return new TableOrder(capacity, customer);
    }

    @Override
    public Order createTableOrder(MenuItem[] items, Customer customer) {
        return new TableOrder(items, customer);
    }

    @Override
    public Order createTableOrder(Order order) {
        return createTableOrder((MenuItem[]) order.toArray(),order.getCustomer());
    }


    @Override
    public Order createInternetOrder() {
        return new InternetOrder();
    }

    @Override
    public Order createInternetOrder(Order order) {
        return createInternetOrder((MenuItem[]) order.toArray(),order.getCustomer());
    }

    @Override
    public Order createInternetOrder(MenuItem[] items, Customer customer) {
        return new InternetOrder(items, customer);
    }


    @Override
    public OrdersManager createTableOrdersManager(int tablesCount) {
        return new TableOrdersManager(tablesCount);
    }

    @Override
    public OrdersManager createInternetOrdersManager(Order[] orders) throws AlreadyAddedException {
        return new InternetOrdersManager(orders);
    }

    @Override
    public OrdersManager createInternetOrderManager() {
        return new InternetOrdersManager();
    }
}
