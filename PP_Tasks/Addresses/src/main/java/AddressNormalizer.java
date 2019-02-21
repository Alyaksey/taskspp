public class AddressNormalizer {

    //Метод для приведения адреса к нормализованному виду, принимает экземпляр класса Address
    public static void normalizeAddress(Address address) {
        normalizeCity(address);
        normalizeDistrict(address);
        normalizeQuarter(address);
        normalizeStreet(address);
        normalizeHouse(address);
    }

    private static void normalizeCity(Address address) {
        String city = address.getCity();
        //Если данные не подходят по шаблону, то исправляем их
        if (!city.matches(NormalizerPattern.CORRECT_CITY_PATTERN.toString()) && !city.isEmpty()) {
            address.setCorrect(false); //Помечаем адрес как некорректный
            address.addIncorrectness(Incorrectness.WRONG_CITY); //Добавляем причину некорректности
            city = city.replaceAll(IncorrectPart.INCORRECT_CITY_PART.toString(), "").trim();//Замена некорректной частицы
            city = new StringBuilder(city).insert(0, CorrectPart.CORRECT_CITY_PART).toString(); //Вставляем в начало строки корректную частицу
            address.setCity(city); //Устанавливаем значение в поле объекта
        }
        if (city.isEmpty()) { //Если значение пустое, то адрес некорректный (в случае с городом, улицей или домом)
            address.setCorrect(false);
            address.addIncorrectness(Incorrectness.WRONG_CITY);
        }
    }

    private static void normalizeDistrict(Address address) {
        String district = address.getDistrict();
        if (!district.matches(NormalizerPattern.CORRECT_DISTRICT_PATTERN.toString()) && !district.isEmpty()) {
            address.setCorrect(false);
            address.addIncorrectness(Incorrectness.WRONG_DISTRICT);
            district = district.replaceAll(IncorrectPart.INCORRECT_DISTRICT_PART.toString(), "").trim();
            district = new StringBuilder(district).append(CorrectPart.CORRECT_DISTRICT_PART.toString()).toString();
            address.setDistrict(district);
        }
    }

    private static void normalizeQuarter(Address address) {
        String quarter = address.getQuarter();
        if (!quarter.matches(NormalizerPattern.CORRECT_QUARTER_PATTERN.toString()) && !quarter.isEmpty()) {
            address.setCorrect(false);
            address.addIncorrectness(Incorrectness.WRONG_QUARTER);
            quarter = quarter.replaceAll(IncorrectPart.INCORRECT_QUARTER_PART.toString(), "").trim();
            quarter = new StringBuilder(quarter).insert(0, CorrectPart.CORRECT_QUARTER_PART.toString()).toString();
            address.setQuarter(quarter);
        }
    }

    private static void normalizeStreet(Address address) {
        String street = address.getStreet();
        if (!street.matches(NormalizerPattern.CORRECT_STREET_PATTERN.toString()) && !street.isEmpty()) {
            address.setCorrect(false);
            address.addIncorrectness(Incorrectness.WRONG_STREET);
            street = street.replaceAll(IncorrectPart.INCORRECT_STREET_PART.toString(), "").trim();
            street = new StringBuilder(street).insert(0, CorrectPart.CORRECT_STREET_PART.toString()).toString();
            address.setStreet(street);
        }
        if (street.isEmpty()) {
            address.setCorrect(false);
            address.addIncorrectness(Incorrectness.WRONG_STREET);
        }
    }

    private static void normalizeHouse(Address address) {
        String house = address.getHouse();
        if (!house.matches(NormalizerPattern.CORRECT_HOUSE_PATTERN.toString()) && !house.isEmpty()) {
            address.setCorrect(false);
            address.addIncorrectness(Incorrectness.WRONG_HOUSE);
            house = house.replaceAll(IncorrectPart.INCORRECT_HOUSE_PART.toString(), "").trim();
            house = new StringBuilder(house).insert(0, CorrectPart.CORRECT_HOUSE_PART.toString()).toString();
            address.setHouse(house);
        }
        if (house.isEmpty()) {
            address.setCorrect(false);
            address.addIncorrectness(Incorrectness.WRONG_HOUSE);
        }
    }
}
