import java.io.*;
import java.util.*;

public class AddressesManager {
    private List<String> unprocessedAddresses; //Необработанные адреса
    private List<Address> normalizedAddresses; //Обработанные адреса

    public AddressesManager(List<String> unprocessedAddresses) {
        this.unprocessedAddresses = unprocessedAddresses;
        normalizedAddresses = new ArrayList<>();
    }

    public void processAddresses() {
        for (int i = 0; i < unprocessedAddresses.size(); i++) {
            //Разделяем строку по запятым, парсим значения и добавляем в лист обработанных адресов
            normalizedAddresses.add(AddressParser.getAddress(unprocessedAddresses.get(i).split(",")));
            AddressNormalizer.normalizeAddress(normalizedAddresses.get(i));
        }
    }

    public void showAddresses() {
        for (Address normalizedAddress : normalizedAddresses) {
            System.out.println(normalizedAddress);
        }
    }

    public void addAddress(Address address) {
        normalizedAddresses.add(address);
    }

    public void deleteAddress(int index) {
        normalizedAddresses.remove(index);
    }

    public List<String> getWrongAddresses() {
        List<String> wrongAddresses = new ArrayList<>();
        for (int i = 0; i < unprocessedAddresses.size(); i++) {
            if (!normalizedAddresses.get(i).isCorrect()) {
                wrongAddresses.add(new StringBuilder(unprocessedAddresses.get(i))
                        .append(',')
                        .append(normalizedAddresses.get(i).getIncorrectnesses().toString()
                                .replaceAll("\\[|\\]", "") //Удаляем квадратные скобки
                                .replaceAll(",", " ")).toString()); //Удаляем запятые
            }
        }
        return wrongAddresses;
    }

    public void writeValidAddressesToCSV() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("Addresses\\\\src\\\\main\\\\resources\\\\Valid.csv"));
        pw.write("Область,Регион,Город,Гор.район,Квартал,Улица,Дом");
        pw.write('\n');
        for (Address normalizedAddress : normalizedAddresses) {
            pw.write(normalizedAddress.toString());
            pw.write('\n');
        }
        pw.flush();
        pw.close();
    }

    public void writeWrongAddressesToCSV() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("Addresses\\\\src\\\\main\\\\resources\\\\Wrong.csv"));
        List<String> wrongAddresses = getWrongAddresses();
        pw.write("Область,Регион,Город,Гор.район,Квартал,Улица,Дом,Причины некорректности");
        pw.write('\n');
        for (String wrongAddress : wrongAddresses) {
            pw.write(wrongAddress);
            pw.write('\n');
        }
    }
}
