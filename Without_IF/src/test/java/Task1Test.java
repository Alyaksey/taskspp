import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Task1Test {
    int[] array;
    int result;

    public Task1Test(int[] array, int result) {
        this.array = array;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 5},
                {new int[]{-1, -2, -3, -4, -5, -6, -7, -8, -9, 0}, 5},
                {new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 0}, 5},
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 10},
                {new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, 0},
                {new int[]{11, 32, -43, 54, -76, 54, -123, -77, 10, 56}, 4},
                {new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0},
        });

    }

    @Test
    public void countOdds() {
        assertEquals(result, Task1.countOdds(array));
    }
}