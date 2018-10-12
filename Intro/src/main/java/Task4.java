import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        if (n <= 10 || n > 21)
            System.out.println(0);
        else if (n == 20)
            System.out.println(15);
        else
            System.out.println(4);
    }
}
