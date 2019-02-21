public enum IncorrectPart { //Некорректные частицы адресов
    INCORRECT_CITY_PART("ПСК|[Сс]ело"),
    INCORRECT_DISTRICT_PART(" р-н мик.| р-н|микро район| гор.р-н"),
    INCORRECT_QUARTER_PART("(КВ|кв).*[Лл]|к[.]|(КВ|кв)[.]*|к"),
    INCORRECT_STREET_PART("улиц[а.]|ул[.]"),
    INCORRECT_HOUSE_PART("д[.]|здание|дом");

    private String value;

    IncorrectPart(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
