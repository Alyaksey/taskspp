package barBossHouse;

public class OrderManagerTest {
    private TableOrdersManager tableOrderManager;
/*
    @Before
    public void setUp() throws Exception {
        MenuItem[] firstMenuItems = new MenuItem[2];
        firstMenuItems[0] = new MenuItem(127.5, "Макароны", "С котлетой");
        firstMenuItems[1] = new MenuItem(355.25, "Пицца", "Пепперони");
        MenuItem[] secondMenuItems = new MenuItem[3];
        secondMenuItems[0] = new MenuItem(65.34, "Курица", "Вареная");
        secondMenuItems[1] = new MenuItem(175.34, "Пюре", "С отбивной");
        secondMenuItems[2] = new MenuItem(134.32, "Курица", "Жареная");
        MenuItem[] thirdMenuItems = new MenuItem[2];
        thirdMenuItems[0] = new MenuItem(37.36, "Салат", "Овощной");
        thirdMenuItems[1] = new MenuItem(450, "Пицца", "Маргарита");
        TableOrder[] orders = new TableOrder[3];
        orders[0] = new TableOrder(firstMenuItems);
        orders[1] = new TableOrder(secondMenuItems);
        orders[2] = new TableOrder(thirdMenuItems);
        tableOrderManager = new TableOrdersManager(6);
        tableOrderManager.add(orders[0], 1);
        tableOrderManager.add(orders[1], 3);
        tableOrderManager.add(orders[2], 5);
    }

    @Test
    public void add() {
        MenuItem[] menuItems = new MenuItem[2];
        menuItems[0] = new MenuItem(65.34, "Курица", "Вареная");
        menuItems[1] = new MenuItem(175.34, "Пюре", "С отбивной");
        TableOrder order = new TableOrder(menuItems);
        tableOrderManager.add(order, 0);
        assertEquals(order, tableOrderManager.getOrder(0));
    }

    @Test
    public void getOrder() {
        assertEquals(3, tableOrderManager.getOrder(3).dishQuantity());
    }

    @Test
    public void addDish() {
        tableOrderManager.addDish(new MenuItem(325.3, "Name", "Description"), 3);
        assertEquals("Name", tableOrderManager.getOrder(3).getItems()[3].getName());
    }

    @Test
    public void remove() {
        tableOrderManager.remove(3);
        assertNull(tableOrderManager.getOrder(3));
    }

    @Test
    public void freeTableNumber() {
        assertEquals(0, tableOrderManager.freeTableNumber());
    }

    @Test
    public void freeTableNumbers() {
        int[] expectedArray = {0, 2, 4};
        assertArrayEquals(expectedArray, tableOrderManager.freeTableNumbers());
    }

    @Test
    public void busyTableNumbers() {
        int[] expectedArray = {1, 3, 5};
        assertArrayEquals(expectedArray,tableOrderManager.busyTableNumbers());
    }

    @Test
    public void getOrders() {
        TableOrder[] expectedOrders = new TableOrder[3];
        expectedOrders[0] = tableOrderManager.getOrder(1);
        expectedOrders[1] = tableOrderManager.getOrder(3);
        expectedOrders[2] = tableOrderManager.getOrder(5);
        for (int i = 0; i < expectedOrders.length; i++) {
            assertEquals(expectedOrders[i],tableOrderManager.getOrders()[i]);
        }
    }

    @Test
    public void orderCostSummary() {
        assertEquals(1345.11,tableOrderManager.orderCostSummary(),0.00000001);
    }

    @Test
    public void dishQuantity() {
        assertEquals(2,tableOrderManager.dishQuantity("Пицца"));
    }*/
}