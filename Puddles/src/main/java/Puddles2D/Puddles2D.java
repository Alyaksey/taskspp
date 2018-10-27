package Puddles2D;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Puddles2D {
    public static int findVolume(int[] walls) {
        PriorityQueue<Wall> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[walls.length];
        //Заносим в PriorityQueue стены с высотой 0, а также первую и последнюю и помечаем их посещенными
        pq.add(new Wall(0, walls[0]));
        isVisited[0] = true;
        pq.add(new Wall(walls.length-1, walls[walls.length-1]));
        isVisited[walls.length - 1] = true;
        for (int i = 1; i < walls.length-1; i++) {
            if (walls[i] == 0){
                pq.add(new Wall(i,walls[i]));
                isVisited[i] = true;
            }
        }
        //Задаем директории, по которым будем двигаться по массиву
        int[] directory = {1, -1};
        int neighbourWall; //Номер соседней стены
        int volume = 0;
        Wall currentWall; //Номер текущей стены
        while (!pq.isEmpty()){
            //Берем наименьший элемент из очереди
            currentWall = pq.poll();
            for (int i = 0; i < 2; i++) {
                //Находим номера соседних стен
                neighbourWall = currentWall.number + directory[i];
                //Задаем условие, чтобы не выходить за границы массива и исключить посещенные стены
                if (neighbourWall >=0 && neighbourWall < walls.length && !isVisited[neighbourWall]){
                    //Помечаем соседнюю стену посещенной
                    isVisited[neighbourWall] = true;
                    //Если разница между текущей стеной и соседней — положительная, прибавляем к уровню воды
                    volume+= Math.max(0, currentWall.height - walls[neighbourWall]);
                    //Заносим в очередь соседнюю стену и запоминаем высоту наибольшего локального элемента
                    pq.add(new Wall(neighbourWall, Math.max(currentWall.height, walls[neighbourWall])));
                }
            }
        }
        return volume;
    }
}
