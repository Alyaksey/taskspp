import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task4Test {
    int n;
    double result;

    public Task4Test(int n, double result) {
        this.n = n;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {1, 0.5},
                {2, 0.25},
                {3, 0.45},
                {4, 0.46785714285},
        });
    }

    @Test
    public void calcSum() {
        assertEquals(result, Task4.calcSum(n), 0.0001);
    }
}