package Task20;

import java.lang.reflect.Field;
import java.util.ArrayList;

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
    public void addString(String[] strings){
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
    }
    public void addStringsAtBeginning(String[] strings){
        for (int i = 0; i < strings.length; i++) {
            list.add(0+i,strings[i]);
        }
    }
    public void addStringsByIndex(String[] strings, int index){
        for (int i = 0; i < strings.length; i++) {
            list.add(index + i, strings[i]);
        }
    }
    public void changeSize(int size){
        list.ensureCapacity(size);
    }
    private static int getArrayListCapacity(ArrayList<String> list) throws NoSuchFieldException, IllegalAccessException {
        Field arrayField = ArrayList.class.getDeclaredField("elementData");
        arrayField.setAccessible(true);
        Object[] internalArray = (Object[])arrayField.get(list);
        return internalArray.length;
    }
    public String showListInfo() throws NoSuchFieldException, IllegalAccessException {
        return list.toString() + "\n" + "Кол-во элементов: " + list.size() + " "
                + "Емкость: " + ListManager.getArrayListCapacity(list);
    }
    public void deleteExtraSpaces(){
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("\\s+"," "));
        }
    }
    public void deleteExtraPunctMarks(){
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("(\\p{P})\\1+", "$1"));
        }
    }
    public void addSpaces(){
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("\\p{P}", "$0 "));
        }
    }
    public void changeNumbers(){
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("[0-9]+", "0,$0"));
        }
    }
}
