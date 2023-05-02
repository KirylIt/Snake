import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
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

    private int appleX1; // координаты для яблока 2
    private int appleY1;

    private int appleX2; // координаты для яблока 3
    private int appleY2;


    private int dots; // количество звеньев змейки (очков в игре)
    private Timer timer; // считает количество кадров в секундку (скорость игры)

    // управление змейки
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = true;

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
        timer = new Timer(150, this);
        timer.start();
        createApple();
    }

    public void chekApple() { // проверка съела ли змейка наше яблоко и добавление очков
        if (x[0] == appleX && y[0] == appleY) { // 1 яблоко
            dots++;
            createApple();
        }

        if (x[0] == appleX1 && y[0] == appleY1) { // 2 яблоко
            dots++;
            createApple();
        }

        if (x[0] == appleX2 && y[0] == appleY2) { // 3 яблоко
            dots++;
            createApple();
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // отрисовка
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            String str = "Game Over";
            g.setColor(Color.CYAN);
            g.drawString(str, SIZE / 6, SIZE / 2);
        }
    }

    public void chekCollision() { // проверка на врезание в стенку или в саму себя
        for (int i = dots; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                inGame = false; // конец игры!
            }
        }
       if (x[0] > SIZE) // проверка при достижении змейки правой стенки (переход в левую стенку)
            x[0] = 0;
        if (x[0] < 0) // проверка при достижении змейки левой стенки (переход в правую стенку)
            x[0] = SIZE;
        if (y[0] > SIZE) // проверка при достижении змейки верхней стенки (конец игры)
            inGame = false;
        if (y[0] < 0) // проверка при достижении змейки нижней стенки (конец игры)
            inGame = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            chekApple(); // проверка яблока
            chekCollision(); // проверка на столкновение
            move();
        }
        repaint();
    }
    public GameField(){
        setBackground(Color.BLACK);
        loadImage();
        initGame();
        addKeyListener(new FiledKeyListener());
        setFocusable(true);
    }

    public void move() { //логика движения движения
        for (int i = dots; i > 0; i--) {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if (left) // движение влево
            x[0] -= DOT_SIZE;
        if (right) // движение вправо
            x[0] += DOT_SIZE;
        if (up) // движение вверх
            y[0] -= DOT_SIZE;
        if (down) // движение вниз
            y[0] += DOT_SIZE;
    }
    class FiledKeyListener extends KeyAdapter { // класс для управление клавишами змейки
        @Override
        public void keyPressed (KeyEvent k){
            super.keyPressed(k);
            int key = k.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down){
                left = false;
                right = false;
                up = true;
            }
            if (key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }

    }
}
