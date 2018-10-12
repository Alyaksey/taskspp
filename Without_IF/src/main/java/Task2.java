import java.util.Calendar;


public class Task2 {
    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        date.set(2018,Calendar.OCTOBER,14);
        System.out.println(getDayOfWeek(date));
    }
    public static String getDayOfWeek(Calendar date) {
        int buffValue = date.get(Calendar.DAY_OF_WEEK) - 1;
        int[] daysArray = {7, 1, 2 , 3, 4, 5, 6};
        int dayOfWeek = daysArray[buffValue];
        String[] buffArray = {"будний", "выходной"};
        return buffArray[dayOfWeek/6];
    }
}
