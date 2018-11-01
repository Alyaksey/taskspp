import java.util.Scanner;

public class TaskJ {
    public static void main(String[] args) {
        int number = new Scanner(System.in).nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append((number >> i) & 1);
        }
        System.out.println(sb.reverse());
    }
}
