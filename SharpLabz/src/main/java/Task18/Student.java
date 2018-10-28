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
        return specialty.compareTo(other.specialty);
    }

    @Override
    public String toString() {
        return surname + " (" + specialty + ")";
    }
}
