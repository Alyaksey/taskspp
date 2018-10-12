public class Task3 {
    public static void main(String[] args) {
        doubleArg("5.5");
        doubleArg("abcd");
    }
    public static void doubleArg(String arg) {
        try {
            double number = Double.parseDouble(arg);
            number *= 2;
            System.out.println(number);
        } catch (NumberFormatException e) {
            StringBuilder doubleString = new StringBuilder();
            for (int i = 0; i <arg.length(); i++) {
                doubleString.append(arg.charAt(i)).append(arg.charAt(i));
            }
            System.out.println(doubleString);
        }
    }
}
