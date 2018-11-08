package Task18;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentManagerTest {
    private List<Student> students;
    private StudentManager sm;

    public StudentManagerTest() {
        students = new ArrayList<>();
    }

    @Before
    public void addStudents() {
        students.add(new Student("Антонов", Student.SPECIALTIES.ПО));
        students.add(new Student("Борисов", Student.SPECIALTIES.РПИС));
        students.add(new Student("Виноградов", Student.SPECIALTIES.УИТС));
        students.add(new Student("Ефимов", Student.SPECIALTIES.ПО));
        students.add(new Student("Королев", Student.SPECIALTIES.РПИС));
        students.add(new Student("Эдуардов", Student.SPECIALTIES.УИТС));
        sm = new StudentManager(students);
    }


    @Test
    public void getOneGroup() {
        Group[] expectedGroups = new Group[1];
        for (int i = 0; i < expectedGroups.length; i++) {
            expectedGroups[i] = new Group(new ArrayList<>());
        }
        expectedGroups[0].addStudent(new Student("Эдуардов", Student.SPECIALTIES.УИТС));
        expectedGroups[0].addStudent(new Student("Виноградов", Student.SPECIALTIES.УИТС));
        expectedGroups[0].addStudent(new Student("Ефимов", Student.SPECIALTIES.ПО));
        expectedGroups[0].addStudent(new Student("Антонов", Student.SPECIALTIES.ПО));
        expectedGroups[0].addStudent(new Student("Королев", Student.SPECIALTIES.РПИС));
        expectedGroups[0].addStudent(new Student("Борисов", Student.SPECIALTIES.РПИС));
        Group[] actualGroups = sm.getGroups(1);
        assertEquals(expectedGroups[0].getStudents().toString(), actualGroups[0].toString());
    }

    @Test
    public void getTwoGroups() {
        Group[] expectedGroups = new Group[2];
        for (int i = 0; i < expectedGroups.length; i++) {
            expectedGroups[i] = new Group(new ArrayList<>());
        }
        expectedGroups[0].addStudent(new Student("Эдуардов", Student.SPECIALTIES.УИТС));
        expectedGroups[1].addStudent(new Student("Виноградов", Student.SPECIALTIES.УИТС));
        expectedGroups[0].addStudent(new Student("Ефимов", Student.SPECIALTIES.ПО));
        expectedGroups[1].addStudent(new Student("Антонов", Student.SPECIALTIES.ПО));
        expectedGroups[0].addStudent(new Student("Королев", Student.SPECIALTIES.РПИС));
        expectedGroups[1].addStudent(new Student("Борисов", Student.SPECIALTIES.РПИС));
        Group[] actualGroups = sm.getGroups(2);
        for (int i = 0; i < expectedGroups.length; i++) {
            assertEquals(expectedGroups[i].getStudents().toString(), actualGroups[i].toString());
        }
    }

    @Test
    public void getThreeGroups() {
        Group[] expectedGroups = new Group[3];
        for (int i = 0; i < expectedGroups.length; i++) {
            expectedGroups[i] = new Group(new ArrayList<>());
        }
        expectedGroups[0].addStudent(new Student("Эдуардов", Student.SPECIALTIES.УИТС));
        expectedGroups[1].addStudent(new Student("Виноградов", Student.SPECIALTIES.УИТС));
        expectedGroups[2].addStudent(new Student("Ефимов", Student.SPECIALTIES.ПО));
        expectedGroups[0].addStudent(new Student("Антонов", Student.SPECIALTIES.ПО));
        expectedGroups[1].addStudent(new Student("Королев", Student.SPECIALTIES.РПИС));
        expectedGroups[2].addStudent(new Student("Борисов", Student.SPECIALTIES.РПИС));
        Group[] actualGroups = sm.getGroups(3);
        for (int i = 0; i < expectedGroups.length; i++) {
            assertEquals(expectedGroups[i].getStudents().toString(), actualGroups[i].toString());
        }
    }

    @Test
    public void getFourGroups() {
        Group[] expectedGroups = new Group[4];
        for (int i = 0; i < expectedGroups.length; i++) {
            expectedGroups[i] = new Group(new ArrayList<>());
        }
        expectedGroups[0].addStudent(new Student("Эдуардов", Student.SPECIALTIES.УИТС));
        expectedGroups[1].addStudent(new Student("Виноградов", Student.SPECIALTIES.УИТС));
        expectedGroups[2].addStudent(new Student("Ефимов", Student.SPECIALTIES.ПО));
        expectedGroups[3].addStudent(new Student("Антонов", Student.SPECIALTIES.ПО));
        expectedGroups[0].addStudent(new Student("Королев", Student.SPECIALTIES.РПИС));
        expectedGroups[1].addStudent(new Student("Борисов", Student.SPECIALTIES.РПИС));
        Group[] actualGroups = sm.getGroups(4);
        for (int i = 0; i < expectedGroups.length; i++) {
            assertEquals(expectedGroups[i].getStudents().toString(), actualGroups[i].toString());
        }
    }

    @Test
    public void getFiveGroups() {
        Group[] expectedGroups = new Group[5];
        for (int i = 0; i < expectedGroups.length; i++) {
            expectedGroups[i] = new Group(new ArrayList<>());
        }
        expectedGroups[0].addStudent(new Student("Эдуардов", Student.SPECIALTIES.УИТС));
        expectedGroups[1].addStudent(new Student("Виноградов", Student.SPECIALTIES.УИТС));
        expectedGroups[2].addStudent(new Student("Ефимов", Student.SPECIALTIES.ПО));
        expectedGroups[3].addStudent(new Student("Антонов", Student.SPECIALTIES.ПО));
        expectedGroups[4].addStudent(new Student("Королев", Student.SPECIALTIES.РПИС));
        expectedGroups[0].addStudent(new Student("Борисов", Student.SPECIALTIES.РПИС));
        Group[] actualGroups = sm.getGroups(5);
        for (int i = 0; i < expectedGroups.length; i++) {
            assertEquals(expectedGroups[i].getStudents().toString(), actualGroups[i].toString());
        }
    }
}