public class Task8 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            getOddsQuantity(111);
        }
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println(timeSpent + " мс");
    }
    public static int getOddsQuantity(int n){
        int count = 0;
        while (n > 0){
            count += n % 2;
            n /= 10;
        }
        return count;
    }
}
