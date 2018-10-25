package Puddles3D;

import java.util.PriorityQueue;

public class Puddles3D {

    public static int findVolume(int[][] walls) {
        int rows = walls.length;
        int columns = walls[0].length;
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
        boolean[][] isVisited = new boolean[rows][columns];
        //Заносим в PriorityQueue рамки массива и помечаем их посещенными
        for (int i = 0; i < rows; i++) {
            isVisited[i][0] = true;
            isVisited[i][columns - 1] = true;
            pq.add(new Cell(i, 0, walls[i][0]));
            pq.add(new Cell(i, columns - 1, walls[i][columns - 1]));
        }
        for (int j = 0; j < columns; j++) {
            isVisited[0][j] = true;
            isVisited[rows - 1][j] = true;
            pq.add(new Cell(0, j, walls[0][j]));
            pq.add(new Cell(rows - 1, j, walls[rows - 1][j]));
        }
        //Задаем директории, по которым будем двигаться в четырех направлениях
        int[] dirRows = {0, 0, 1, -1};
        int[] dirColumns = {1, -1, 0, 0};
        //Координаты соседних ячеек
        int neighbourRow;
        int neighbourColumn;
        Cell currentCell;
        int volume = 0;
        while (!pq.isEmpty()) {
            //Достаем из очереди наименьший по высоте элемент
            currentCell = pq.poll();
            //Проходимся по соседям данной ячейки
            for (int i = 0; i < 4; i++) {
                neighbourRow = currentCell.row + dirRows[i];
                neighbourColumn = currentCell.column + dirColumns[i];
                //Исключаем выход за границы массива и не проходимся по уже посещенным соседним клеткам
                if (neighbourRow >= 0 && neighbourRow < rows && neighbourColumn >= 0 && neighbourColumn < columns
                        && !isVisited[neighbourRow][neighbourColumn]) {
                    isVisited[neighbourRow][neighbourColumn] = true;
                    //Если высота соседа данной ячейки меньше, чем высота текущей, то прибавляем к уровню воды их разницу
                    volume += Math.max(0, currentCell.height - walls[neighbourRow][neighbourColumn]);
                    //Добавляем в очередь соседей, и запоминаем уровень воды, то есть наибольшую по высоте ячейку
                    pq.add(new Cell(neighbourRow, neighbourColumn,
                            Math.max(walls[neighbourRow][neighbourColumn], currentCell.height)));
                }
            }
        }
        return volume;
    }
}
