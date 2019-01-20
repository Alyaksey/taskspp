import java.util.Arrays;
import java.util.Scanner;

public class Task14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int fCity;
        int sCity;
        int INF = 1000000;
        int[][] graph = new int[n][n];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int i = 0; i < m; i++) {
            fCity = scanner.nextInt() - 1;
            sCity = scanner.nextInt() - 1;
            graph[fCity][sCity] = cost[fCity];
            graph[sCity][fCity] = cost[sCity];
        }
        int[] d = new int[n];
        int[] used = new int[n];
        Arrays.fill(d, INF);
        d[0] = 0;
        int min;
        int w;
        for (int i = 0; i < n; i++) {
            min = INF;
            w = -1;
            for (int j = 0; j < n; j++) {
                if (used[j] == 0 && d[j] < min){
                    min  = d[j];
                    w = j;
                }
            }
            if (w < 0)
                break;
            for (int j = 0; j < n; j++) {
                if (used[j] == 0)
                    d[j] = Math.min(d[j], d[w] + graph[w][j]);
            }
            used[w] = 1;
        }
        if (d[n-1] == INF)
            System.out.println(-1);
        else
            System.out.println(d[n-1]);
    }
}
