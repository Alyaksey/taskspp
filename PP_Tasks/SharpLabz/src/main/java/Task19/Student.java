package Task19;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    private String surname;
    private int mathScore;
    private int physScore;
    private int infoScore;

    public Student(String name, String surname, int mathScore, int physScore, int infoScore) {
        this.name = name;
        this.surname = surname;
        this.mathScore = mathScore;
        this.physScore = physScore;
        this.infoScore = infoScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getPhysScore() {
        return physScore;
    }

    public void setPhysScore(int physScore) {
        this.physScore = physScore;
    }

    public int getInfoScore() {
        return infoScore;
    }

    public void setInfoScore(int infoScore) {
        this.infoScore = infoScore;
    }

    public double getAverage() {
        return (double) (mathScore + physScore + infoScore) / 3;
    }

    public static double[] getAllAverages(List<Student> students) {
        double[] averages = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            averages[i] = Double.parseDouble(new BigDecimal(students.get(i)
                    .getAverage()).setScale(1, RoundingMode.HALF_EVEN).toString());
        }
        return averages;
    }

    public static ArrayList<String> getGoodStudents(List<Student> students) {
        ArrayList<String> goodStuds = new ArrayList<>();
        for (Student student : students) {
            if (student.mathScore > 3 && student.physScore > 3 && student.infoScore > 3)
                goodStuds.add(new StringBuilder()
                        .append(student.name).append(" ").append(student.surname).toString());
        }
        return goodStuds;
    }

    public static ArrayList<String> getBestStudents(List<Student> students) {
        double maxAvg = Collections.max(students).getAverage();
        ArrayList<String> bestStuds = new ArrayList<>();
        for (Student item : students) {
            if (item.getAverage() == maxAvg) {
                bestStuds.add(new StringBuilder()
                        .append(item.name).append(" ").append(item.surname).toString());
            }
        }
        return bestStuds;
    }

    public static void sortStudents(List<Student> students) {
        Collections.sort(students);
        Collections.reverse(students);
    }

    public static double[] getThreeAverages(List<Student> students) {
        double[] avgScores = new double[3];
        for (Student student : students) {
            avgScores[0] += student.mathScore;
            avgScores[1] += student.physScore;
            avgScores[2] += student.infoScore;
        }
        for (int i = 0; i < avgScores.length; i++) {
            avgScores[i] /= (double) students.size();
            avgScores[i] = Double.parseDouble(new BigDecimal(avgScores[i])
                    .setScale(1,RoundingMode.HALF_EVEN).toString());
        }
        return avgScores;
    }

    public static Student parseStudent(String string) {
        String[] bufSplit = string.split(" ");
        return new Student(bufSplit[0], bufSplit[1], Integer.parseInt(bufSplit[2]), Integer.parseInt(bufSplit[3]),
                Integer.parseInt(bufSplit[4]));
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(getAverage(), other.getAverage());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" ").append(surname);
        sb.append(" ").append(mathScore);
        sb.append(" ").append(physScore);
        sb.append(" ").append(infoScore);
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            Path filePath = Paths.get("SharpLabz\\src\\main\\resources\\in.txt");
            Charset charset = Charset.forName("ISO-8859-1");
            FileWriter fw = new FileWriter("SharpLabz\\src\\main\\resources\\out.txt");
            List<String> lines = Files.readAllLines(filePath, charset);
            ArrayList<Student> students = new ArrayList<>();
            for (String line : lines) {
                students.add(Student.parseStudent(line));
            }
            fw.append(Arrays.toString(Student.getAllAverages(students)));
            fw.append("\n");
            fw.append(Student.getGoodStudents(students).toString());
            fw.append("\n");
            fw.append(Arrays.toString(Student.getThreeAverages(students)));
            fw.append("\n");
            fw.append(Student.getBestStudents(students).toString());
            fw.append("\n");
            Student.sortStudents(students);
            fw.append(students.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
