public enum CorrectPart { //Корректные частицы адресов
    CORRECT_CITY_PART("с."),
    CORRECT_DISTRICT_PART(" микрор-н"),
    CORRECT_QUARTER_PART("Кв."),
    CORRECT_STREET_PART("Ул."),
    CORRECT_HOUSE_PART("Д.");

    private String value;

    CorrectPart(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
