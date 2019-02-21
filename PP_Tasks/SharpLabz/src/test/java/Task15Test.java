import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task15Test {
    String fString;
    String sString;
    boolean result;

    public Task15Test(String fString, String sString, boolean result) {
        this.fString = fString;
        this.sString = sString;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {"Abcdefgh","Abcd",true},
                {"ABCDE","abc", false},
                {"ABCDEFG", "DEF", true},
                {"abcdefg","cdeh", false},
                {"abcdefg","efg", true},
                {"      ","   ", true}
        });
    }

    @Test
    public void isSubstring() {
        assertEquals(result, Task15.isSubstring(fString, sString));
    }
}