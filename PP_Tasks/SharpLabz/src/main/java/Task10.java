import java.util.ArrayList;

public class Task10 {
    public static void main(String[] args) {
        int[] array = new int[1000000];
        initArray(array);
    }

    public static int Random(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static void initArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Random(-50, 50);
        }
    }

    public static int getMultiplesOfThree(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 3 == 0)
                count++;
        }
        return count;
    }

    public static int calcSumOfPositives(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0)
                sum += array[i];
        }
        return sum;
    }

    public static int findMaxPositiveIndex(int[] array) {
        int indexOfMax = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[indexOfMax])
                indexOfMax = i;
        }
        return indexOfMax;
    }
    public static boolean isZero(int[] array){
        boolean bool = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0)
                bool = true;
        }
        return bool;
    }
    public static ArrayList<Integer> getEvens(int[] array){
        ArrayList<Integer> evens = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0)
                    evens.add(new Integer(array[i]));
        }
        return evens;
    }
}
