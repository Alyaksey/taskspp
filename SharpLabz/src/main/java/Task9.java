public class Task9 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            reverse(123456);
        }
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println(timeSpent + " мс");
    }
    public static int reverse(int n){
        int result = 0;
        while (n > 0){
            result = result * 10 + n % 10;
            n /= 10;
        }
        return result;
    }
}
