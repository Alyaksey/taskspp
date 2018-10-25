import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Puddles2DTest {
    int[] walls;
    int result;

    public Puddles2DTest(int[] walls, int result) {
        this.walls = walls;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {new int[]{5, 0, 0, 5}, 0},
                {new int[]{0, 0, 0, 0}, 0},
                {new int[]{1, 2, 3, 2, 3, 1, 4}, 3},
                {new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, 0},
                {new int[]{6, 7, 6}, 0},
                {new int[]{1, 2, 3, 4, 3, 2, 1}, 0},
                {new int[]{2, 7, 2, 7, 4, 7, 1, 7, 3, 7}, 18},
                {new int[]{6, 7, 7, 4, 3, 2, 1, 5, 2}, 10},
                {new int[]{2, 5, 1, 2, 3, 4, 7, 7, 6}, 10},
                {new int[]{6, 1, 4, 6, 7, 5, 1, 6, 4}, 13},
                {new int[]{4, 0, 0, 4, 0, 4, 0, 0, 4, 2}, 0},
        });
    }

    @Test
    public void findVolume() {
        assertEquals(result, Puddles2D.findVolume(walls));
    }
}