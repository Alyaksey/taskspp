package barBossHouse;

public class Dish {
    private double cost;
    private String name;
    private String description;

    private static final double DEFAULT_COST = 0.0;

    /**
     * Конструктор, принимающий два параметра – название и описание блюда. Стоимость при этом
     * инициализируется значением 0
     */
    public Dish(String name, String description) {
        this(DEFAULT_COST, name, description);
    }

    /**
     * Конструктор, принимающий три параметра – стоимость, название и описание блюда.
     */
    public Dish(double cost, String name, String description) {
        this.cost = cost;
        this.name = name;
        this.description = description;
    }

    /**
     * Метод, возвращающий стоимость.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Метод, устанавливающий новое значение стоимости.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Метод, возвращающий название.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, устанавливающий новое значение названия
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод, возвращающий описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * устанавливающий новое значение описания.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
