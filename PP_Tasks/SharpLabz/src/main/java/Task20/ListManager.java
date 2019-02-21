package Task20;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class ListManager {
    private ArrayList<String> list;

    public ListManager(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public void addString(String[] strings) {
        list.addAll(Arrays.asList(strings));
    }

    public void addStringsAtBeginning(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            list.add(i, strings[i]);
        }
    }

    public void addStringsByIndex(String[] strings, int index) {
        for (int i = 0; i < strings.length; i++) {
            list.add(index + i, strings[i]);
        }
    }

    public void changeCapacity(int size) {
        list.ensureCapacity(size);
    }

    private static int getArrayListCapacity(ArrayList<String> list) throws NoSuchFieldException, IllegalAccessException {
        Field arrayField = ArrayList.class.getDeclaredField("elementData");
        arrayField.setAccessible(true);
        Object[] internalArray = (Object[]) arrayField.get(list);
        return internalArray.length;
    }

    public String showListInfo() throws NoSuchFieldException, IllegalAccessException {
        return new StringBuilder(list.toString()).append("\n").append("Кол-во элементов: ").append(list.size())
                .append("\n").append("Емкость: ").append(ListManager.getArrayListCapacity(list)).toString();
    }

    public void deleteExtraSpaces() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("\\s+", " "));
        }
    }

    public void deleteExtraPunctMarks() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("(\\p{P})\\1+", "$1"));
        }
    }

    public void addSpaces() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("\\p{P}", "$0 "));
        }
    }

    public void changeNumbers() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("[0-9]+", "0,$0"));
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
