package Task18;

public class Student implements Comparable<Student> {
    public enum SPECIALTIES {РПИС, ПО, УИТС}

    private String surname;
    private SPECIALTIES specialty;

    public Student() {
    }

    public Student(String surname, SPECIALTIES specialty) {
        this.surname = surname;
        this.specialty = specialty;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public SPECIALTIES getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SPECIALTIES specialty) {
        this.specialty = specialty;
    }

    @Override
    public int compareTo(Student other) {
        return specialty.compareTo(other.specialty);//Сравниваем по специальностям студентов, чтобы потом отсортировать
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(surname);
        sb.append(" ");
        sb.append(specialty);
        return sb.toString();
    }
}
