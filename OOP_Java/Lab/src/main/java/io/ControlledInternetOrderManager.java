package io;

import barBossHouse.AlreadyAddedException;
import barBossHouse.InternetOrdersManager;
import barBossHouse.Order;
import factory.OrdersFactory;

import java.io.IOException;
import java.util.Collection;

public class ControlledInternetOrderManager extends InternetOrdersManager {
    protected Source<Order> source;
    private OrdersFactory factory;

    public ControlledInternetOrderManager(Source<Order> source, OrdersFactory factory) {
        super();
        this.source = source;
        this.factory = factory;
    }

    public ControlledInternetOrderManager(Order[] orders, Source<Order> source, OrdersFactory factory) throws AlreadyAddedException {
        super(orders);
        this.source = source;
        this.factory = factory;
    }

    private ControlledInternetOrder getControlledOrder(Order order) {
        return (ControlledInternetOrder) factory.createInternetOrder(order);
    }

    private void create(ControlledInternetOrder internetOrder) {
        try {
            source.create(internetOrder);
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
    public void addFirst(Order order) {
        create(getControlledOrder(order));
        super.addFirst(order);
    }

    @Override
    public void addLast(Order order) {
        create(getControlledOrder(order));
        super.addLast(order);
    }

    @Override
    public boolean offerFirst(Order order) {
        create(getControlledOrder(order));
        return super.offerFirst(order);
    }

    @Override
    public boolean offerLast(Order order) {
        create(getControlledOrder(order));
        return super.offerLast(order);
    }

    @Override
    public Order removeFirst() {
        create(getControlledOrder(super.getFirst()));
        return super.removeFirst();
    }

    @Override
    public Order removeLast() {
        create(getControlledOrder(super.getLast()));
        return super.removeLast();
    }

    @Override
    public Order pollFirst() {
        delete(super.getFirst());
        return super.pollFirst();
    }

    @Override
    public Order pollLast() {
        delete(super.getLast());
        return super.pollLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        delete((Order) o);
        return super.removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        delete((Order) o);
        return super.removeLastOccurrence(o);
    }

    @Override
    public boolean add(Order order) {
        create(getControlledOrder(order));
        return super.add(order);
    }

    @Override
    public boolean remove(Object o) {
        delete((Order) o);
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        c.forEach(order -> create(getControlledOrder(order)));
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(obj -> delete((Order) obj));
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        this.stream().filter(order -> !c.contains(order)).forEach(this::delete);
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        this.forEach(this::delete);
        super.clear();
    }

    @Override
    public boolean offer(Order order) {
        create(getControlledOrder(order));
        return super.offer(order);
    }

    @Override
    public Order remove() {
        delete(getFirst());
        return super.remove();
    }

    @Override
    public Order poll() {
        delete(getFirst());
        return super.poll();
    }

    @Override
    public void push(Order order) {
        create(getControlledOrder(order));
        super.push(order);
    }

    @Override
    public Order pop() {
        delete(super.getFirst());
        return super.pop();
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
}
