import java.util.Arrays;
import java.util.Scanner;

public class Task13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int s = scanner.nextInt() - 1;
        int f = scanner.nextInt() - 1;
        int INF = 10000000;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
                if (graph[i][j] == -1)
                    graph[i][j] = INF;
            }
        }
        int[] d = new int[n];
        int[] used = new int[n];
        Arrays.fill(d, INF);
        d[s] = 0;
        int min;
        int w = -1;
        for (int i = 0; i < n; i++) {
            min = INF;
            for (int j = 0; j < n; j++)
                if (used[j] == 0 && d[j] < min) {
                    min = d[j];
                    w = j;
                }
            if (min == INF)
                break;
            for (int j = 0; j < n; j++)
                if (used[j] == 0)
                    d[j] = Math.min(d[j], d[w] + graph[w][j]);
            used[w] = 1;
        }
        if (d[f-1] == INF)
            d[f-1] = -1;
        System.out.println(d[f]);
    }
}
