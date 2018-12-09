import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Addresses\\src\\main\\resources\\Addr.csv"), "Cp1251"));
        String line;
        reader.readLine();
        ArrayList<String> list = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        AddressesManager am = new AddressesManager(list);
        am.processAddresses();
        am.writeValidAddressesToCSV();
        am.writeWrongAddressesToCSV();
    }
}

