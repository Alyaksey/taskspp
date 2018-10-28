package Task19;

import java.io.*;
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
        return (double)(mathScore + physScore + infoScore) / 3;
    }
    public static double[] getAllAverages(ArrayList<Student> students){
        double[] averages = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            averages[i] = students.get(i).getAverage();
        }
        return averages;
    }

    public static ArrayList<String> getGoodStudents(ArrayList<Student> students) {
        ArrayList<String> goodStuds = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).mathScore > 3 && students.get(i).physScore > 3 && students.get(i).infoScore > 3)
                goodStuds.add(students.get(i).name + " " + students.get(i).surname);
        }
        return goodStuds;
    }

    private static double findMaxScore(ArrayList<Student> students) {
        return Collections.max(students).getAverage();
    }

    public static ArrayList<String> findBestStudents(ArrayList<Student> students) {
        double maxAvg = findMaxScore(students);
        ArrayList<String> bestStuds = new ArrayList<>();
        for (Student item : students) {
            if (item.getAverage() == maxAvg) {
                bestStuds.add(item.name + " " + item.surname);
            }
        }
        return bestStuds;
    }

    public static void sortStudents(ArrayList<Student> students) {
        Collections.sort(students);
        Collections.reverse(students);
    }

    public static double[] getThreeAverages(ArrayList<Student> students) {
        double[] avgScores = new double[3];
        for (int i = 0; i < students.size(); i++) {
            avgScores[0] += students.get(i).mathScore;
            avgScores[1] += students.get(i).physScore;
            avgScores[2] += students.get(i).infoScore;
        }
        for (int i = 0; i < avgScores.length; i++) {
            avgScores[i] /= (double)students.size();
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
        return (int) (getAverage() - other.getAverage());
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + mathScore + " " + physScore + " " + infoScore;
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
            fw.append(Student.findBestStudents(students).toString());
            fw.append("\n");
            Student.sortStudents(students);
            fw.append(students.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
