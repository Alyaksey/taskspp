import java.sql.SQLOutput;
import java.util.Random;

public class Task13 {
    public static void main(String[] args) {
        int[][] array = new int[5][6];
        initArray(array);
        printArray(array);
        System.out.println();
        findMinElement(array);
        System.out.println();
        System.out.println(findAverage(array));
    }
    public static int Random(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static void initArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Random(-100, 100);
            }
        }
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void findMinElement(int[][] array) {
        int columns = array[array.length - 1].length;
        int[] maxElems = new int[columns];
        int min = array[0][0];
        for (int i = 0; i < array[0].length; i++) {
            min = array[0][i];
            for (int j = 0; j < array.length; j++) {
                if (min > array[j][i])
                    min = array[j][i];
            }
            System.out.print(min + "\t");
        }
    }

    public static double findAverage(int[][] array) {
        double average = 0.0;
        int count = 0;
        for (int[] arr : array) {
            for (int item : arr)
                if (item < 0) {
                    average += item;
                    count++;
                }
        }
        return average / count;
    }
}
