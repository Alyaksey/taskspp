import java.util.Random;

public class Task16 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int length = 50;
        initString(sb,length);
        System.out.println(sb);
        replaceChars(sb);
        System.out.println(sb);
    }
    public static void initString(StringBuilder sb, int length){
        String chrArray = "abcdefgh1234567?!;";
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chrArray.charAt(rnd.nextInt(chrArray.length())));
        }
    }
    public static void replaceChars(StringBuilder sb){
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ';')
                sb.setCharAt(i,'_');
        }
    }

}
