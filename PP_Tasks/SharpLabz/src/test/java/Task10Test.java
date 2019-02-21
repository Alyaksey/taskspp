import org.junit.Test;

import static org.junit.Assert.*;

public class Task10Test {

    @Test
    public void getMultiplesOfThree() {
        int[] array = {10, 6, 10, 3, 7, 333, 11, 24};
        assertEquals(4, Task10.getMultiplesOfThree(array));
    }

    @Test
    public void calcSumOfPositives() {
        int[] array = {-10, -78, 123, 5, -2, 4, 5};
        assertEquals(137, Task10.calcSumOfPositives(array));
    }

    @Test
    public void findMaxPositiveIndex() {
        int[] array = {-1000, 200, 100, 1, -2000};
        assertEquals(1, Task10.findMaxPositiveIndex(array));
    }

    @Test
    public void isZero() {
        int[] firstArray = {123, 3, 5, 7, 3, 4, 5};
        assertEquals(false, Task10.isZero(firstArray));
        int[] secondArray = {0, 1, 2, 3, 4};
        assertEquals(true, Task10.isZero(secondArray));
    }

    @Test
    public void getEvens() {
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12};
        assertEquals("[2, 4, 6, 8, 10, 12]", Task10.getEvens(array).toString());
    }
}