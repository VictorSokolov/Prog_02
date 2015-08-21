// Подключения необходимых библиотек
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class pole extends JPanel { // @author Victor Класс панели, которая является игровым полем
    
    private Image start_game = null, shapka = null, fon = null, end_game = null; // Стартовая заставка. шапка, фон игрового поля и финальная заставка
    public int x = 400; // Публичная переменная, ? ширина шапки
    private int level = 1; // Приватная переменная сложности игры, по умолчанию уровень сложности = 1
    private final podar[] gamePodar = new podar[7]; // Приватный массив подарков
    public Timer timerUpdate, timerDraw; // Таймер обновления и рисования
    
    public pole(int lvl) { // Конструктор класса
        level = lvl;
        File buf = new File(okno.AppPatch+"image/"); // Картинки разместим в подкаталоге \image
        try { // Попытка загрузки изображения стартовй заставки
            start_game = ImageIO.read(new File(okno.AppPatch+"start_game.png"));
        } catch(IOException ex) { System.out.println(ex + "Do't load Start image"); }
        try { // Попытаться загрузить изображение шапки из файла
            shapka = ImageIO.read(new File(okno.AppPatch+"shapka.png"));
        } catch(IOException ex) { System.out.println(ex + "Do't load Heap image"); }
        try { // Попытка загрузить изображение фона из файла
            fon = ImageIO.read(new File(okno.AppPatch+"fon.png"));
        } catch(IOException ex) { System.out.println(ex + "Do't load Back image"); }
        try { // Попытка загрузки изображения финальной заставки
            end_game = ImageIO.read(new File(okno.AppPatch+"end_game.png"));
        } catch(IOException ex) { System.out.println(ex + "Do't load Final image"); }
        
        for ( int i = 0; i < 7; i++ ) { // Загрузка семи изображений подарков
            try { gamePodar[i] = new podar(ImageIO.read(new File(okno.AppPatch+"p"+i+".png")));
            } catch (IOException ex) { System.out.println(ex + "Do't load Present image No = " + i); }
        }
        timerUpdate = new Timer(3000, new ActionListener() { // Создание таймера, который будет раз в три секунды проверять и добавлять подарки на игровое поле
            @Override
            public void actionPerformed(ActionEvent e) { updateStart();}}); // Метод для проверки и добавление подарков на игровое поле
        timerUpdate.start(); // Запуск таймера timerUpdate
        timerDraw = new Timer(50,new ActionListener() {	// Создание таймера, который будет перерисовывать игровое поле 20 раз в секунду
            @Override
            public void actionPerformed(ActionEvent e) { repaint();}}); // Запуск метода перерисовки поля (public void paintComponent(Graphics gr))
        timerDraw.start(); // Запуск таймера для перерисовки
    }
    
    @Override
    public void paintComponent(Graphics gr) { // Метод, который отрисовывает графические объекты на панели
        super.paintComponent(gr); // Выполнить отрисовку сначала самого окна
        gr.drawImage(fon, 0, 0, null); // Рисование фона
        gr.drawImage(shapka, x, 465, null); // Рисование шапки
        for (podar i : gamePodar) // for (int i=0;i<7;i++) // Цикл, который отображает подарки на игровом поле и проверяет пропущенные подарки
            i.draw(gr); // Отображение подарка
    }
    
    private void updateStart() { // Метод для проверки и добавление подарков на игровое поле
        
        int kol = 0; // Переменная для подсчета подарков на игровом поле
        
        for (podar i : gamePodar) // Цикл перебора всех подарков массива
            if ( i.act ) // Если подарок на игровом поле
                if ( kol < level ) { // Если текущее количество менее номера сложности (от 1 до 7)
                    i.start(); // Активизация подарка на игровом поле, вывод его сверху игрового поля
                    break; // Прерывание цикла
                }
            else
                    kol++;
    }
}