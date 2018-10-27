import org.junit.Test;

import static org.junit.Assert.*;

public class Task13Test {

    @Test
    public void findMinElement() {
        int[][] array = {
                {123, -2, 3, 0},
                {2, -5, -7, 0},
                {0, -1, -9, 0}};
        assertArrayEquals(new int[] {0,-5,-9,0}, Task13.findMinElement(array));
    }

    @Test
    public void findAverage() {
        int[][] array = {
                {123, -2, 3, 0},
                {2, -5, -7, 0},
                {0, -1, -9, 0}};
        assertEquals(-4.8,Task13.findAverage(array), 0.00000000001);
    }
}