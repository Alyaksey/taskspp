public enum ParserPattern { //Регулярки для парсинга адресов
    PROVINCE_REGEXP(".* обл."),
    REGION_REGEXP("^(Ставр|Волж).* р-н$"),
    CITY_REGEXP("^г[.].*|.*с[.].*|^(ПСК) .*|^п.*[.].*|.*село$"),
    DISTRICT_REGEXP(".*мик.*|.*гор.р-н.*|^(?!Ставр|Волж).* р-н$"),
    QUARTER_REGEXP("(?!гор-д).*(КВ|кв).*|.* к[.].*|^[0-9]+$|^к[.].*[0-9]+$|^[0-9]+к$"),
    STREET_REGEXP("(^((?!г.).*)[Уу]л+.*)|(.*б-р.*)|(.+пр[.])|(.*пр.*[дт]*.*)|(^((?!Самарск).*)ая.*)|(.+ш[.]?(оссе)*$)|(.+пер[.])"),
    HOUSE_REGEXP("здание [0-9]+[А-Яа-я]*[/]*[0-9]*$|^[Дд][.][0-9]+[А-Яа-я]*[/]*[0-9]*|^[0-9]+[А-Яа-я]*[/]*[0-9]* д.*|[0-9]+");

    private String value;

    ParserPattern(String value) {
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
