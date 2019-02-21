import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Task16Test {
    StringBuilder sb;
    StringBuilder result;

    public Task16Test(StringBuilder sb, StringBuilder result) {
        this.sb = sb;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {new StringBuilder("123456"), new StringBuilder("123456")},
                {new StringBuilder("123456;_"), new StringBuilder("123456__")},
                {new StringBuilder("abcdefgh1234567?!\""), new StringBuilder("abcdefgh1234567?!\"")},
                {new StringBuilder("abcdefgh1234567?!;\""), new StringBuilder("abcdefgh1234567?!_\"")},
                {new StringBuilder("______"), new StringBuilder("______")},
                {new StringBuilder(";;;;"), new StringBuilder("____")},
        });
    }

    @Test
    public void replaceChars() {
        assertEquals(result.toString(), Task16.replaceChars(sb).toString());
    }
}