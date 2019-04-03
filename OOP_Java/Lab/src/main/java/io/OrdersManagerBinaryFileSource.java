package io;

import barBossHouse.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

public class OrdersManagerBinaryFileSource extends OrderManagerFileSource {
    private static String EXTENSION = ".bin";

    public OrdersManagerBinaryFileSource(String path) {
        setPath(path);
    }

    @Override
    public void load(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getDateTime().toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(Files.newInputStream(path)));
        Customer customer = loadCustomer(inputStream);
        LocalDateTime orderTime = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
        MenuItem[] items = loadMenuItems(inputStream);
        inputStream.close();
        order.clear();
        order.setCustomer(customer);
        order.setDateTime(orderTime);
        order.addAll(Arrays.asList(items));
    }

    private MenuItem[] loadMenuItems(DataInputStream inputStream) throws IOException {
        int size = inputStream.readInt();
        MenuItem[] items = new MenuItem[size];
        String name, description, drinkType;
        int cost;
        double alcoholVol;
        for (int i = 0; i < items.length; i++) {
            name = inputStream.readUTF();
            description = inputStream.readUTF();
            cost = inputStream.readInt();
            if (inputStream.readUTF().equals("Drink")) {
                alcoholVol = inputStream.readDouble();
                drinkType = inputStream.readUTF();
                items[i] = new Drink(cost, name, description, alcoholVol, DrinkTypeEnum.valueOf(drinkType));
            } else
                items[i] = new Dish(cost, description, name);
        }
        return items;
    }

    private Customer loadCustomer(DataInputStream inputStream) throws IOException {
        String firstName = inputStream.readUTF();
        String lastName = inputStream.readUTF();
        LocalDate birthDate = LocalDate.ofEpochDay(inputStream.readLong());
        Address address = loadAddress(inputStream);
        return new Customer(firstName, lastName, birthDate, address);
    }

    private Address loadAddress(DataInputStream inputStream) throws IOException {
        String cityName = inputStream.readUTF();
        int zipCode = inputStream.readInt();
        String streetName = inputStream.readUTF();
        int buildingNumber = inputStream.readInt();
        String buildingLetter = inputStream.readUTF();
        int apartmentNumber = inputStream.readInt();
        return new Address(cityName, zipCode, streetName, buildingNumber, buildingLetter, apartmentNumber);
    }

    @Override
    public void store(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getDateTime().toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(path)));
        writeCustomer(outputStream, order);
        outputStream.writeLong(order.getDateTime().toEpochSecond(ZoneOffset.UTC));
        writeMenuItems(outputStream, order);
        outputStream.close();
    }

    private void writeMenuItems(DataOutputStream outputStream, Order order) throws IOException {
        outputStream.writeInt(order.size());
        for (MenuItem item : order) {
            outputStream.writeUTF(item.getName());
            outputStream.writeUTF(item.getDescription());
            outputStream.writeDouble(item.getCost());
            if (item instanceof Drink) {
                outputStream.writeUTF("Drink");
                outputStream.writeDouble(((Drink) item).getAlcoholVol());
                outputStream.writeUTF(((Drink) item).getType().toString());
            } else
                outputStream.writeUTF("Dish");
        }
    }

    private void writeCustomer(DataOutputStream outputStream, Order order) throws IOException {
        outputStream.writeUTF(order.getCustomer().getFirstName());
        outputStream.writeUTF(order.getCustomer().getSecondName());
        outputStream.writeLong(order.getCustomer().getBirthDate().toEpochDay());
        writeAddress(outputStream, order);
    }

    private void writeAddress(DataOutputStream outputStream, Order order) throws IOException {
        outputStream.writeUTF(order.getCustomer().getAddress().getCityName());
        outputStream.writeInt(order.getCustomer().getAddress().getZipCode());
        outputStream.writeUTF(order.getCustomer().getAddress().getStreetName());
        outputStream.writeInt(order.getCustomer().getAddress().getBuildingNumber());
        outputStream.writeUTF(order.getCustomer().getAddress().getBuildingLetter());
        outputStream.writeInt(order.getCustomer().getAddress().getApartmentNumber());
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
