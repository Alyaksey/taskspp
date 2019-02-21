package Puddles2D;

public class Wall implements Comparable<Wall> {
    int number;
    int height;

    public Wall(int number, int height) {
        this.number = number;
        this.height = height;
    }

    //Сравниваем стены по высоте
    public int compareTo(Wall other) {
        return height - other.height;
    }

}
