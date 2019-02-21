public class AddressParser {
    //Метод принимает массив строк, парсит его и возвращет экземпляр класса address
    public static Address getAddress(String[] splitAddress) {
        Address address = new Address();
        address.setProvince(AddressParser.parseProvince(splitAddress));
        address.setDistrict(AddressParser.parseDistrict(splitAddress));
        address.setCity(AddressParser.parseCity(splitAddress));
        address.setRegion(AddressParser.parseRegion(splitAddress));
        address.setQuarter(AddressParser.parseQuarter(splitAddress));
        address.setStreet(AddressParser.parseStreet(splitAddress));
        address.setHouse(AddressParser.parseHouse(splitAddress));
        return address;
    }
    //Приватные методы для поиска данных в массиве строк
    private static String parseProvince(String[] address) {
        String province = "";
        for (String addres : address) {
            //Если находим необходимые данные, выходим из цикла и возвращаем строку
            if (addres.matches(ParserPattern.PROVINCE_REGEXP.toString())) {
                province = addres;
                break;
            }
        }
        return province;
    }

    private static String parseRegion(String[] address) {
        String region = "";
        for (String addres : address) {
            if (addres.matches(ParserPattern.REGION_REGEXP.toString())) {
                region = addres;
                break;
            }
        }
        return region;
    }

    private static String parseCity(String[] address) {
        String city = "";
        for (String addres : address) {
            if (addres.matches(ParserPattern.CITY_REGEXP.toString())) {
                city = addres;
                break;
            }
        }
        return city;
    }

    private static String parseDistrict(String[] address) {
        String district = "";
        for (String addres : address) {
            if (addres.matches(ParserPattern.DISTRICT_REGEXP.toString())) {
                district = addres;
                break;
            }
        }
        return district;
    }

    private static String parseQuarter(String[] address) {
        String quarter = "";
        for (int i = 0; i < address.length - 1; i++) {
            if (address[i].matches(ParserPattern.QUARTER_REGEXP.toString())) {
                quarter = address[i];
                break;
            }
        }
        return quarter;
    }

    private static String parseStreet(String[] address) {
        String street = "";
        for (int i = address.length - 1; i >= 0; i--) { // Улица чаще всего находится на предпоследнем столбце, ищем с конца
            if (address[i].matches(ParserPattern.STREET_REGEXP.toString())) {
                street = address[i];
                break;
            }
        }
        return street;
    }

    private static String parseHouse(String[] address) {
        String house = "";
        for (int i = address.length - 1; i >= 0; i--) { //Дом чаще всего находится на последнем столбце, ищем с конца
            if (address[i].matches(ParserPattern.HOUSE_REGEXP.toString())) {
                house = address[i];
                break;
            }
        }
        return house;
    }
}
