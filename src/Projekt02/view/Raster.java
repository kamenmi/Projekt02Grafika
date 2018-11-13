package Projekt02.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Raster extends JPanel {

    private BufferedImage img;
    private static final int FPS = 1000 / 30;
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    public Raster() {
        // inicializace image, nastavení rozměrů (nastavení typu - pro nás nedůležité)
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        setTimer();
        setLayout(new BorderLayout());
        setInfo();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    private void setTimer() {
        // časovač, který 30 krát za vteřinu obnoví obsah plátna aktuálním img
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // říct plátnu, aby zobrazil aktuální img
                repaint();
                // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
            }
        }, 0, FPS);
    }

    public void clear() {
        // https://stackoverflow.com/a/5843470
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void drawPixel(int x, int y, int color) {
        if(x >=0 && x < WIDTH && y >=0 && y < HEIGHT)
        img.setRGB(x, y, color);
    }

    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

    public void setInfo(){
        JLabel lblInfo = new JLabel();
        lblInfo.setText("Mazání plátna (C) | SeedFill - prvni podminka (Ctrl+click) | SeedFill - druha podminka (Alt + click) | ScanLine - shift + click"); //Dopsat klavesy pro kontrolovani vykreslovani
        lblInfo.setFont(new Font("courier", Font.PLAIN,12));
        lblInfo.setForeground(new Color(0xffffff));
        add(lblInfo, BorderLayout.SOUTH);
    }
    public void Trida(){
        System.out.println("AHoj");
    }

}
