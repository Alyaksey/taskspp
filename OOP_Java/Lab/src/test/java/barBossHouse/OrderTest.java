package barBossHouse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;

    @Before
    public void setUp() throws Exception {
        Dish[] dishes = new Dish[6];
        dishes[0] = new Dish(127.5, "Макароны", "С котлетой");
        dishes[1] = new Dish(355.25, "Пицца", "Пепперони");
        dishes[2] = new Dish(65.34, "Курица", "Вареная");
        dishes[3] = new Dish(175.34, "Пюре", "С отбивной");
        dishes[4] = new Dish(134.32, "Курица", "Жареная");
        dishes[5] = new Dish(37.36, "Салат", "Овощной");
        order = new Order(dishes);
    }

    @Test
    public void add() {
        assertTrue(order.add(new Dish(604.32, "Стейк", "Medium rare")));
        assertEquals(7, order.dishQuantity());
    }

    @Test
    public void remove() {
        assertTrue(order.remove("Макароны"));
        assertEquals(5, order.dishQuantity());
        assertNotEquals("Макароны", order.getDishes()[0].getName());
        assertEquals("Пицца", order.getDishes()[0].getName());
        assertEquals("Курица", order.getDishes()[3].getName());
    }

    @Test
    public void removeAll() {
        assertEquals(2, order.removeAll("Курица"));
        assertEquals(4, order.dishQuantity());
        assertNotEquals("Курица", order.getDishes()[2].getName());
    }

    @Test
    public void dishQuantity() {
        assertEquals(6, order.dishQuantity());
    }

    @Test
    public void getDishes() {
        order.add(new Dish(604.32, "Стейк", "Medium rare"));
        assertEquals("Стейк", order.getDishes()[6].getName());
        assertEquals(7, order.getDishes().length);
    }

    @Test
    public void costTotal() {
        assertEquals(895.11, order.costTotal(), 0.00000001);
    }

    @Test
    public void dishQuantity1() {
        assertEquals(6, order.dishQuantity());
    }

    @Test
    public void dishesNames() {
        String[] expectedNames = {"Макароны", "Пицца", "Курица", "Пюре", "Салат"};
        assertArrayEquals(expectedNames, order.dishesNames());
    }

    @Test
    public void sortedDishesByCostDec() {
        double[] expectedCosts = {355.25, 175.34, 134.32, 127.5, 65.34, 37.36};
        Dish[] dishes = order.sortedDishesByCostDec();
        for (int i = 0; i < expectedCosts.length; i++) {
            assertEquals(expectedCosts[i], dishes[i].getCost(),0.000000001);
        }
    }
}