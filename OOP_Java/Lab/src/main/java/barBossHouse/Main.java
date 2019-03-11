package barBossHouse;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Вонючая ул.",-1,'А',4);
        System.out.println(address.toString());
        Drink drink = new Drink(4.3,"Водяра","охуенная",40.0,DrinkTypeEnum.VODKA);
        Drink drink1 = new Drink(4.3,"Водяра","охуенная",40.0,DrinkTypeEnum.VODKA);
        System.out.println(drink.equals(drink1));
        System.out.println(drink.hashCode());
        System.out.println(drink1.hashCode());
    }
}
