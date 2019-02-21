import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task8Test {
    int n;
    int result;

    public Task8Test(int n, int result) {
        this.n = n;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {111, 3},
                {222, 0},
                {123456, 3},
                {0, 0},
                {1, 1}
        });
    }

    @Test
    public void getOddsQuantity() {
        assertEquals(result, Task8.getOddsQuantity(n));
    }
}