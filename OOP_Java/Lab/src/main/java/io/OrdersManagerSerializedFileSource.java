package io;

import barBossHouse.MenuItem;
import barBossHouse.Order;
import barBossHouse.OrdersManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.util.Arrays;

public class OrdersManagerSerializedFileSource extends OrderManagerFileSource {
    private static final String EXTENSION = ".ser";

    public OrdersManagerSerializedFileSource(String path) {
        setPath(path);
    }

    @Override
    public void load(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getDateTime().toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        Order loadedOrder;
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
            loadedOrder = (Order) inputStream.readObject();
            inputStream.close();
            order = loadedOrder;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void store(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getDateTime().toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path));
        outputStream.writeObject(order);
        outputStream.close();
    }

    @Override
    public void delete(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getDateTime().toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        Files.delete(path);
    }

    @Override
    public void create(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getDateTime().toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        Files.createFile(path);
        store(order);
    }
}
