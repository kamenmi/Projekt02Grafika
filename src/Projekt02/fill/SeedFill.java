package Projekt02.fill;

import Projekt02.view.Raster;

import java.awt.*;

public class SeedFill implements Filler {

    private int x;
    private int y;
    private int color;
    private int currentRGB;

    private int boundaryColor = Color.yellow.getRGB();
    public int fillColor = Color.green.getRGB();

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

    public void init(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        currentRGB = raster.getPixel(x, y);
    }

    // pozor na rekurzivní volání
    // nutné upravit parametr pro VM "-Xss100m"
    // https://stackoverflow.com/questions/4967885/jvm-option-xss-what-does-it-do-exactly
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
    // SeedFill druhá podmínka -- s vyplnovani podle barevne hranice
    //Uvažujte dvě možnosti hraniční podmínky vyplňování. Jednak omezení barvou pozadí a jednak barvou hranice.
    // Co bude v zapoctu -- prednasky -- B,C,D?
}
