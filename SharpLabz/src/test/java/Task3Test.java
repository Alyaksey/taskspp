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
    int m;
    int n;
    int p;
    int a;
    int b;
    int c;
    int d;
    int e;
    double result;
    public Task3Test(int m, int n, int p, int a, int b, int c, int d, int e, double result) {
        this.m = m;
        this.n = n;
        this.p = p;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.result = result;
    }


    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {1,1,1,1,1,1,1,1, -0.050742660435783106},
                {2,2,2,2,2,2,2,2, -0.012814035965791264},
                {3,3,3,3,3,3,3,3, -0.07864539062689267},
        });
    }
    @Test
    public void calcX() {
        assertEquals(result, Task3.calcX(m,n,p,a,b,c,d,e), 0.0000000001);
    }
}