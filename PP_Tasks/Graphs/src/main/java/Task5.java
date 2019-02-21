import java.util.*;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] graph = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        int firstVertex = scanner.nextInt();
        int secondVertex = scanner.nextInt();
        int[] d = new int[n+1];
        Arrays.fill(d, -1);
        d[firstVertex] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(firstVertex);
        int vertex;
        while (!q.isEmpty()){
            vertex = q.poll();
            for (int i = 1; i <= n; i++) {
                if (graph[vertex][i] == 1 && d[i] == -1){
                    d[i] = d[vertex] + 1;
                    q.offer(i);
                }
            }
        }
        System.out.println(d[secondVertex]);
        int[] way = new int[d[secondVertex] + 1];
        vertex = secondVertex;
        for (int i = d[secondVertex]; i >= 0; i--) {
            way[i] = vertex;
            for (int j = 1; j <= n; j++) {
                if (graph[vertex][j] == 1 && d[j] == d[vertex] - 1){
                    vertex = j;
                    break;
                }
            }
        }
        for (int i = 0; i < way.length; i++) {
            System.out.printf("%d ", way[i]);
        }
    }
}
