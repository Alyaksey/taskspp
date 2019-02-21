import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];
        int[] countArray = new int[101];
        int sticks = 0;
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            countArray[a[i]]++;
        }
        for (int aCountArray : countArray) {
            sticks += aCountArray / 2;
        }
        System.out.println(sticks / 2);
    }
}
