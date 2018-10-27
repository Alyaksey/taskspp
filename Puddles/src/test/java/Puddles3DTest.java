import Puddles3D.Puddles3D;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Puddles3DTest {
    int[][] walls;
    int result;

    public Puddles3DTest(int[][] walls, int result) {
        this.walls = walls;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {new int[][]{
                        {5, 5, 5, 5},
                        {5, 0, 0, 5},
                        {5, 5, 5, 5}}, 0},
                {new int[][]{
                        {5, 5, 5, 5},
                        {5, 5, 0, 5},
                        {5, 5, 5, 5}}, 0},
                {new int[][]{
                        {6, 5, 9},
                        {3, 4, 7}}, 0},
                {new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1},
                        {1, 9, 0, 0, 0, 0, 0, 0, 0, 9, 1},
                        {1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, 0},
                {new int[][]{
                        {2, 5, 10, 10},
                        {7, 2, 4, 10},
                        {17, 3, 24, 0},
                        {10, 13, 6, 8}}, 6},
                {new int[][]{
                        {2, 23, 10, 10},
                        {7, 2, 4, 10},
                        {17, 3, 24, 0},
                        {10, 13, 6, 8}}, 12},
                {new int[][]{
                        {5, 0, 5, 5},
                        {5, 0, 0, 5},
                        {5, 5, 5, 5}}, 0},
                {new int[][]{
                        {5, 0, 5, 5},
                        {5, 5, 0, 5},
                        {5, 5, 5, 5}}, 0},
                {new int[][]{
                        {0, 0, 5, 0},
                        {5, 5, 0, 5},
                        {0, 5, 5, 0}}, 0},
                {new int[][]{
                        {12, 13, 0, 12, 30},
                        {13, 4, 13, 12, 27},
                        {13, 8, 0, 12, 16},
                        {12, 13, 12, 12, 19},
                        {56, 12, 43, 50, 12}}, 4},
                {new int[][]{
                        {12, 10000, 90, 12, 30},
                        {13, 4, 13, 12, 27},
                        {13, 8, 10, 12, 16},
                        {12, 13, 12, 12, 19},
                        {56, 12, 43, 50, 12}}, 14},
                {new int[][]{
                        {12, 10000, 90, 12, 30},
                        {13, 4, 13, 12, 27},
                        {13, 0, 0, 0, 16},
                        {12, 13, 12, 12, 19},
                        {56, 12, 43, 50, 12}}, 0},
        });
    }

    @Test
    public void findVolume() {
        assertEquals(result, Puddles3D.findVolume(walls));
    }
}