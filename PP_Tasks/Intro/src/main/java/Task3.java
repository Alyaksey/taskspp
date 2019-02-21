import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int currentCapacity = 0;
        int maxCapacity = 0;
        int passengersIn;
        int passengersOut;
        for (int i = 0; i < n; i++) {
            passengersOut = scan.nextInt();
            passengersIn = scan.nextInt();
            currentCapacity -= passengersOut;
            currentCapacity += passengersIn;
            if (maxCapacity < currentCapacity)
                maxCapacity = currentCapacity;
        }
        System.out.println(maxCapacity);
    }
}
