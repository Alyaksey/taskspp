package barBossHouse;

public final class Address {
    private final String cityName;
    private final int zipCode;
    private final String streetName;
    private final int buildingNumber;
    private final char buildingLetter;
    private final int apartmentNumber;

    public static final Address EMPTY_ADDRESS = new Address();

    private static final String UNKNOWN_CITY_NAME = "";
    private static final int UNKNOWN_ZIP_CODE = -1;
    private static final String UNKNOWN_STREET_NAME = "";
    private static final int UNKNOWN_BUILDING_NUMBER = -1;
    private static final char UNKNOWN_BUILDING_LETTER = ' ';
    private static final int UNKNOWN_APARTMENT_NUMBER = -1;
    private static final String DEFAULT_CITY = "Самара";


    public Address() {
        this(UNKNOWN_CITY_NAME, UNKNOWN_ZIP_CODE, UNKNOWN_STREET_NAME, UNKNOWN_BUILDING_NUMBER, UNKNOWN_BUILDING_LETTER, UNKNOWN_APARTMENT_NUMBER);
    }

    public Address(String streetName, int buildingNumber, char buildingLetter, int apartmentNumber) {
        this(DEFAULT_CITY, UNKNOWN_ZIP_CODE, streetName, buildingNumber, buildingLetter, apartmentNumber);
    }

    public Address(String cityName, int zipCode, String streetName, int buildingNumber, char buildingLetter, int apartmentNumber) {
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.apartmentNumber = apartmentNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address: ");
        if (!cityName.isEmpty())
            sb.append(cityName);
        if (zipCode != UNKNOWN_ZIP_CODE)
            sb.append(' ').append(zipCode);
        if (!streetName.isEmpty())
            sb.append(", ").append(streetName);
        if (buildingNumber != UNKNOWN_BUILDING_NUMBER)
            sb.append(' ').append(buildingNumber);
        if (buildingLetter != UNKNOWN_BUILDING_LETTER)
            sb.append(buildingLetter);
        if (apartmentNumber != UNKNOWN_APARTMENT_NUMBER)
            sb.append('-').append(apartmentNumber);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Address address = (Address) obj;
        return (address.cityName.equals(this.cityName) && address.zipCode == this.zipCode &&
                address.streetName.equals(this.streetName) && address.buildingNumber == this.buildingNumber &&
                address.buildingLetter == this.buildingLetter && address.apartmentNumber == this.apartmentNumber);
    }

    @Override
    public int hashCode() {
        return cityName.hashCode() ^ Integer.hashCode(zipCode) ^ streetName.hashCode() ^
                Integer.hashCode(buildingNumber) ^ Character.hashCode(buildingLetter) ^
                Integer.hashCode(apartmentNumber);
    }
}
