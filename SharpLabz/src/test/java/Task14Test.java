import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class Task14Test {
    String str;
    int result;

    public Task14Test(String str, int result) {
        this.str = str;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {"Abcd, abcd  abcd.", 3},
                {"Abcd  abcd.", 2},
                {"Djkerwe, dkferw, sdfjkl, sdj prew.", 5},
                {"sdlfk, Qewere, DererS, AAAA.", 4},
                {" ", 0}
        });
    }

    @Test
    public void getWordsQuantity() {
        assertEquals(result, Task14.getWordsQuantity(str));
    }
}