import java.util.Arrays;

public class Task11 {
    public static void main(String[] args) {
    }

    public static String getShiftedArray(int[] array) {
        int[] shiftedArray = new int[array.length];
        for (int i = 0; i < array.length - 1; i++) {
            shiftedArray[i + 1] = array[i];
        }
        shiftedArray[0] = array[array.length - 1];
        return Arrays.toString(shiftedArray);
    }
}
