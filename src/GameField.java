import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameField extends JPanel{
    private final int SIZE = 320; // Установка размерности
    private final int  DOT_SIZE = 16; // размерность одной точки (яблочка и змейки) размерность в пикселях
    private final int ALL_DOTS = 400; // Количество точек на поле

    private Image dot; // Инициализация картинки
    private Image apple;

    //массив координат
    private int[] x = new int[ALL_DOTS]; // все возможные координаты по Х
    private int [] y = new int[ALL_DOTS]; // все возможные координаты по У

    private  int appleX; // координаты для яблока
    private int appleY;

    private int dots; // количество звеньев змейки (очков в игре)
    private  Timer timer; // считает количество кадров в секундку (скорость игры)

    private boolean inGame = true;

    public void loadImage (){ // загрузка картинок в переменные dot и apple
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }

    public void createApple(){ // генерация случайного места яблока
        Random random = new Random();
        appleX = random.nextInt(20)*DOT_SIZE;  // 1 яблоко
        appleY = random.nextInt(20)*DOT_SIZE;

        Random random1 = new Random();
        appleX = random1.nextInt(20)*DOT_SIZE; // 2 яблоко
        appleY = random1.nextInt(20)*DOT_SIZE;

        Random random2 = new Random();
        appleX = random2.nextInt(20)*DOT_SIZE; // 3 яблоко
        appleY = random2.nextInt(20)*DOT_SIZE;

    }

}
