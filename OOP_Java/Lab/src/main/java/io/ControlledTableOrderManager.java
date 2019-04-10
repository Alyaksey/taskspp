package io;

import barBossHouse.*;
import factory.OrdersFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

//todo при исключениях надобы откатить действия, чтобы данные в FS и списке были консистентными+
//todo метод добадвения menuitem к ордеру (там надо store() делать)+
public class ControlledTableOrderManager extends TableOrdersManager {
    protected Source<Order> source;
    protected OrdersFactory factory;

    public ControlledTableOrderManager(int tableCount, Source<Order> source, OrdersFactory factory) {
        super(tableCount);
        this.source = source;
        this.factory = factory;
    }

    public Source<Order> getSource() {
        return source;
    }

    public void setSource(Source<Order> source) {
        this.source = source;
    }


    private void createControlledOrder(Order order) {
        ControlledTableOrder tableOrder = (ControlledTableOrder) factory.createTableOrder(order);
        try {
            source.create(tableOrder);
        } catch (IOException ex) {
            remove(order);
        }
    }

    private void delete(Order order) {
        try {
            source.delete(order);
        } catch (IOException e) {
            add(order);
        }
    }

    @Override
    public void addItem(MenuItem item, int tableNumber) {
        super.addItem(item, tableNumber);
        store();
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) throws NoFreeTableException {
        boolean areAdded = false;
        try {
            areAdded = super.addAll(index, c);
            c.forEach(this::createControlledOrder);
        } catch (NoFreeTableException e) {
            super.removeAll(c);
        }
        return areAdded;
    }

    @Override
    public void add(int index, Order element) throws AlreadyAddedException {
        super.add(index, element);
        createControlledOrder(element);
    }

    @Override
    public Order remove(int index) {
        Order removedOrder = super.remove(index);
        if (Objects.nonNull(removedOrder))
            delete(super.get(index));
        return removedOrder;
    }

    @Override
    public boolean add(Order order) throws NoFreeTableException {
        boolean isAdded = super.add(order);
        createControlledOrder(order);
        return isAdded;
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved = super.remove(o);
        if (isRemoved)
            delete((Order) o);
        return isRemoved;
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) throws NoFreeTableException {
        try {
            boolean areAdded = super.addAll(c);
            c.forEach(this::createControlledOrder);
            return areAdded;
        } catch (NoFreeTableException e) {
            super.removeAll(c);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean areRemoved = super.removeAll(c);
        c.forEach(object -> delete((Order) object));
        return areRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean areRetained = super.retainAll(c);
        this.stream().filter(order -> !c.contains(order)).forEach(this::delete);
        return areRetained;
    }

    public void store() {
        this.stream().filter(order -> ((ControlledTableOrder) order).isChanged).forEach(order -> {
            try {
                source.store(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void load() {
        this.forEach(order -> {
            try {
                source.load(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int remove(Order order) {
        int removedOrders = super.remove(order);
        try {
            if (removedOrders > 0)
                source.delete(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removedOrders;
    }

    @Override
    public int removeAll(Order order) throws IOException {
        int removedOrders = super.removeAll(order);
        if (removedOrders > 0)
            source.delete(order);
        return removedOrders;
    }

    @Override
    public void clear() {
        super.clear();
        this.forEach(this::delete);
    }
}
