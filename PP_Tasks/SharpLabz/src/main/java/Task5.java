public class Task5 {
    public static void main(String[] args) {
    }
    public static double calcSum(int n){
        double sum = 0.0;
        double buffValue = 0;
        for (int i = 1; i <= n; i++) {
            buffValue += i;
            sum += 1/buffValue;
        }
        return sum;
    }
}
