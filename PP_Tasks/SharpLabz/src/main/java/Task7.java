import java.util.ArrayList;

public class Task7 {
    public static String calcSquares(int n) {
        int number = 1;
        ArrayList<Integer> numbers = new ArrayList<>();
        while (number * number < n) {
            numbers.add(number*number);
            number++;
        }
        return numbers.toString();
    }
}
