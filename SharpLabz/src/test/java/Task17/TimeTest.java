package Task17;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

public class TimeTest {
    private Time time;

    public TimeTest() {
        time = new Time();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHours() {
        time.setHours(23);
        assertEquals(23, time.getHours());
        time.setHours(23423);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMinutes() {
        time.setMinutes(23);
        assertEquals(23, time.getMinutes());
        time.setMinutes(1213);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setSeconds() {
        time.setSeconds(45);
        assertEquals(45, time.getSeconds());
        time.setMinutes(1213);
    }

    @Test
    public void changeHours() {
        time.setHours(22);
        time.changeHours(49);
        assertEquals(23, time.getHours());
        time.changeHours(27);
        assertEquals(2, time.getHours());
        time.changeHours(-3);
        assertEquals(23, time.getHours());
    }

    @Test
    public void changeMinutes() {
        time.setMinutes(59);
        time.changeMinutes(23);
        assertEquals(22, time.getMinutes());
        assertEquals(1, time.getHours());
        time.changeMinutes(40);
        assertEquals(2, time.getMinutes());
        assertEquals(2, time.getHours());
        time.changeMinutes(-41);
        assertEquals(21, time.getMinutes());
        assertEquals(1, time.getHours());
    }

    @Test
    public void changeSeconds() {
        time.setSeconds(34);
        time.changeSeconds(2);
        assertEquals(36, time.getSeconds());
        time.changeSeconds(100);
        assertEquals(16, time.getSeconds());
        assertEquals(2, time.getMinutes());
        time.changeSeconds(-40);
        assertEquals(36, time.getSeconds());
        assertEquals(1, time.getMinutes());
    }
}