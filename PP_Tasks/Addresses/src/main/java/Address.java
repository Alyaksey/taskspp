import java.util.*;

public class Address {
    private String province;
    private String region;
    private String city;
    private String district;
    private String quarter;
    private String street;
    private String house;
    private boolean correct; //Поле корректности адреса
    private ArrayList<Incorrectness> incorrectnesses; //Причины некорректности адреса

    private final String DEFAULT_VALUE = "";
    private final boolean DEFAULT_CORRECTNESS = true;

    public Address() {
        province = DEFAULT_VALUE;
        region = DEFAULT_VALUE;
        city = DEFAULT_VALUE;
        district = DEFAULT_VALUE;
        quarter = DEFAULT_VALUE;
        street = DEFAULT_VALUE;
        house = DEFAULT_VALUE;
        correct = DEFAULT_CORRECTNESS;
        incorrectnesses = new ArrayList<>();
    }

    public Address(String province, String region, String city, String district, String quarter, String street, String house) {
        this.province = province;
        this.region = region;
        this.city = city;
        this.district = district;
        this.quarter = quarter;
        this.street = street;
        this.house = house;
        correct = DEFAULT_CORRECTNESS;
        incorrectnesses = new ArrayList<>();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public ArrayList<Incorrectness> getIncorrectnesses() {
        return incorrectnesses;
    }

    public void setIncorrectnesses(ArrayList<Incorrectness> incorrectnesses) {
        this.incorrectnesses = incorrectnesses;
    }

    public void addIncorrectness(Incorrectness incorrectness){
        incorrectnesses.add(incorrectness);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(province).append(',').append(region).append(',').append(city)
                .append(',').append(district).append(',').append(quarter).append(',').append(street).append(',')
                .append(house);
        return sb.toString();
    }
}
