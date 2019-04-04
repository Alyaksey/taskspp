package io;

import barBossHouse.Order;
import barBossHouse.TableOrdersManager;
import factory.OrdersFactory;

import java.io.IOException;
import java.util.Collection;

//todo при исключениях надобы откатить действия, чтобы данные в FS и списке были консистентными
//todo метод добадвения menuitem к ордеру (там надо store() делать)
public class ControlledTableOrderManager extends TableOrdersManager {
    protected Source<Order> source;
    private OrdersFactory factory;

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
            ex.printStackTrace();
        }
    }

    private void delete(Order order) {
        try {
            source.delete(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        c.forEach(this::createControlledOrder);
        return super.addAll(index, c);
    }

    @Override
    public void add(int index, Order element) {
        createControlledOrder(element);
        super.add(index, element);
    }

    @Override
    public Order remove(int index) {
        delete(super.get(index));
        return super.remove(index);
    }

    @Override
    public boolean add(Order order) {
        createControlledOrder(order);
        return super.add(order);
    }

    @Override
    public boolean remove(Object o) {
        delete((Order) o);
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        c.forEach(this::createControlledOrder);
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(object -> delete((Order) object));
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        this.stream().filter(order -> !c.contains(order)).forEach(this::delete);
        return super.retainAll(c);
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
    public int remove(Order order) throws IOException {
        source.delete(order);
        return super.remove(order);
    }

    @Override
    public int removeAll(Order order) throws IOException {
        source.delete(order);
        return super.removeAll(order);
    }

    @Override
    public void clear() {
        this.forEach(this::delete);
        super.clear();
    }
}
