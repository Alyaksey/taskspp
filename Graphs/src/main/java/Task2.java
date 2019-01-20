import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] crossroads = new int[n];
        int m = scanner.nextInt();
        for (int i = 0; i < 2*m; i++) {
            crossroads[scanner.nextInt()-1]++;
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ",crossroads[i]);
        }
    }
}
