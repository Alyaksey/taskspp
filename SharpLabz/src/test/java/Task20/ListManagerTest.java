package Task20;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListManagerTest {
    private ListManager lm;

    public ListManagerTest() {
        lm = new ListManager(new ArrayList<>());
    }

    @Before
    public void fillList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a   b   c");
        list.add("a,,,b...c");
        list.add("123");
        lm = new ListManager(list);
    }

    @Test
    public void addString() {
        String[] arr = {"123", "456"};
        lm.addString(arr);
        assertEquals("[a   b   c, a,,,b...c, 123, 123, 456]", lm.toString());
    }

    @Test
    public void addStringsAtBeginning() {
        String[] arr = {"123", "456"};
        lm.addStringsAtBeginning(arr);
        assertEquals("[123, 456, a   b   c, a,,,b...c, 123]", lm.toString());
    }

    @Test
    public void addStringsByIndex() {
        String[] arr = {"123", "456"};
        lm.addStringsByIndex(arr, 1);
        assertEquals("[a   b   c, 123, 456, a,,,b...c, 123]", lm.toString());
    }

    @Test
    public void showListInfo() throws NoSuchFieldException, IllegalAccessException {
        assertEquals("[a   b   c, a,,,b...c, 123]\nКол-во элементов: 3\nЕмкость: 10", lm.showListInfo());
    }

    @Test
    public void deleteExtraSpaces() {
        lm.deleteExtraSpaces();
        assertEquals("[a b c, a,,,b...c, 123]", lm.toString());
    }

    @Test
    public void deleteExtraPunctMarks() {
        lm.deleteExtraPunctMarks();
        assertEquals("[a   b   c, a,b.c, 123]", lm.toString());
    }

    @Test
    public void addSpaces() {
        lm.addSpaces();
        assertEquals("[a   b   c, a, , , b. . . c, 123]", lm.toString());
    }

    @Test
    public void changeNumbers() {
        lm.changeNumbers();
        assertEquals("[a   b   c, a,,,b...c, 0,123]", lm.toString());
    }
}