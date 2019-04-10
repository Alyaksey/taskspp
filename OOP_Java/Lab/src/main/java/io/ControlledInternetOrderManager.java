package io;

import barBossHouse.AlreadyAddedException;
import barBossHouse.InternetOrdersManager;
import barBossHouse.Order;
import factory.OrdersFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

//todo при исключениях надобы откатить действия, чтобы данные в FS и списке были консистентными+
public class ControlledInternetOrderManager extends InternetOrdersManager {
    private Source<Order> source;
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

    private void createControlledOrder(Order order) {
        ControlledInternetOrder internetOrder = (ControlledInternetOrder) factory.createInternetOrder(order);
        try {
            source.create(internetOrder);
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
    public void addFirst(Order order) throws AlreadyAddedException {
        super.addFirst(order);
        createControlledOrder(order);
    }

    @Override
    public void addLast(Order order) throws AlreadyAddedException {
        super.addLast(order);
        createControlledOrder(order);
    }

    @Override
    public boolean offerFirst(Order order) throws AlreadyAddedException {
        boolean isOffered = super.offerFirst(order);
        createControlledOrder(order);
        return isOffered;
    }

    @Override
    public boolean offerLast(Order order) throws AlreadyAddedException {
        boolean isOffered = super.offerLast(order);
        createControlledOrder(order);
        return isOffered;
    }

    @Override
    public Order removeFirst() {
        Order removedOrder = super.removeFirst();
        if (Objects.nonNull(removedOrder))
            delete(super.getFirst());
        return removedOrder;
    }

    @Override
    public Order removeLast() {
        Order removedOrder = super.removeLast();
        if (Objects.nonNull(removedOrder))
            delete(super.getLast());
        return super.removeLast();
    }

    @Override
    public Order pollFirst() {
        Order polledOrder = super.pollFirst();
        if (Objects.nonNull(polledOrder))
            delete(super.getFirst());
        return polledOrder;
    }

    @Override
    public Order pollLast() {
        Order polledOrder = super.pollLast();
        if (Objects.nonNull(polledOrder))
            delete(super.getLast());
        return polledOrder;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        boolean isRemoved = super.removeFirstOccurrence(o);
        if (isRemoved)
            delete((Order) o);
        return isRemoved;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        boolean isRemoved = super.removeLastOccurrence(o);
        if (isRemoved)
            delete((Order) o);
        return isRemoved;
    }

    @Override
    public boolean add(Order order) throws AlreadyAddedException {
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
    public boolean addAll(Collection<? extends Order> c) throws AlreadyAddedException {
        try {
            boolean areAdded = super.addAll(c);
            c.forEach(this::createControlledOrder);
            return areAdded;
        } catch (AlreadyAddedException e) {
            super.removeAll(c);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean areRemoved = super.removeAll(c);
        c.forEach(obj -> delete((Order) obj));
        return areRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean areRetained = super.retainAll(c);
        this.stream().filter(order -> !c.contains(order)).forEach(this::delete);
        return areRetained;
    }

    @Override
    public void clear() {
        super.clear();
        this.forEach(this::delete);
    }

    @Override
    public boolean offer(Order order) throws AlreadyAddedException {
        boolean isOffered = super.offer(order);
        createControlledOrder(order);
        return isOffered;
    }

    @Override
    public Order remove() {
        Order removedOrder = super.remove();
        if (Objects.nonNull(removedOrder))
            delete(getFirst());
        return removedOrder;
    }

    @Override
    public Order poll() {
        Order polledOrder = super.poll();
        if (Objects.nonNull(polledOrder))
            delete(getFirst());
        return polledOrder;
    }

    @Override
    public void push(Order order) throws AlreadyAddedException {
        super.push(order);
        createControlledOrder(order);
    }

    @Override
    public Order pop() {
        Order poppedOrder = super.pop();
        if (Objects.nonNull(poppedOrder))
            delete(super.getFirst());
        return poppedOrder;
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
