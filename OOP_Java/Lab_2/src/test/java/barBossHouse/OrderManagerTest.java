package barBossHouse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderManagerTest {
    private OrderManager orderManager;

    @Before
    public void setUp() throws Exception {
        Dish[] firstDishes = new Dish[2];
        firstDishes[0] = new Dish(127.5, "Макароны", "С котлетой");
        firstDishes[1] = new Dish(355.25, "Пицца", "Пепперони");
        Dish[] secondDishes = new Dish[3];
        secondDishes[0] = new Dish(65.34, "Курица", "Вареная");
        secondDishes[1] = new Dish(175.34, "Пюре", "С отбивной");
        secondDishes[2] = new Dish(134.32, "Курица", "Жареная");
        Dish[] thirdDishes = new Dish[2];
        thirdDishes[0] = new Dish(37.36, "Салат", "Овощной");
        thirdDishes[1] = new Dish(450, "Пицца", "Маргарита");
        Order[] orders = new Order[3];
        orders[0] = new Order(firstDishes);
        orders[1] = new Order(secondDishes);
        orders[2] = new Order(thirdDishes);
        orderManager = new OrderManager(6);
        orderManager.add(orders[0], 1);
        orderManager.add(orders[1], 3);
        orderManager.add(orders[2], 5);
    }

    @Test
    public void add() {
        Dish[] dishes = new Dish[2];
        dishes[0] = new Dish(65.34, "Курица", "Вареная");
        dishes[1] = new Dish(175.34, "Пюре", "С отбивной");
        Order order = new Order(dishes);
        orderManager.add(order, 0);
        assertSame(order, orderManager.getOrder(0));
    }

    @Test
    public void getOrder() {
        assertEquals(3, orderManager.getOrder(3).dishQuantity());
    }

    @Test
    public void addDish() {
        orderManager.addDish(new Dish(325.3, "Name", "Description"), 3);
        assertEquals("Name", orderManager.getOrder(3).getDishes()[3].getName());
    }

    @Test
    public void removeOrder() {
        orderManager.removeOrder(3);
        assertNull(orderManager.getOrder(3));
    }

    @Test
    public void freeTableNumber() {
        assertEquals(0, orderManager.freeTableNumber());
    }

    @Test
    public void freeTableNumbers() {
        int[] expectedArray = {0, 2, 4};
        assertArrayEquals(expectedArray, orderManager.freeTableNumbers());
    }

    @Test
    public void busyTableNumbers() {
        int[] expectedArray = {1, 3, 5};
        assertArrayEquals(expectedArray,orderManager.busyTableNumbers());
    }

    @Test
    public void getOrders() {
        Order[] expectedOrders = new Order[3];
        expectedOrders[0] = orderManager.getOrder(1);
        expectedOrders[1] = orderManager.getOrder(3);
        expectedOrders[2] = orderManager.getOrder(5);
        for (int i = 0; i < expectedOrders.length; i++) {
            assertSame(expectedOrders[i],orderManager.getOrders()[i]);
        }
    }

    @Test
    public void orderCostSummary() {
        assertEquals(1345.11,orderManager.orderCostSummary(),0.00000001);
    }

    @Test
    public void dishQuantity() {
        assertEquals(2,orderManager.dishQuantity("Пицца"));
    }
}