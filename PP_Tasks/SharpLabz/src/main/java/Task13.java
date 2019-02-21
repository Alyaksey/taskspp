import java.util.Arrays;

public class Task13 {

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
        for (int[] anArray : array) {
            for (int anAnArray : anArray) {
                System.out.print(anAnArray + "\t");
            }
            System.out.println();
        }
    }

    public static int[] findMinElement(int[][] array) {
        int min;
        int[] minElems = new int[array[0].length];
        for (int i = 0; i < array[0].length; i++) {
            min = array[0][i];
            for (int[] anArray : array) {
                if (min > anArray[i])
                    min = anArray[i];
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
