import java.util.Arrays;
import java.util.Scanner;

public class Task15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt() - 1;
        int v = scanner.nextInt() - 1;
        int r = scanner.nextInt();
        int[][] busTrips = new int[r][4];
        for (int i = 0; i < r; i++) {
            busTrips[i][0] = scanner.nextInt() - 1;
            busTrips[i][1] = scanner.nextInt();
            busTrips[i][2] = scanner.nextInt() - 1;
            busTrips[i][3] = scanner.nextInt();
        }
        int[] dist = new int[n];
        boolean[] used = new boolean[n];
        int INF = 1000000;
        Arrays.fill(dist, INF);
        dist[d] = 0;
        int w;
        int min;
        while (true) {
            w = -1;
            min = INF;
            for (int i = 0; i < n; i++) {
                if (!used[i] && dist[i] < min) {
                    min = dist[i];
                    w = i;
                }
            }
            if (w < 0)
                break;
            for (int i = 0; i < r; i++) {
                if (busTrips[i][0] == w && !used[busTrips[i][2]] && busTrips[i][1] >= dist[w] &&
                        dist[busTrips[i][2]] > busTrips[i][3]) {
                    dist[busTrips[i][2]] = busTrips[i][3];
                }
            }
            used[w] = true;
        }
        if (dist[v] == INF)
            System.out.println(-1);
        else
            System.out.println(dist[v]);
    }
}
