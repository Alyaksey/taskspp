public class Task3 {
    public static void main(String[] args) {
    }

    public static String doubleArg(String arg) {
        try {
            int number = Integer.parseInt(arg);
            number *= 2;
            return Integer.toString(number);
        } catch (NumberFormatException e) {
            try {
                double number = Double.parseDouble(arg);
                number *= 2;
                return Double.toString(number);
            } catch (NumberFormatException ex) {
                StringBuilder doubleString = new StringBuilder();
                for (int i = 0; i < arg.length(); i++) {
                    doubleString.append(arg.charAt(i)).append(arg.charAt(i));
                }
                return doubleString.toString();
            }
        }
    }
}
