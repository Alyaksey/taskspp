package io;

import barBossHouse.*;

import java.time.LocalDateTime;
import java.util.Collection;

public class ControlledInternetOrder extends InternetOrder {
    protected boolean isChanged;
    private static final boolean DEFAULT_STATE = false;

    public ControlledInternetOrder(MenuItem[] items, Customer customer) {
        super(items, customer);
        isChanged = DEFAULT_STATE;
    }

    public ControlledInternetOrder() {
        super();
        isChanged = DEFAULT_STATE;
    }

    public ControlledInternetOrder(Order order) {
        this((MenuItem[]) order.toArray(), order.getCustomer());
        this.setDateTime(order.getDateTime());
        isChanged = DEFAULT_STATE;
    }

    @Override
    public boolean add(MenuItem item) throws UnlawfulActionException {
        isChanged = super.add(item);
        return isChanged;
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        c.forEach(item -> add(index, item));
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (super.retainAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object object) {
        if (super.remove(object)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public int removeAll(String itemName) {
        int removedItemsCount = super.removeAll(itemName);
        if (removedItemsCount != 0)
            isChanged = true;
        return removedItemsCount;
    }

    @Override
    public int removeAll(MenuItem item) {
        int removedItemsCount = super.removeAll(item);
        if (removedItemsCount != 0)
            isChanged = true;
        return removedItemsCount;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (super.removeAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public void setCustomer(Customer customer) {
        super.setCustomer(customer);
        isChanged = true;
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        MenuItem item = super.set(index, element);
        isChanged = true;
        return item;
    }

    @Override
    public void add(int index, MenuItem element) throws UnlawfulActionException {
        super.add(index, element);
        isChanged = true;
    }

    @Override
    public MenuItem remove(int index) {
        MenuItem removedItem = super.remove(index);
        isChanged = true;
        return removedItem;
    }

    @Override
    public void setDateTime(LocalDateTime time) {
        super.setDateTime(time);
        isChanged = true;
    }

    @Override
    public void clear() {
        super.clear();
        isChanged = true;
    }
}
