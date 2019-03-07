package barBossHouse;

public class Order {
    private Dish[] dishes;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Конструктор, не принимающий параметров, инициирующий массив из 16 элементов (сами элементы
     * имеют значение null).
     */
    public Order() {
        this(DEFAULT_CAPACITY);
        size = 0;
    }

    /**
     * Конструктор, принимающий целое число – емкость массива, инициирующий массив указанным числом
     * элементов (сами элементы имеют значение null).
     */
    public Order(int capacity) {
        if (capacity > 0) {
            dishes = new Dish[capacity];
        } else
            dishes = new Dish[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Конструктор, принимающий массив блюд.
     */
    public Order(Dish[] dishes) {
        this.dishes = new Dish[dishes.length];
        System.arraycopy(dishes, 0, this.dishes, 0, dishes.length);
        size = dishes.length;
    }

    /**
     * Метод, добавляющий блюдо в заказ (принимает ссылку на экземпляр класса Dish). Пока этот метод
     * возвращает истину после выполнения операции добавления элемента.
     */
    public boolean add(Dish dish) {
        if (size == dishes.length) {
            Dish[] newDishes = new Dish[dishes.length * 2];
            System.arraycopy(dishes, 0, newDishes, 0, dishes.length);
            dishes = newDishes;
        }
        dishes[size++] = dish;
        return true;
    }

    /**
     * Метод, удаляющий блюдо из заказа по его названию (принимает название блюда в качестве
     * параметра). Если блюд с заданным названием несколько, удаляется первое найденное. Возвращает
     * логическое значение (true, если элемент был удален).
     */
    public boolean remove(String dishName) {
        for (int i = 0; i < size; i++) {
            if (dishName.equals(dishes[i].getName())) {
                shiftArray(i);
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, удаляющий все блюда с заданным именем (принимает название блюда в качестве
     * параметра). Возвращает число удаленных элементов.
     */
    public int removeAll(String dishName) {
        int count = 0;
        Dish[] newDishes = new Dish[dishes.length];
        for (int i = 0; i < size; i++) {
            if (dishName.equals(dishes[i].getName())) {
                dishes[i] = null;
                count++;
            }
            if (dishes[i] != null)
                newDishes[i - count] = dishes[i];
        }
        dishes = newDishes;
        size -= count;
        return count;
    }

    /**
     * Метод, возвращающий общее число блюд (повторяющиеся блюда тоже считаются) в заказе.
     */
    public int dishQuantity() {
        return size;
    }

    /**
     * Метод, возвращающий массив блюд (значений null в массиве быть не должно).
     */
    public Dish[] getDishes() {
        Dish[] newDishes = new Dish[size];
        System.arraycopy(dishes, 0, newDishes, 0, size);
        return newDishes;
    }

    /**
     * Метод, возвращающий общую стоимость заказа.
     */
    public double costTotal() {
        double costTotal = 0.0;
        for (int i = 0; i < size; i++)
            costTotal += dishes[i].getCost();
        return costTotal;
    }

    /**
     * Метод, возвращающий число заказанных блюд (принимает название блюда в качестве параметра)
     */
    public int dishQuantity(String dishName) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (dishName.equals(dishes[i].getName())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Метод, возвращающий массив названий заказанных блюд (без повторов).
     */
    public String[] dishesNames() {
        String[] names = new String[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            names[count] = dishes[i].getName();
            if (!containsDuplicates(names, count))
                count++;
        }
        String[] uniqueNames = new String[count];
        System.arraycopy(names, 0, uniqueNames, 0, count);
        return uniqueNames;
    }

    /**
     * Метод, возвращающий массив блюд, отсортированный по убыванию цены.
     */
    public Dish[] sortedDishesByCostDec() {
        Dish[] sortedDishes = getDishes();
        mergeSort(sortedDishes);
        return sortedDishes;
    }

    private void shiftArray(int index) {
        System.arraycopy(dishes, index + 1, dishes, index, size - index - 1);
        dishes[size - 1] = null;
    }

    private boolean containsDuplicates(String[] names, int count) {
        for (int i = 0; i < count; i++) {
            if (names[i].equals(names[count]))
                return true;
        }
        return false;
    }

    private void mergeSort(Dish[] dishes) {
        if (dishes.length < 2)
            return;
        int mid = dishes.length / 2;
        Dish[] leftArray = new Dish[mid];
        Dish[] rightArray = new Dish[dishes.length - mid];
        System.arraycopy(dishes, 0, leftArray, 0, leftArray.length);
        System.arraycopy(dishes, leftArray.length, rightArray, 0, rightArray.length);
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(dishes, leftArray, rightArray);
    }

    private void merge(Dish[] dishes, Dish[] leftArray, Dish[] rightArray) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].getCost() >= rightArray[j].getCost()) {
                dishes[k++] = leftArray[i++];
            } else {
                dishes[k++] = rightArray[j++];
            }
        }
        while (i < leftArray.length) {
            dishes[k++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            dishes[k++] = rightArray[j++];
        }
    }
}
