package factory;

import barBossHouse.*;

public abstract class OrdersFactory {
    public abstract Order createTableOrder();

    public abstract Order createTableOrder(int capacity, Customer customer);

    public abstract Order createTableOrder(MenuItem[] items, Customer customer);

    public abstract Order createTableOrder(Order order);

    public abstract Order createInternetOrder();

    public abstract Order createInternetOrder(Order order);

    public abstract Order createInternetOrder(MenuItem[] items, Customer customer);

    public abstract OrdersManager createTableOrdersManager(int tablesCount);

    public abstract OrdersManager createInternetOrdersManager(Order[] orders) throws AlreadyAddedException;

    public abstract OrdersManager createInternetOrderManager();

    static public OrdersFactory getOrdersFactory(OrdersFactoryTypesEnumeration type, String path) {
        switch (type) {
            case ORDINARY_ORDERS_FACTORY:
                return new OrdinaryOrdersFactory();
            case TEXT_FILE_BASED_ORDERS_FACTORY:
                return new TextFileBasedOrdersFactory(path);
            case BINARY_FILE_BASED_ORDERS_FACTORY:
                return new BinaryFileBasedOrdersFactory(path);
            case SERIALIZED_FILE_BASED_ORDERS_FACTORY:
                return new SerializedBasedOrdersFactory(path);
            case SOCKET_BASED_ORDERS_FACTORY:
                return null;
        }
        return null;
    }
}
