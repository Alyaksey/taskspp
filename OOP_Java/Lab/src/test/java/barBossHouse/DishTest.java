package barBossHouse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DishTest {
    private Dish Dish;

    @Before
    public void setUp() throws Exception {
        this.Dish = new Dish(25.55,"Бутерброд","С колбасой");
    }

    @Test
    public void getCost() {
        double expectedCost = 25.55;
        assertEquals(expectedCost, Dish.getCost(),0.0000000001);
    }


    @Test
    public void getName() {
        String expectedName = "Бутерброд";
        assertEquals(expectedName, Dish.getName());
    }


    @Test
    public void getDescription() {
        String expectedDescription = "С колбасой";
        assertEquals(expectedDescription, Dish.getDescription());
    }

}