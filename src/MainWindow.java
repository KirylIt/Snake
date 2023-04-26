import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Snake Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // При нажатии на красный крестик, игра завершит свою работу
        setSize(320,345); // Установка размеров поля
        setLocation(400, 400); // Установка позиции начала игры
        setVisible(true); //Установка видимости экрана
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(); // Вызов окна игры
    }
}
