public class Puddles2D {
    static int findVolume(int[] walls) {
        int leftPointer = 0; //Указывает текущее положение слева
        int rightPointer = walls.length - 1; //Указывает на текущее положение справа
        int leftMax = walls[leftPointer];
        int rightMax = walls[rightPointer];
        int volume = 0;
        while (leftPointer < rightPointer) {
            //Находим локальные максимумы с правой и левой стороны
            if (walls[leftPointer] > leftMax)
                leftMax = walls[leftPointer];
            if (walls[rightPointer] > rightMax)
                rightMax = walls[rightPointer];
            /*Сравниваем локальные максимумы слева и справа и в зависимости от этого прибавляем к уровню воды
            локальный максимум и вычитаем высоту стены, которая идет после локального максимума
            */
            if (leftMax >= rightMax) {
                if (walls[rightPointer] != 0)
                    volume += rightMax - walls[rightPointer];
                rightPointer--;
            }
            if (leftMax < rightMax) {
                if (walls[leftPointer] != 0)
                    volume += leftMax - walls[leftPointer];
                leftPointer++;
            }
        }
        return volume;
    }
}
