public enum NormalizerPattern { //Регулярки для нормализации адресов
    CORRECT_CITY_PATTERN("(г.|с.|п.|пос.).*"),
    CORRECT_DISTRICT_PATTERN(".*микрор-н$"),
    CORRECT_QUARTER_PATTERN("Кв[.].*"),
    CORRECT_STREET_PATTERN("Ул[.].*"),
    CORRECT_HOUSE_PATTERN("Д[.].*");

    private String value;

    NormalizerPattern(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
