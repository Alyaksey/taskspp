package barBossHouse;

public class OrderTest {
    private TableOrder tableOrder;

/*    @Before
    public void setUp() throws Exception {
        MenuItem[] menuItems = new MenuItem[6];
        menuItems[0] = new MenuItem(127.5, "Макароны", "С котлетой");
        menuItems[1] = new MenuItem(355.25, "Пицца", "Пепперони");
        menuItems[2] = new MenuItem(65.34, "Курица", "Вареная");
        menuItems[3] = new MenuItem(175.34, "Пюре", "С отбивной");
        menuItems[4] = new MenuItem(134.32, "Курица", "Жареная");
        menuItems[5] = new MenuItem(37.36, "Салат", "Овощной");
        tableOrder = new TableOrder(menuItems);
    }

    @Test
    public void add() {
        assertTrue(tableOrder.add(new MenuItem(604.32, "Стейк", "Medium rare")));
        assertEquals(7, tableOrder.dishQuantity());
    }

    @Test
    public void remove() {
        assertTrue(tableOrder.remove("Макароны"));
        assertEquals(5, tableOrder.dishQuantity());
        assertNotEquals("Макароны", tableOrder.getItems()[0].getName());
        assertEquals("Пицца", tableOrder.getItems()[0].getName());
        assertEquals("Курица", tableOrder.getItems()[3].getName());
    }

    @Test
    public void removeAll() {
        assertEquals(2, tableOrder.removeAll("Курица"));
        assertEquals(4, tableOrder.dishQuantity());
        assertNotEquals("Курица", tableOrder.getItems()[2].getName());
    }

    @Test
    public void dishQuantity() {
        assertEquals(6, tableOrder.dishQuantity());
    }

    @Test
    public void getItems() {
        tableOrder.add(new MenuItem(604.32, "Стейк", "Medium rare"));
        assertEquals("Стейк", tableOrder.getItems()[6].getName());
        assertEquals(7, tableOrder.getItems().length);
    }

    @Test
    public void costTotal() {
        assertEquals(895.11, tableOrder.costTotal(), 0.00000001);
    }

    @Test
    public void dishQuantity1() {
        assertEquals(6, tableOrder.dishQuantity());
    }

    @Test
    public void dishesNames() {
        String[] expectedNames = {"Макароны", "Пицца", "Курица", "Пюре", "Салат"};
        assertArrayEquals(expectedNames, tableOrder.dishesNames());
    }

    @Test
    public void sortedDishesByCostDec() {
        double[] expectedCosts = {355.25, 175.34, 134.32, 127.5, 65.34, 37.36};
        MenuItem[] menuItems = tableOrder.sortedDishesByCostDec();
        for (int i = 0; i < expectedCosts.length; i++) {
            assertEquals(expectedCosts[i], menuItems[i].getCost(),0.000000001);
        }
    }*/
}