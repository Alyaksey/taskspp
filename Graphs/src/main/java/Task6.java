import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x1 = scanner.nextInt() - 1;
        int y1 = scanner.nextInt() - 1;
        int x2 = scanner.nextInt() - 1;
        int y2 = scanner.nextInt() - 1;
        int[] dx = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};
        int[][] d = new int[n][n];
        Arrays.fill(d, -1);
        d[x1][y1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x1);
        queue.offer(y1);
        int ux;
        int uy;
        int vx;
        int vy;
        while (!queue.isEmpty()) {
            ux = queue.poll();
            uy = queue.poll();
            for (int i = 0; i < 8; i++) {
                vx = ux + dx[i];
                vy = uy + dy[i];
                if (vx >= 0 && vx < n && vy >= 0 && vy < n && d[vx][vy] == -1) {
                    d[vx][vy] = d[ux][uy] + 1;
                    queue.offer(vx);
                    queue.offer(vy);
                }
            }
        }
        System.out.println(d[x2][y2]);
        int distance = d[x2][y2];
        ux = x2;
        uy = y2;
        int[][] way = new int[d[x2][y2]+1][2];
        for (int i = d[x2][y2]; i >= 0; i--) {
            way[i][0] = ux;
            way[i][1] = uy;
            for (int j = 0; j < 8; j++) {
                vx = ux + dx[j];
                vy = uy + dy[j];
                if (vx >= 0 && vx < n && vy >= 0 && vy < n && d[vx][vy] == distance - 1){
                    ux = vx;
                    uy = vy;
                    distance--;
                    break;
                }
            }
        }
        for (int i = 0; i < way.length; i++) {
            for (int j = 0; j < way[i].length; j++) {
                System.out.printf("%d ", way[i][j]+1);
            }
            System.out.println();
        }
    }
}
