public class Task6 {
    public static int calcSum(int n){
        int sum = 0;
        int buffValue = 1;
        for (int i = 1; i <= n; i++) {
            buffValue *= i;
            sum += buffValue;
        }
        return sum;
    }
}
