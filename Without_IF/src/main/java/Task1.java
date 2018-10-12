public class Task1 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int count = 0;
        for (int item : array) {
            count += item % 2;
        }
        System.out.println(count);
    }
}
