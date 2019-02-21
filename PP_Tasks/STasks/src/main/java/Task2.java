import java.io.*;

public class Task2 {
    public static int[] getPrefixFunction(String s) {
        int[] prefFunc = new int[150001];
        int length = s.length();
        int j = 0;
        for (int i = 1; i < length; i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = prefFunc[j - 1];
            if (s.charAt(j) == s.charAt(i))
                j++;
            prefFunc[i] = j;
        }
        return prefFunc;
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        streamTokenizer.nextToken();
        String fStr = streamTokenizer.sval;
        streamTokenizer.nextToken();
        String sStr = streamTokenizer.sval;
        String str = new StringBuilder(fStr).append("#").append(sStr).toString();
        int[] prefFunc = getPrefixFunction(str);
        int fLength = fStr.length();
        int sLength = sStr.length();
        int[] prefLength = new int[75001];
        int prefCount = 0;
        int pointer = sLength;
        while (prefFunc[fLength + pointer] != 0) {
            prefLength[prefCount] = prefFunc[fLength + pointer];
            pointer -= prefFunc[fLength + pointer];
            prefCount++;
        }
        if (pointer != 0)
            out.print("Yes");
        else {
            out.println("No");
            for (int i = prefCount - 1; i >= 0; i--) {
                for (int j = 0; j < prefLength[i]; j++) {
                    out.print(fStr.charAt(j));
                }
                if (i == 0)
                    break;
                out.print(" ");
            }
        }
        out.close();
    }
}
