import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstCoord = scanner.next();
        String secondCoord = scanner.next();
        int x1 = firstCoord.charAt(0) - 'a';
        int y1 = firstCoord.charAt(1) - '0' - 1;
        int x2 = secondCoord.charAt(0) - 'a';
        int y2 = secondCoord.charAt(1) - '0' - 1;
        int[] dx = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};
        int[][] d = new int[8][8];
        Arrays.fill(d,-1);
        d[y1][x1] = 0;
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
                if (vx >= 0 && vx < 8 && vy >= 0 && vy < 8 && d[vy][vx] == -1) {
                    d[vy][vx] = d[uy][ux] + 1;
                    queue.offer(vx);
                    queue.offer(vy);
                }
            }
        }
        if (d[y2][x2] % 2 != 0)
            System.out.println("-1");
        else System.out.println(d[y2][x2] / 2);
    }
}
