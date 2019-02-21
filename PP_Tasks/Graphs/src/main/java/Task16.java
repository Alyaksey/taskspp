import java.util.Arrays;
import java.util.Scanner;

public class Task16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Edge[] e = new Edge[m];
        for (int i = 0; i < m; i++) {
            e[i] = new Edge(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt());
        }
        int INF = 30000;
        int[] d = new int[n];
        Arrays.fill(d, INF);
        d[0] = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < m; j++)
                if (d[e[j].a] < INF) {
                    d[e[j].b] = Math.min(d[e[j].b], d[e[j].a] + e[j].cost);
                }
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", d[i]);
        }
    }
}

class Edge {
    int a;
    int b;
    int cost;

    public Edge(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
}
