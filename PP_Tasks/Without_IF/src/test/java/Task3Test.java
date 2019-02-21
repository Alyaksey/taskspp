import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Task3Test {
    String arg;
    String result;

    public Task3Test(String arg, String result) {
        this.arg = arg;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection arguments() {
        return Arrays.asList(new Object[][]{
                {"5", "10"},
                {"psuti", "ppssuuttii"},
                {"3.2", "6.4"},
                {"Alexxxey", "AAlleexxxxxxeeyy"},
                {"0", "0"},
                {" ", "  "},
                {"4.24", "8.48"},
        });
    }

    @Test
    public void doubleArg() {
        assertEquals(result, Task3.doubleArg(arg));
    }
}