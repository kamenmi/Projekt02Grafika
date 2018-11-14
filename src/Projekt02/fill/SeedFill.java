package Projekt02.fill;

import Projekt02.view.Raster;

import java.awt.*;

public class SeedFill implements Filler {

    private int x;
    private int y;
    private int color;
    private int currentRGB;

    private int boundaryColor = Color.yellow.getRGB();
    private int fillColor = Color.green.getRGB();

    private int fillColorVzor = Color.BLUE.getRGB();
    private int edgecolor = Color.GRAY.getRGB();

    private Raster raster;

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill() {
        seed(x, y);
    }

    public void fillDruhaPodminka() {
        seedJinaPOdminka(x, y);
    }

    public void fillVzor() {
        seedVzor(x, y);
    }

    public void init(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        currentRGB = raster.getPixel(x, y);
    }

    private void seed(int ax, int ay) {
        if ((ax >= 0) && (ay >= 0) && (ax < Raster.WIDTH) && (ay < Raster.HEIGHT)) {
            if (currentRGB == raster.getPixel(ax, ay)) {
                raster.drawPixel(ax, ay, color);
                seed(ax + 1, ay);
                seed(ax - 1, ay);
                seed(ax, ay + 1);
                seed(ax, ay - 1);
            }
        }
    }

    private void seedJinaPOdminka(int ax, int ay) {
        if ((ax >= 0) && (ay >= 0) && (ax < Raster.WIDTH) && (ay < Raster.HEIGHT)) {
            if (raster.getPixel(ax, ay) != boundaryColor && raster.getPixel(ax, ay) != fillColor) {
                raster.drawPixel(ax, ay, color);
                seedJinaPOdminka(ax + 1, ay);
                seedJinaPOdminka(ax - 1, ay);
                seedJinaPOdminka(ax, ay + 1);
                seedJinaPOdminka(ax, ay - 1);
            }
        }
    }

    private void seedVzor(int ax, int ay) {

        int[][] pattern = {{1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 1, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 1, 1, 0}};

        if ((ax >= 0) && (ay >= 0) && (ax < Raster.WIDTH) && (ay < Raster.HEIGHT)) {
            if (currentRGB == raster.getPixel(ax, ay)) {
                raster.drawPixel(ax, ay, color);

                if (/*matrix*/pattern[ax % 8][ay % 8] == 1) {
                    raster.drawPixel(ax, ay, fillColorVzor);
                } else {
                    raster.drawPixel(ax, ay, edgecolor);
                }
                seedVzor(ax + 1, ay);
                seedVzor(ax - 1, ay);
                seedVzor(ax, ay + 1);
                seedVzor(ax, ay - 1);
            }
        }
    }
}
