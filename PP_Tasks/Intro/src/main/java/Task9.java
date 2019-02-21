import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextInt();
        int[] t = new int[(int)n];
        long[] buffArray = new long[21];
        long count = 0;
        for (int i = 0; i < n; i++) {
            t[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            buffArray[10 + t[i]]++;
        }
        for (int i = 0; i < 10; i++) {
            count += buffArray[i] * buffArray[20 - i];
        }
        count += (buffArray[10] - 1) * buffArray[10] / 2;
        System.out.println(count);
    }
}
