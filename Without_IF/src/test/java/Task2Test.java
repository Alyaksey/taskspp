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
    Calendar date;
    String result;

    public Task2Test(Calendar date, String result) {
        this.date = date;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection dates() {
        return Arrays.asList(new Object[][]{
                {new GregorianCalendar(2018, Calendar.OCTOBER, 8), "будний"},
                {new GregorianCalendar(2018, Calendar.OCTOBER, 9), "будний"},
                {new GregorianCalendar(2018, Calendar.OCTOBER, 10), "будний"},
                {new GregorianCalendar(2018, Calendar.OCTOBER, 11), "будний"},
                {new GregorianCalendar(2018, Calendar.OCTOBER, 12), "будний"},
                {new GregorianCalendar(2018, Calendar.OCTOBER, 13), "выходной"},
                {new GregorianCalendar(2018, Calendar.OCTOBER, 14), "выходной"},
        });

    }

    @Test
    public void getDayOfWeek() {
        assertEquals(result, Task2.getDayOfWeek(date));
    }
}