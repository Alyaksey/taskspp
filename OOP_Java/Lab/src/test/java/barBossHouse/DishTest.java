package barBossHouse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DishTest {
    private Dish dish;

    @Before
    public void setUp() throws Exception {
        this.dish = new Dish(25.55,"Бутерброд","С колбасой");
    }

    @Test
    public void getCost() {
        double expectedCost = 25.55;
        assertEquals(expectedCost, dish.getCost(),0.0000000001);
    }

    @Test
    public void setCost() {
        double expectedCost = 30.45;
        dish.setCost(expectedCost);
        assertEquals(expectedCost, dish.getCost(),0.0000000001);
    }

    @Test
    public void getName() {
        String expectedName = "Бутерброд";
        assertEquals(expectedName, dish.getName());
    }

    @Test
    public void setName() {
        String expectedName = "Пирог";
        dish.setName(expectedName);
        assertEquals(expectedName, dish.getName());
    }

    @Test
    public void getDescription() {
        String expectedDescription = "С колбасой";
        assertEquals(expectedDescription, dish.getDescription());
    }

    @Test
    public void setDescription() {
        String expectedDescription = "С сыром";
        dish.setDescription(expectedDescription);
        assertEquals(expectedDescription, dish.getDescription());
    }
}