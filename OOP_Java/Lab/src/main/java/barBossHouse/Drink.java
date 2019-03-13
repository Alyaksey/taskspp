package barBossHouse;

import java.util.Objects;

public final class Drink extends MenuItem implements Alcoholable {

    private double alcoholVol;
    private DrinkTypeEnum type;

    private static final double DEFAULT_COST = 0.0;
    private static final double DEFAULT_ALCOHOL_VOLUME = 0.0;
    private static final String UNKNOWN_DESCRIPTION = "";

    public Drink(String name, DrinkTypeEnum type) {
        this(DEFAULT_COST, name, UNKNOWN_DESCRIPTION, DEFAULT_ALCOHOL_VOLUME, type);
    }

    public Drink(double cost, String name, DrinkTypeEnum type, String description) {
        this(cost, name, description, DEFAULT_ALCOHOL_VOLUME, type);
    }

    public Drink(double cost, String name, String description, double alcoholVol, DrinkTypeEnum type) {
        super(cost, name, description);
        this.alcoholVol = alcoholVol;
        this.type = type;
    }

    public DrinkTypeEnum getType() {
        return type;
    }

    @Override
    public boolean isAlcoholicDrink() {
        return alcoholVol > 0;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
    }

    @Override
    public String toString() {
        return isAlcoholicDrink() ?
                String.format("Drink: %s, %s, Alcohol: %.2f %s", type, super.toString(), alcoholVol, type) :
                String.format("Drink: %s, %s", type, super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;
        Drink drink = (Drink) obj;
        return super.equals(obj) &&
                drink.alcoholVol == alcoholVol &&
                type.equals(drink.type);
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Double.hashCode(alcoholVol) ^ type.hashCode();
    }
}
