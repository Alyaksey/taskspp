import java.util.Arrays;

public class Task13 {
    public static void main(String[] args) {
        int[][] array = new int[3][3];
        initArray(array);
        printArray(array);
        System.out.println();
        System.out.println(Arrays.toString(findMinElement(array)));
        System.out.println();
        System.out.println(findAverage(array));
    }

    public static int Random(int min, int max) {
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

    public static int[] findMinElement(int[][] array) {
        int min = array[0][0];
        int[] minElems = new int[array[0].length];
        for (int i = 0; i < array[0].length; i++) {
            min = array[0][i];
            for (int j = 0; j < array.length; j++) {
                if (min > array[j][i])
                    min = array[j][i];
            }
            minElems[i] = min;
        }
        return minElems;
    }

    public static double findAverage(int[][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .filter(x -> x < 0)
                .average()
                .getAsDouble();
    }
}
