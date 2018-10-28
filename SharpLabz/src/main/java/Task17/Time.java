package Task17;

import java.util.*;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        try {
            checkHours(hours);
            this.hours = hours;
            checkMinutes(minutes);
            this.minutes = minutes;
            checkSeconds(seconds);
            this.seconds = seconds;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        try {
            checkHours(hours);
            this.hours = hours;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        try {
            checkMinutes(minutes);
            this.minutes = minutes;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        try {
            checkSeconds(seconds);
            this.seconds = seconds;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void checkHours(int hours) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Диапазон часов 0-23");
        }
    }

    private void checkMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Диапазон минут 0-59");
        }
    }

    private void checkSeconds(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Диапазон секунд 0-59");
        }
    }

    public void changeHours(int hours) {
        if (getHours() + hours > 23)
            setHours(getHours() + hours % 24);
        else if (getHours() + hours < 0) {
            if (hours % 24 == 0)
                setHours(getHours());
            else
                setHours(getHours() + 24 + hours % 24);
        } else
            setHours(getHours() + hours);
    }

    public void changeMinutes(int minutes) {
        changeHours(minutes / 60);
        if (getMinutes() + minutes > 59)
            setMinutes(getMinutes() + minutes % 60);
        else if (getMinutes() + minutes < 0) {
            if (minutes % 60 == 0)
                setMinutes(getMinutes());
            else
                setMinutes(getMinutes() + 60 + minutes % 60);
        }
        else
            changeMinutes(getMinutes() + minutes);
    }
    public void changeSeconds(int seconds){
        changeMinutes(seconds / 60);
        if (getSeconds() + seconds > 59)
            setMinutes(getSeconds() + seconds % 60);
        else if (getSeconds() + seconds < 0) {
            if (seconds % 60 == 0)
                setMinutes(getSeconds());
            else
                setMinutes(getSeconds() + 60 + seconds % 60);
        }
        else
            changeMinutes(getSeconds() + seconds);
    }

    @Override
    public String toString() {
        return "Time{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
