import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        String digits = new Scanner(System.in).nextLine();
        int counter = 0;
        int sum;
        while (digits.length() != 1){
            sum = 0;
            for (int i = 0; i < digits.length(); i++) {
                sum +=Character.getNumericValue(digits.charAt(i));
            }
            digits = Integer.toString(sum);
            counter++;
        }
        System.out.println(counter);
    }
}
