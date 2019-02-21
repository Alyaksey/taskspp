import java.util.*;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] table = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table[i][j] = scanner.nextInt();
            }
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int[][] d = new int[n][m];
        Queue<Cell> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (table[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new Cell(i, j, 0));
                } else
                    d[i][j] = -1;
            }
        }
        int ux;
        int uy;
        Cell currentCell;
        while (!queue.isEmpty()) {
            currentCell = queue.poll();
            for (int i = 0; i < 4; i++) {
                ux = currentCell.getX() + dx[i];
                uy = currentCell.getY() + dy[i];
                if (ux >= 0 && ux < n && uy >= 0 && uy < m && d[ux][uy] == -1) {
                    d[ux][uy] = d[currentCell.getX()][currentCell.getY()] + 1;
                    queue.offer(new Cell(ux,uy,d[ux][uy]));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d ", d[i][j]);
            }
            System.out.println();
        }
    }
}

class Cell implements Comparable<Cell> {
    int x;
    int y;
    int distance;

    public Cell() {
    }

    public Cell(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int compareTo(Cell other) {
        return distance - other.distance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
