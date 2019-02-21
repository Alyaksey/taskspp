import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Edge[] e = new Edge[m];
        for (int i = 0; i < m; i++) {
            e[i] = new Edge(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt());
        }
        int INF = -100000;
        int[] d = new int[n];
        Arrays.fill(d, INF);
        d[0] = 0;
        int x = -1;
        for (int i = 0; i < n; i++) {
            x = -1;
            for (int j = 0; j < m; j++) {
                if (d[e[j].a] > INF) {
                    if (d[e[j].b] < d[e[j].a] + e[j].cost) {
                        d[e[j].b] = d[e[j].a] + e[j].cost;
                        x = 1;
                    }
                }
            }
        }
        if (d[n - 1] == INF)
            System.out.println(":(");
        else {
            if (x == 1)
                System.out.println(":)");
            else
                System.out.println(d[n-1]);
        }
    }
}
