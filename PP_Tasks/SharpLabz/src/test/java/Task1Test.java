import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Task1Test {
    int x;
    int result;

    public Task1Test(int x, int result) {
        this.x = x;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {1,1},
                {2,64},
                {-1,1},
                {-2,64},
                {0,0},
        });
    }
    @Test
    public void calcPowSix() {
        assertEquals(result,Task1.calcPowSix(x));
    }
}