package Projekt02.fill;

import Projekt02.model.Edge;
import Projekt02.model.Point;
import Projekt02.renderer.Renderer;
import Projekt02.view.Raster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler {
    private List<Point> points;
    private int fillColor, edgeColor;
    private Raster raster;

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;

    }

    @Override
    public void fill() {
        scanLine();

    }

    public void init(List<Point> points, int fillColor, int edgeColor) {
        this.points = points;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
    }

    private void scanLine() {
        // projet vsechny vrcholy a vytvorit z nich hrany
        // 0 a 1 bod budou
        // posleni a 0 budou hrana
        // pred pridelenim do seznamy hrany orientujeme
        // nepridavame vodorovne hrany
        // najit max Y a min Y

        int minY = points.get(0).y;
        int maxY = minY;
        //projet vsechny mody a najit minimalni a maximalni Y
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            if (minY > points.get(i).y)
                minY = points.get(i).y;
            if (maxY < points.get(i).y)
                maxY = points.get(i).y;

            Edge temp = new Edge(points.get(i), points.get((i + 1) % points.size()));

            if (!temp.isHorizontal()) {
                temp.orientate();
                edges.add(temp);
            }
        }


        // pro vsechna y od nalazeneho minima od nalezenho maxima
        List<Integer> intersections = new ArrayList<>();
        Renderer draw = new Renderer(raster);

        for (int y = minY; y <= maxY; y++) {
            for (Edge sl : edges) {
                if (sl.intersectionExits(y)) {
                    intersections.add(sl.getIntersection(y));
                }
            }

            //projit vsechny hrany
            // pokud ma usecka prusecik na tomot Y
            // tak vypocitat a ulozit hodnotu pruseciku do seznamu

            Collections.sort(intersections);
            //nebo volitene implementovat vlastni algoritmus
            //provedeme vzbarveni mezi pruseciky
            //tzn. vykreslit caru mezi kazdym sudym a lichym prusecikem
            for (int i = 0; i < intersections.size(); i += 2) {
                draw.drawDDA(intersections.get(i), y, intersections.get(i + 1), y, 0xFF1493);
            }
            intersections.clear();
        }
        // obtahnuti hranice
        draw.drawPolygon(points, 0x00FF00);
    }
}
