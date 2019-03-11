package barBossHouse;

public final class Dish extends MenuItem {
    protected Dish(String name, String description) {
        super(name, description);
    }

    protected Dish(double cost, String name, String description) {
        super(cost, name, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dish: ").append(super.toString()).append(' ').append(getDescription());
        return sb.toString();
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
