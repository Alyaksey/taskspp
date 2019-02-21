import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task6Test {
    int n;
    int result;

    public Task6Test(int n, int result) {
        this.n = n;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {1, 1},
                {2, 3},
                {3, 9},
                {4, 33},
        });
    }

    @Test
    public void calcSum() {
        assertEquals(result, Task6.calcSum(n));
    }
}