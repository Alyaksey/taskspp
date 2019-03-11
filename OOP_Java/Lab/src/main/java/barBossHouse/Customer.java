package barBossHouse;

public final class Customer {
    private final String firstName;
    private final String secondName;
    private final int age;
    private final Address address;

    private static final String UNKNOWN_FIRST_NAME = "";
    private static final String UNKNOWN_SECOND_NAME = "";
    private static final int UNKNOWN_AGE = -1;

    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(14);
    public static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer(18);

    public Customer() {
        this(UNKNOWN_FIRST_NAME, UNKNOWN_SECOND_NAME, UNKNOWN_AGE, Address.EMPTY_ADDRESS);
    }

    public Customer(int age) {
        this(UNKNOWN_FIRST_NAME, UNKNOWN_SECOND_NAME, age, Address.EMPTY_ADDRESS);
    }

    public Customer(String firstName, String secondName, int age, Address address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ");
        if (!secondName.isEmpty())
            sb.append(secondName);
        if (!firstName.isEmpty())
            sb.append(" ").append(firstName);
        if (age != UNKNOWN_AGE)
            sb.append(", ").append(age);
        if (!address.equals(Address.EMPTY_ADDRESS))
            sb.append(", ").append(address.toString());
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Customer customer = (Customer) obj;
        return age == customer.age &&
                firstName.equals(customer.firstName) &&
                secondName.equals(customer.secondName) &&
                address.equals(customer.address);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() ^ secondName.hashCode() ^ Integer.hashCode(age) ^ address.hashCode();
    }
}
