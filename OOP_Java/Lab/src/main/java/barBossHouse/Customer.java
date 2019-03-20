package barBossHouse;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public final class Customer {
    private final String firstName;
    private final String secondName;
    private final LocalDate birthDate;
    private final Address address;

    private static final String UNKNOWN_FIRST_NAME = "";
    private static final String UNKNOWN_SECOND_NAME = "";
    private static final int NOT_MATURE_CUSTOMER_AGE = 14;
    private static final int MATURE_CUSTOMER_AGE = 18;

    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(LocalDate.of(LocalDate.now().getYear() - NOT_MATURE_CUSTOMER_AGE, 1, 1));
    public static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer(LocalDate.of(LocalDate.now().getYear() - MATURE_CUSTOMER_AGE, 1, 1));

    public Customer() {
        this(UNKNOWN_FIRST_NAME, UNKNOWN_SECOND_NAME, LocalDate.now(), Address.EMPTY_ADDRESS);
    }

    public Customer(LocalDate birthDate) {
        this(UNKNOWN_FIRST_NAME, UNKNOWN_SECOND_NAME, birthDate, Address.EMPTY_ADDRESS);
    }

    public Customer(String firstName, String secondName, LocalDate birthDate, Address address) {
        if (birthDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Are you from the future?");
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return Period.between(LocalDate.now(), birthDate).getYears();
        //todo Period p = Period.between(LocalDate.now(), birthDate);+

    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        //todo Objects.toString(secondName, "")+
        StringBuilder sb = new StringBuilder("Customer: ");
        sb.append(Objects.toString(secondName, ""))
                .append(" ")
                .append(Objects.toString(firstName, ""))
                .append(", ")
                .append(Objects.toString(birthDate, ""))
                .append(", ").append(Objects.toString(address, ""));
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Customer customer = (Customer) obj;
        //todo Objects.equals(birthDate, customer.birthDate);+
        return Objects.equals(birthDate, customer.birthDate) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(secondName, customer.secondName) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        //todo Objects.hash(firstName, secondName ..);+
        return Objects.hash(firstName, secondName, birthDate, address);
    }
}
