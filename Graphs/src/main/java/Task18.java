import java.util.*;

public class Task18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Edge> e = new ArrayList<>();
        int cost;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost = scanner.nextInt();
                if (cost != 0 && cost != 10000)
                    e.add(new Edge(i, j, cost));
            }
        }
        int[] d = new int[n];
        int INF = 1000000;
        Arrays.fill(d, INF);
        d[0] = 0;
        int[] p = new int[n];
        Arrays.fill(p, -1);
        int x = -1;
        for (int i = 0; i < n; i++) {
            x = -1;
            for (int j = 0; j < e.size(); j++) {
                if (d[e.get(j).b] > d[e.get(j).a] + e.get(j).cost) {
                    d[e.get(j).b] = Math.max(-INF, d[e.get(j).a] + e.get(j).cost);
                    p[e.get(j).b] = e.get(j).a;
                    x = e.get(j).b;
                }
            }
        }
        if (x == -1)
            System.out.println("NO");
        else {
            int y = x;
            for (int i = 0; i < n; i++) {
                y = p[y];
            }
            List<Integer> path = new ArrayList<>();
            for (int cur = y; ; cur = p[cur]) {
                path.add(cur + 1);
                if (cur == y && path.size() > 1)
                    break;
            }
            Collections.reverse(path);
            System.out.println("YES");
            System.out.println(path.size());
            for (int i = 0; i < path.size(); i++) {
                System.out.printf("%d ", path.get(i));
            }
        }
    }
}
