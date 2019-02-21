public class Task1 {
    public static void main(String[] args) {
    }

    public static int countOdds(int[] array) {
        int counter = 0;
        for (int item : array) {
            counter += Math.abs(item % 2);
        }
        return counter;
    }
}
