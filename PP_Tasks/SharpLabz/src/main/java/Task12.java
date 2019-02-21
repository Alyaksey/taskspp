import java.util.Arrays;

public class Task12 {
    public static void main(String[] args) {
    }
    public static String getMergedArray(int[] a, int[] b){
        int[] mergedArray = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a.length && j < b.length)
            mergedArray[k++] = a[i] < b[j] ? a[i++] : b[j++];
        while (i < a.length)
            mergedArray[k++] = a[i++];
        while (j < b.length)
            mergedArray[k++] = b[j++];
        return Arrays.toString(mergedArray);
    }
}
