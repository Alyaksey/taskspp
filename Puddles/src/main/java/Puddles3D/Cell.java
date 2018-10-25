package Puddles3D;

public class Cell implements Comparable<Cell> {
    int row;
    int column;
    int height;

    public Cell(int row, int column, int height) {
        this.row = row;
        this.column = column;
        this.height = height;
    }
    //Сравниваем ячейки по высоте
    public int compareTo(Cell other) {
        return height - other.height;
    }
}
