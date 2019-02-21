import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Task11Test {
    int[] array;
    String result;

    public Task11Test(int[] array, String result) {
        this.array = array;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4}, "[4, 1, 2, 3]"},
                {new int[]{1, 1, 1, 1}, "[1, 1, 1, 1]"},
                {new int[]{1, 3, 6, 9, 12}, "[12, 1, 3, 6, 9]"},
                {new int[]{1, 1}, "[1, 1]"},
                {new int[]{-1, -2, -3, -4}, "[-4, -1, -2, -3]"},
        });

    }

    @Test
    public void getShiftedArray() {
        assertEquals(result, Task11.getShiftedArray(array));
    }
}