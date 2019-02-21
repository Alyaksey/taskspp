package Task19;

import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentTest {
    private List<Student> students;

    public StudentTest() {
        students = new ArrayList<>();
    }

    @Before
    public void addStudents() {
        try {
            Path filePath = Paths.get("src\\test\\resources\\in.txt");
            Charset charset = Charset.forName("ISO-8859-1");
            List<String> lines = Files.readAllLines(filePath, charset);
            for (String line : lines) {
                students.add(Student.parseStudent(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllAverages() {
        double[] averages = {3.7, 4.3, 5.0, 4.3, 5.0};
        assertArrayEquals(averages, Student.getAllAverages(students), 0.000000001);
    }

    @Test
    public void getGoodStudents() {
        String expectedString = "[Antonov Anton, Konstantinov Nikolay, Kuznetsov Ivan]";
        assertEquals(expectedString, Student.getGoodStudents(students).toString());
    }

    @Test
    public void getBestStudents() {
        String expectedString = "[Antonov Anton, Kuznetsov Ivan]";
        assertEquals(expectedString, Student.getBestStudents(students).toString());
    }

    @Test
    public void sortStudents() {
        String expectedString = "[Kuznetsov Ivan 5 5 5, Antonov Anton 5 5 5, Konstantinov Nikolay 4 4 5, Petrov Sergey 5 3 5, Ivanov Vasiliy 4 3 4]";
        Student.sortStudents(students);
        assertEquals(expectedString, students.toString());
    }

    @Test
    public void getThreeAverages() {
        double[] threeAverages = {4.6, 4.0, 4.8};
        assertArrayEquals(threeAverages, Student.getThreeAverages(students), 0.00000000001);
    }
}