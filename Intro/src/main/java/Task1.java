import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        String fString;
        String sString;
        Scanner scan = new Scanner(System.in);
        fString = scan.nextLine();
        sString = scan.nextLine();
        if (fString.compareToIgnoreCase(sString) < 0)
            System.out.println(-1);
        else if (fString.compareToIgnoreCase(sString) > 0)
            System.out.println(1);
        else System.out.println(0);
    }
}
