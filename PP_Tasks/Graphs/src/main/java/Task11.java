import java.util.*;

public class Task11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] graph = new int[n][n];
        final int INF = 1000000;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
                if (graph[i][j] == 0 && i != j)
                    graph[i][j] = INF;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] < INF && graph[k][j] < INF)
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == INF)
                    res[i][j] = 0;
                else
                    res[i][j] = 1;
                for (int k = 0; k < n; k++) {
                    if (graph[k][k] < 0 && graph[i][k] < INF && graph[k][j] < INF)
                        res[i][k]=res[i][j]=res[k][j] = 2;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", res[i][j]);
            }
            System.out.println();
        }
    }
}
