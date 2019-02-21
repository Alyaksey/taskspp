import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int x1 = scan.nextInt();
        int y1 = scan.nextInt();
        int x2 = scan.nextInt();
        int y2 = scan.nextInt();
        int x;
        int y;
        x = x1;
        y = y1;
        x1 = x + y;
        y1 = y - x;
        x = x2;
        y = y2;
        x2 = x + y;
        y2 = y - x;
        a *= 2;
        b *= 2;
        x1 = x1 / a + ((x1 > 0) ? 1 : 0);
        x2 = x2 / a + ((x2 > 0) ? 1 : 0);
        y1 = y1 / b + ((y1 > 0) ? 1 : 0);
        y2 = y2 / b + ((y2 > 0) ? 1 : 0);
        System.out.println(Math.max(Math.abs(y2-y1), Math.abs(x2-x1)));
    }
}
