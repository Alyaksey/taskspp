import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task2 {
    public static void main(String[] args) {

    }
    public static String getDayOfTheWeek(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
        return (dayOfTheWeek > 5 ? "выходной" : "будний");
    }
}
