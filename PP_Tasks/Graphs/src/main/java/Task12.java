import java.util.Scanner;

public class Task12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int firstNumber;
        int secondNumber;
        int index = -2;
        int max = -1;
        for (int i = 0; i < n; i++) {
            firstNumber = scanner.nextInt();
            secondNumber = scanner.nextInt();
            if (firstNumber > max && secondNumber == 1) {
                max = firstNumber;
                index = i;
            }
        }
        System.out.println(index + 1);
    }
}
