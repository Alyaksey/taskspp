package Task18;

import java.util.*;

public class StudentManager {
    private List<Student> students;

    public StudentManager(List<Student> students) {
        this.students = students;
    }

    public StudentManager() {
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Group[] getGroups(int groupQuantity) {
        Collections.sort(students); //Сортируем студентов по специальностям
        Group[] groups = new Group[groupQuantity];
        for (int i = 0; i < groupQuantity; i++) {
            groups[i] = new Group(new ArrayList<>());
        }
        int studsQuantity = students.size() - 1;
        //Удаляем студентов из листа и добавляем в группу
        while (!students.isEmpty()) {
            for (int i = 0; i < groupQuantity && studsQuantity >= 0; i++) {
                groups[i].addStudent(students.remove(studsQuantity));
                studsQuantity--;
            }
        }
        return groups;
    }
}
