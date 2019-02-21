import java.util.Calendar;


public class Task2 {
    public static void main(String[] args) {
    }
    public static String getDayOfWeek(Calendar date) {
        int buffValue = date.get(Calendar.DAY_OF_WEEK) - 1;
        int[] daysArray = {7, 1, 2 , 3, 4, 5, 6};
        int dayOfWeek = daysArray[buffValue];
        String[] weekdayOrWeekend = {"будний", "выходной"};
        return weekdayOrWeekend[dayOfWeek/6];
    }
}
