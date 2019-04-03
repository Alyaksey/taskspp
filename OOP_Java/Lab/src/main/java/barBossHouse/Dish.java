package barBossHouse;

public final class Dish extends MenuItem {
    public Dish(String name, String description) {
        super(name, description);
    }

    public Dish(double cost, String name, String description) {
        super(cost, name, description);
    }

    @Override
    public String toString() {
        return String.format("Dish: %s %s", super.toString(), getDescription());
    }

    public String toFileString() {
        return String.format("Dish %s %s\n", super.toFileString(), getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
