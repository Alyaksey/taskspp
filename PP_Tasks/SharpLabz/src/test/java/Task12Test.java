import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Task12Test {
    int[] fArray;
    int[] sArray;
    String result;

    public Task12Test(int[] fArray, int[] sArray, String result) {
        this.fArray = fArray;
        this.sArray = sArray;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}, "[1, 1, 2, 2, 3, 3, 4, 4]"},
                {new int[]{1, 2}, new int[]{3, 4}, "[1, 2, 3, 4]"},
                {new int[]{1, 1}, new int[]{1, 1}, "[1, 1, 1, 1]"},
                {new int[]{1, 1}, new int[] {0, 0}, "[0, 0, 1, 1]"},
                {new int[]{-4, -3, -2, -1}, new int[]{-5,-4,-3}, "[-5, -4, -4, -3, -3, -2, -1]"},
        });

    }
    @Test
    public void getMergedArray() {
        assertEquals(result,Task12.getMergedArray(fArray,sArray));
    }
}