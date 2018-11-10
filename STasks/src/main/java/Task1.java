import java.util.Scanner;

public class Task1 {
    public static char getStrongestSpell(String s){
        int countArray[] = new int[123];//Массив — счетчик для латинских букв
        for (int i = 0; i < s.length(); i++) {
            countArray[s.charAt(i)]++;//Инкрементируем значение счетчика, где индекс — численное значение символа
        }
        int index = 0;
        int max = countArray[0];//Символ, встречающийся наибольшее число раз
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > max) {
                index = i;//Находим численное значение символа, который встречался наибольшее число раз
                max = countArray[i];
            }
        }
        return (char)(index);//Представляем это число в виде символа
    }
    public static void main(String[] args) {
        System.out.println(getStrongestSpell(new Scanner(System.in).next()));
    }
}
