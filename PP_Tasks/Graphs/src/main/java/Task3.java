import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] hills = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hills[i][j] = scanner.nextInt();
            }
        }
        int[] hillColor = new int[n];
        for (int i = 0; i < n; i++) {
            hillColor[i] = scanner.nextInt();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (hills[i][j] == 1 && hillColor[i] != hillColor[j])
                    count++;
            }
        }
        System.out.println(count);
    }
}
