package barBossHouse;

import java.time.LocalDateTime;

public interface Order {
    LocalDateTime getDateTime();

    void setDateTime(LocalDateTime dateTime);

    boolean add(MenuItem item);

    String[] itemsNames();

    int itemsQuantity();

    int itemQuantity(MenuItem itemName);

    int itemQuantity(String itemName);

    MenuItem[] getItems();

    boolean remove(String itemName);

    boolean remove(MenuItem item);

    int removeAll(String itemName);

    int removeAll(MenuItem item);

    MenuItem[] sortedItemsByCostDesc();

    double costTotal();

    Customer getCustomer();

    void setCustomer(Customer customer);

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    default void checkLawless(MenuItem item) {
        if (item instanceof Drink) {
            Drink drink = (Drink) item;
            if (drink.isAlcoholicDrink() && (getCustomer().getAge() < 18 || LocalDateTime.now().getHour() > 22))
                throw new UnlawfulActionException("You're not allowed to buy an alcohol");
        }
    }
}
