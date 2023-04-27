import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameField extends JPanel {
    private final int SIZE = 320; // Установка размерности
    private final int DOT_SIZE = 16; // размерность одной точки (яблочка и змейки) размерность в пикселях
    private final int ALL_DOTS = 400; // Количество точек на поле

    private Image dot; // Инициализация картинки
    private Image apple;

    //массив координат
    private int[] x = new int[ALL_DOTS]; // все возможные координаты по Х
    private int[] y = new int[ALL_DOTS]; // все возможные координаты по У

    private int appleX; // координаты для яблока 1
    private int appleY;

    private int appleX1; // координаты для яблока 1
    private int appleY1;

    private int appleX2; // координаты для яблока 1
    private int appleY2;

    private int dots; // количество звеньев змейки (очков в игре)
    private Timer timer; // считает количество кадров в секундку (скорость игры)

    private boolean inGame = true;

    public void loadImage() { // загрузка картинок в переменные dot и apple
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }

    public void createApple() { // генерация случайного места яблока
        Random random = new Random();
        appleX = random.nextInt(20) * DOT_SIZE;  // 1 яблоко
        appleY = random.nextInt(20) * DOT_SIZE;

        appleX1 = random.nextInt(20) * DOT_SIZE;  // 2 яблоко
        appleY1 = random.nextInt(20) * DOT_SIZE;

        appleX2 = random.nextInt(20) * DOT_SIZE;  // 3 яблоко
        appleY2 = random.nextInt(20) * DOT_SIZE;
    }

    public void initGame() { // Метод инициализации игры в самом начале (Вызов при старте игры)
        dots = 3; // размер змейки с начала игры
        for (int i = 0; i < dots; i++) {
            y[i] = 48;  // координаты змейки на начало игры по У
            x[i] = 48 - i * DOT_SIZE; // расположение горизонтально в левую сторону
        }
        timer = new Timer(150,this);
        timer.start();
        createApple();
    }

    public void chekApple(){ // проверка съела ли змейка наше яблоко и добавление очков
        if (x[0] == appleX && y[0] == appleY){ // 1 яблоко
            dots++;
            createApple();
        }

        if (x[0] == appleX1 && y[0] == appleY1){ // 2 яблоко
            dots++;
            createApple();
        }

        if (x[0] == appleX2 && y[0] == appleY2){ // 3 яблоко
            dots++;
            createApple();
        }
    }
}
