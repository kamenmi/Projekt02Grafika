package Projekt02.renderer;

import Projekt02.model.Edge;
import Projekt02.model.Point;
import Projekt02.view.Raster;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private Raster raster;
    private ArrayList<Point> ctverecPoint = new ArrayList<>();

    public Renderer(Raster raster) {
        this.raster = raster;
    }

    public List<Point> vykresliCtverec() {
        ctverecPoint.clear();
        Point p1 = new Point(50, 50);
        Point p2 = new Point(300, 50);
        Point p3 = new Point(300, 300);
        Point p4 = new Point(50, 300);
        ctverecPoint.add(p1);
        ctverecPoint.add(p2);
        ctverecPoint.add(p3);
        ctverecPoint.add(p4);

        return ctverecPoint;
    }

    public void drawDDA(int x1, int y1, int x2, int y2, int color) {
        int dx, dy;
        float k, h, g, x, y;

        dx = x2 - x1;
        dy = y2 - y1;
        k = (float) dy / dx;

        x = x1;
        y = y1;

        if (Math.abs(dx) > Math.abs(dy)) {
            g = 1;
            h = k;

            if (x1 > x2) {
                int a = x1;
                x1 = x2;
                x2 = a;
                int b = y1;
                y1 = y2;
                y2 = b;
            }
            y = y1;
            x = x1;
            for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
                raster.drawPixel(Math.round(x), Math.round(y), color);
                x = x + g;
                y = y + h;
            }
        } else {
            g = 1 / k;
            h = 1;
            if (y1 > y2) {
                int a = y1;
                y1 = y2;
                y2 = a;
                int b = x1;
                x1 = x2;
                x2 = b;
            }
            y = y1;
            x = x1;

            for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
                raster.drawPixel(Math.round(x), Math.round(y), color);
                x = x + g;
                y = y + h;
            }
        }
    }

    public void drawPolygon(List<Point> polygonPoints, int color) {
        for (int i = 0; i < polygonPoints.size() - 1; i++) {
            drawDDA(polygonPoints.get(i).x, polygonPoints.get(i).y, polygonPoints.get(i + 1).x, polygonPoints.get(i + 1).y, color);
        }
        drawDDA(polygonPoints.get(0).x,
                polygonPoints.get(0).y, polygonPoints.get(polygonPoints.size() - 1).x, polygonPoints.get(polygonPoints.size() - 1).y, color);
    }

    public List<Point> clip(List<Point> points, List<Point> clipPoints) {
        if (clipPoints.size() < 2 || points.size() < 2) {
            return points;
        }
        List<Point> newPoints = points;
        Point p1 = clipPoints.get(clipPoints.size() - 1);
        for (Point p2 : clipPoints) {
            newPoints = clipEdge(points, new Edge(p1, p2));
            points = newPoints;
            p1 = p2;
        }
        return newPoints;
    }

    private List<Point> clipEdge(List<Point> points, Edge e) {
        if (points.size() < 2) {
            return points;
        }
        List<Point> out = new ArrayList<>();
        out.clear();
        Point v1 = points.get(points.size() - 1);
        for (Point v2 : points) {
            if (e.isInside(v2)) {
                if (!e.isInside(v1)) {
                    out.add(e.getIntersection(v1, v2));
                }
                out.add(v2);
            } else if (e.isInside(v1)) {
                out.add(e.getIntersection(v1, v2));
            }
            v1 = v2;
        }
        return out;
    }
}
