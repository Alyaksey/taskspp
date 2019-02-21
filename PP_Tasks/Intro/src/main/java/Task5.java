import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int l = scan.nextInt();
        int importance = -1;
        while (l % k == 0){
            l /= k;
            importance++;
        }
        if (importance >= 0 && l == 1){
            System.out.println("YES");
            System.out.println(importance);
        }
        else
            System.out.println("NO");
    }
}
