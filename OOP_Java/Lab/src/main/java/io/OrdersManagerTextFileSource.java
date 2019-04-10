package io;

import barBossHouse.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdersManagerTextFileSource extends OrderManagerFileSource {
    private static String EXTENSION = ".txt";

    public OrdersManagerTextFileSource(String path) {
        setPath(path);
    }

    @Override
    public void load(Order order) throws IOException {
        LocalDateTime orderTime = order.getDateTime();
        Path path = Paths.get(getPath() + orderTime.toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        Scanner scanner = new Scanner(path);
        Customer customer = loadCustomer(scanner);
        List<MenuItem> items = loadItems(scanner);
        scanner.close();
        order.clear();
        order.setDateTime(orderTime);
        order.setCustomer(customer);
        order.addAll(items);
    }

    private Customer loadCustomer(Scanner scanner) {
        String firstName = scanner.next();
        String lastName = scanner.next();
        LocalDate birthDate = LocalDate.ofEpochDay(scanner.nextLong());
        Address address = loadAddress(scanner);
        return new Customer(firstName, lastName, birthDate, address);
    }

    private Address loadAddress(Scanner scanner) {
        String city = scanner.next();
        int zipCode = scanner.nextInt();
        String streetName = scanner.next();
        int buildingNumber = scanner.nextInt();
        String buildingLetter = scanner.next();
        int apartmentNumber = scanner.nextInt();
        return new Address(city, zipCode, streetName, buildingNumber, buildingLetter, apartmentNumber);
    }

    //todo возвращй ArrayList по ссылке List+
    private List<MenuItem> loadItems(Scanner scanner) {
        int size = scanner.nextInt();
        List<MenuItem> items = new ArrayList<>(size);
        String type, name, description, drinkType;
        double cost;
        double alcoholVol;
        for (int i = 0; i < size; i++) {
            type = scanner.next();
            name = scanner.next();
            cost = scanner.nextDouble();
            description = scanner.next();
            if (type.equals("Drink")) {
                drinkType = scanner.next();
                alcoholVol = scanner.nextDouble();
                items.add(new Drink(cost, name, description, alcoholVol, DrinkTypeEnum.valueOf(drinkType)));
            } else
                items.add(new Dish(cost, description, name));
        }
        return items;
    }

    @Override
    public void store(Order order) {
        Path path = Paths.get(getPath() + order.getDateTime().toEpochSecond(ZoneOffset.UTC) + EXTENSION);
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path))) {
            writer.write(order.toFileString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
