package barBossHouse;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Comparable<MenuItem>, Serializable {
    private double cost;
    private String name;
    private String description;

    private static final double DEFAULT_COST = 0.0;

    /**
     * Конструктор, принимающий два параметра – название и описание блюда. Стоимость при этом
     * инициализируется значением 0
     */
    protected MenuItem(String name, String description) {
        this(DEFAULT_COST, name, description);
    }

    /**
     * Конструктор, принимающий три параметра – стоимость, название и описание блюда.
     */
    protected MenuItem(double cost, String name, String description) {
        if (cost < 0)
            throw new IllegalArgumentException("Cost cannot be negative");
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
     * Метод, возвращающий название.
     */
    public String getName() {
        return name;
    }


    /**
     * Метод, возвращающий описание.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s, %.2fр.", Objects.toString(name, ""), cost);
    }

    public String toFileString() {
        return String.format("%s %f", name, cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MenuItem menuItem = (MenuItem) obj;
        return menuItem.cost == cost &&
                Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }

    @Override
    public int compareTo(MenuItem o) {
        return Double.compare(cost, o.cost);
    }

}
