package barBossHouse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface Order extends List<MenuItem>, Serializable {
    LocalDateTime getDateTime();

    void setDateTime(LocalDateTime dateTime);

    String[] itemsNames();

    int itemQuantity(MenuItem itemName);

    int itemQuantity(String itemName);

    boolean remove(String itemName);

    int removeAll(String itemName);

    int removeAll(MenuItem item);

    double costTotal();

    Customer getCustomer();

    void setCustomer(Customer customer);

    @Override
    String toString();

    @Override
    int hashCode();

    String toFileString();

    default MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] sortedItems = (MenuItem[]) toArray();
        Arrays.sort(sortedItems);
        return sortedItems;
    }

    default boolean isEqual(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Order))
            return false;
        Order order;
        if (obj instanceof InternetOrder)
            order = (InternetOrder) obj;
        else
            order = (TableOrder) obj;
        if (Objects.equals(this.getCustomer(), order.getCustomer())
                && this.size() == order.size()
                && Objects.equals(this.getDateTime(), order.getDateTime())) {
            String[] itemsNames = itemsNames();
            for (String itemsName : itemsNames) {
                if (itemQuantity(itemsName) != order.itemQuantity(itemsName))
                    return false;
            }
            return true;
        }
        return false;
    }

    static void mergeSort(MenuItem[] items) {
        if (items.length < 2)
            return;
        int mid = items.length / 2;
        MenuItem[] leftArray = new MenuItem[mid];
        MenuItem[] rightArray = new MenuItem[items.length - mid];
        System.arraycopy(items, 0, leftArray, 0, leftArray.length);
        System.arraycopy(items, leftArray.length, rightArray, 0, rightArray.length);
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(items, leftArray, rightArray);
    }

    static void merge(MenuItem[] items, MenuItem[] leftArray, MenuItem[] rightArray) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].getCost() >= rightArray[j].getCost()) {
                items[k++] = leftArray[i++];
            } else {
                items[k++] = rightArray[j++];
            }
        }
        while (i < leftArray.length) {
            items[k++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            items[k++] = rightArray[j++];
        }
    }

}
