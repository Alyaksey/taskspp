import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];
        int even = 0;
        int odd = 0;
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0)
                even++;
            else
                odd++;
        }
        System.out.println((odd % 2 == 1) ? odd : even);
    }
}
