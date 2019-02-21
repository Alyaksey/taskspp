import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task7Test {
    int n;
    String result;

    public Task7Test(int n, String result) {
        this.n = n;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {50, "[1, 4, 9, 16, 25, 36, 49]"},
                {5, "[1, 4]"},
                {8, "[1, 4]"},
                {12, "[1, 4, 9]"},
        });
    }

    @Test
    public void calcSquares() {
        assertEquals(result, Task7.calcSquares(n));
    }
}