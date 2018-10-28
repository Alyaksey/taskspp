package Task18;

import java.util.List;

public class Group {
    List<Student> students;

    public Group(List<Student> students) {
        this.students = students;
    }

    public Group(){
    }
    public void addStudent(Student student){
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return students.toString();
    }
}
