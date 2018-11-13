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
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, FPS);
    }

    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void drawPixel(int x, int y, int color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT)
            img.setRGB(x, y, color);
    }

    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

    public void setInfo() {
        JLabel lblInfo = new JLabel();
        lblInfo.setText("Mazání plátna (C) | ScanLine - Shiftl + left click | SeedFill - prvni podminka (Ctrl+ left click) | SeedFill - druha podminka (Alt + left click)");
        lblInfo.setFont(new Font("courier", Font.PLAIN, 12));
        lblInfo.setForeground(new Color(0xffffff));
        add(lblInfo, BorderLayout.SOUTH);
    }
}
