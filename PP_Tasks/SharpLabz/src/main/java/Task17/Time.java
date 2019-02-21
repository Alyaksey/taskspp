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

    public Time() {
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        try {
            checkHours(hours);
            this.hours = hours;
        } catch (IllegalArgumentException e) {
            throw e;
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
            throw e;
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
            throw e;
        }
    }

    private void checkHours(int hours) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException();
        }
    }

    private void checkMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException();
        }
    }

    private void checkSeconds(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException();
        }
    }

    public void changeHours(int hours) {
        //Проверяем, если число выходит за пределы часов
        if (getHours() + hours > 23)
            setHours(getHours() + hours - (((getHours() + hours) / 24) * 24));
        else if (getHours() + hours < 0) {
            if (hours % 24 == 0)
                setHours(getHours());
            else
                setHours(getHours() + 24 + hours % 24);
        } else
            setHours(getHours() + hours);
    }

    public void changeMinutes(int minutes) {
        if (getMinutes() + minutes > 59) {
            changeHours((getMinutes() + minutes) / 60);//В зависимости от минут меняем также и часы
            setMinutes(getMinutes() + minutes - (((getMinutes() + minutes) / 60) * 60));
        }
        else if (getMinutes() + minutes < 0) {
            changeHours((getMinutes() + minutes) / 60 - 1);//В случае с отрицательным числом минут меняем часы
            if (minutes % 60 == 0)
                setMinutes(getMinutes());
            else
                setMinutes(getMinutes() + 60 + minutes % 60);
        } else
            setMinutes(getMinutes() + minutes);
    }

    public void changeSeconds(int seconds) {
        if (getSeconds() + seconds > 59) {
            changeMinutes((getSeconds() + seconds) / 60);//В зависимости от секунд меняем минуты
            setSeconds(getSeconds() + seconds - (((getSeconds() + seconds) / 60) * 60));
        }
        else if (getSeconds() + seconds < 0) {
            changeMinutes((getSeconds() + seconds) / 60 - 1);//С отрицательным числом секунд меняем минуты
            if (seconds % 60 == 0)
                setSeconds(getSeconds());
            else
                setSeconds(getSeconds() + 60 + seconds % 60);
        } else
            setSeconds(getSeconds() + seconds);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Time{");
        sb.append("hours=").append(hours);
        sb.append(", minutes=").append(minutes);
        sb.append(", seconds=").append(seconds);
        sb.append('}');
        return sb.toString();
    }
}
