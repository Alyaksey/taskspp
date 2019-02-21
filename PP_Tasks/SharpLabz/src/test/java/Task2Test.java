import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Task2Test {
    int naturalNumber;
    int result;

    public Task2Test(int naturalNumber, int result) {
        this.naturalNumber = naturalNumber;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {123, 2},
                {1, 0},
                {20, 2},
                {400, 0},
                {777, 7},
        });
    }

    @Test
    public void getTensQuantity() {
        assertEquals(result,Task2.getTensQuantity(naturalNumber));
    }
}