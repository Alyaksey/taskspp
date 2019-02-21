import java.util.StringTokenizer;

public class Task14 {
    public static void main(String[] args) {
    }
    public static int getWordsQuantity(String str){
        StringTokenizer st = new StringTokenizer(str);
        int count = st.countTokens();
        return count;
    }
}
