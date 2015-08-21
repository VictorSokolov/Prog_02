// Подключения необходимых библиотек
// import java.util.*; // Для получение параметров (Properties) компьютера
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class okno extends JFrame { // @author Victor Класс окна, в котором размещено игровое поле

    public static String AppPatch = "c:/Temp/"; // Каталог задачи
    public int HScreenSize = 1280, VScreenSize = 1024; // Параметры экрана, максимальные размеры окна
    public int MinHWindowSize = 640, MinVWindowSize = 480; // Минимальные размеры окна
    public int HWindowSize = 800, VWindowSize = 600; // Вертикальный и горизонтальный размер окна 
    public int HWindowPoint = 0, VWindowPoint = 0; // Вертикальная и горизонтальная координты начала окна
    public int HPersSize = 30, VPersSize = 30; // вертикальный и горизонтальный размер прсонажа
    private pole gameP = null; // Приватная переменная класса - игровое поле
    private int lvl = 1;  // Приватная переменная класса - сложность игры
    
    public okno(int HWndwP, int VWndwP, int HWndwSz, int VWndwSz, int level) { // Конструктор класса, задаётся координаты угла окна, его размер, сложность игры

        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize(); // Получим параметры экрана
        VScreenSize = sSize.height; HScreenSize = sSize.width;
        HWindowSize = (HWndwSz >= MinHWindowSize && HWndwSz <= HScreenSize) ? HWndwSz : HWindowSize; // Если входной параметр в разумных пределах использовать его
        VWindowSize = (VWndwSz >= MinVWindowSize && VWndwSz <= VScreenSize) ? VWndwSz : VWindowSize; // Если входной параметр в разумных пределах использовать его
        HWindowPoint = (HWndwP >= 0 && HWndwP <= HScreenSize - HWindowSize) ? HWndwP : 0; // Если входной параметр в разумных пределах использовать его
        VWindowPoint = (VWndwP >= 0 && VWndwP <= VScreenSize - VWindowSize) ? VWndwP : 0; // Если входной параметр в разумных пределах использовать его
        lvl = ( level > 0 && level < 8 ) ? level : 1; // Помещение сложности, выбранной пользователем в переменную класса                
    }
    
    public static void main(String[] args) {
        new okno(new Integer(args[0]).intValue(), new Integer(args[1]).intValue(),
                new Integer(args[2]).intValue(), new Integer(args[3]).intValue(),
                new Integer(args[4]).intValue()).run(); // Запустить игру
    }
    
    public void run() {
        gameP = new pole(lvl); // Создание объекта - игрового поля
        setTitle("Игра: Новогодний дождь"); // Задание заголовка окна
        setBounds(HWindowPoint, VWindowPoint, HWindowSize, VWindowSize); // Задание размеров и положения окна
        addKeyListener(new myKey()); // Подключение обработчика события для клавиатуры к окну
    	setFocusable(true); // Установка активности окна
        Container con = getContentPane(); // Прикрепление (вложение) панели - игрового поля в окно
        con.add(gameP);
    	setVisible(true); // Сделать окно видимым
    }
    
    private class myKey implements KeyListener { // Обработчик событий нажатий на клавиши
        @Override
        public void keyPressed(KeyEvent e) { // Метод, который срабатывает при нажатии
            int key_ = e.getKeyCode(); // Получение кода нажатой клавиши
            switch (e.getKeyCode()) {
                case 37 : gameP.x = (gameP.x > -18) ? gameP.x - 30 : 752; break; // if (gameP.x-30 > -48) gameP.x -= 30; else gameP.x = 752; // Контроль перемещения влево за пределы окна
                case 39 : gameP.x = (gameP.x < 722) ? gameP.x + 30 : -48; break; // Контроль перемещения вправо за пределы окна
                // default : System.exit(0); // case 27 : System.exit(0); break;  // Выход из программы, если нажат - Esc
            }
        }
        
        // public void keyReleased(KeyEvent e) {}
        
        // public void keyTyped(KeyEvent e) {}
    }
}