import java.util.Scanner;

public class TaskZ {
    public static void main(String[] args) {
        int tripsQuantity = new Scanner(System.in).nextInt();
        int[] tripsPerTicket = {60, 20, 10, 5, 1};//Кол-во поездок i-го билета
        int[] benefitTrips = {36, 18, 9, 5, 1};//Кол-во поездок, начиная с которых выгоднее купить i-ый билет
        int[] minTickets = new int[5];//Кол-во i-ых билетов, которые будет купить выгоднее всего
        for (int i = 0; i < tripsPerTicket.length; i++) {
            minTickets[i] = tripsQuantity / tripsPerTicket[i];
            tripsQuantity %= tripsPerTicket[i];
            if (tripsQuantity >= benefitTrips[i]) {
                minTickets[i]++;
                tripsQuantity /= tripsPerTicket[i];
            }
        }
        System.out.println(String.format("%d %d %d %d %d", minTickets[4], minTickets[3], minTickets[2],
                minTickets[1], minTickets[0]));
    }
}
