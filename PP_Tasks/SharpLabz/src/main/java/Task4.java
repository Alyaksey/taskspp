public class Task4 {
    public static void main(String[] args) {
    }
    public static double calcSum(int n) {
        double sum = 0.0;
        for (int k = 1; k <= 2 * n; k++) {
            if (k % 3 != 0) {
                sum += Math.pow(-1, k + 1) / k;
            }
        }
        return sum;
    }
}
