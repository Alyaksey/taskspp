package factory;

import barBossHouse.*;
import io.*;

import java.util.Objects;

public class SerializedBasedOrdersFactory extends OrdersFactory {
    private OrdersManagerSerializedFileSource source;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private void checkPath() {
        Objects.requireNonNull(path, "You must set the path");
    }

    public SerializedBasedOrdersFactory(String path) {
        this.path = path;
        source = new OrdersManagerSerializedFileSource(path);
    }

    @Override
    public Order createTableOrder() {
        checkPath();
        return new ControlledTableOrder();
    }

    @Override
    public Order createTableOrder(int capacity, Customer customer) {
        checkPath();
        return new ControlledTableOrder(capacity, customer);
    }

    @Override
    public Order createTableOrder(MenuItem[] items, Customer customer) {
        checkPath();
        return new ControlledTableOrder(items, customer);
    }

    @Override
    public Order createTableOrder(Order order) {
        checkPath();
        return new ControlledTableOrder(order);
    }

    @Override
    public Order createInternetOrder() {
        checkPath();
        return new ControlledInternetOrder();
    }

    @Override
    public Order createInternetOrder(Order order) {
        checkPath();
        return new ControlledInternetOrder(order);
    }

    @Override
    public Order createInternetOrder(MenuItem[] items, Customer customer) {
        checkPath();
        return new ControlledInternetOrder(items, customer);
    }

    @Override
    public OrdersManager createTableOrdersManager(int tablesCount) {
        checkPath();
        return new ControlledTableOrderManager(tablesCount, source, this);
    }

    @Override
    public OrdersManager createInternetOrdersManager(Order[] orders) throws AlreadyAddedException {
        checkPath();
        return new ControlledInternetOrderManager(orders, source, this);
    }

    @Override
    public OrdersManager createInternetOrderManager() {
        checkPath();
        return new ControlledInternetOrderManager(source, this);
    }
}
