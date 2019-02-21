import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task9Test {
    int n;
    int result;

    public Task9Test(int n, int result) {
        this.n = n;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {123, 321},
                {111, 111},
                {321, 123},
                {1, 1},
                {0, 0}
        });
    }

    @Test
    public void reverse() {
        assertEquals(result,Task9.reverse(n));
    }
}