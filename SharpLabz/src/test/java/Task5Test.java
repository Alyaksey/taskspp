import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task5Test {
    int n;
    double result;

    public Task5Test(int n, double result) {
        this.n = n;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {1, 1},
                {2, 1.33333333333},
                {3, 1.5},
                {4, 1.6},
        });
    }

    @Test
    public void calcSum() {
        assertEquals(result, Task5.calcSum(n), 0.0000000001);
    }
}