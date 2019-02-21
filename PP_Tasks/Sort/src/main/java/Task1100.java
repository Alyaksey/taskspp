import java.io.*;
import java.util.Arrays;

public class Task1100 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        streamTokenizer.nextToken();
        int n = (int) streamTokenizer.nval;
        Team[] teams = new Team[n];
        for (int i = 0; i < n; i++) {
            teams[i] = new Team();
            streamTokenizer.nextToken();
            teams[i].setId((int) streamTokenizer.nval);
            streamTokenizer.nextToken();
            teams[i].setScore((int) streamTokenizer.nval);
        }
        Arrays.sort(teams);
        for (int i = 0; i < n; i++) {
            out.println(teams[i].toString());
        }
        out.close();
    }
}
class Team implements Comparable<Team> {
    private int id;
    private int score;

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Team o) {
        return o.score - score;
    }

    @Override
    public String toString() {
        return id + " " + score;
    }
}