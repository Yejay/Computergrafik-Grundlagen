package cgg.a02;

import cgtools.*;

public class Disc{
    // Eine Position im Bild
    // cx, cy = centerx, centery
    double cx, cy;

    // Einen Radius
    double radius;

    // Eine Farbe
    Color color;

    public Disc(double cx, double cy, double radius, Color color) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
    }

    // Determines whether a Point with any x and y coordinates is within a Disc or not.
    public boolean isPointInDisc(double x, double y) {
         // >= inside circle, < outside circle
        double distance = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));
        if (radius >= distance) {
            return true;
        }
        return false;
    }
}
