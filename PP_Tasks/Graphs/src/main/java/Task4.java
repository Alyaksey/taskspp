import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = scanner.nextInt();
            }
        }
        int longestDistance = 3000;
        int[] shortestWay = new int[3];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (longestDistance > distances[i][j] + distances[j][k] + distances[i][k]){
                        longestDistance = distances[i][j] + distances[j][k] + distances[i][k];
                        shortestWay[0] = i+1;
                        shortestWay[1] = j+1;
                        shortestWay[2] = k+1;
                    }
                }
            }
        }
        System.out.printf("%d %d %d", shortestWay[0], shortestWay[1], shortestWay[2]);
    }
}
