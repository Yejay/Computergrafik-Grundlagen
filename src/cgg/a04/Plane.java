package cgg.a04;

import cgtools.*;

public class Plane implements Shape {
    Point point;
    Direction normalVector;
    Color color;
    double radius;
    Color surfaceColor = Vector.gray;

    public Plane(Point point, Direction normalVector, Color color, double radius) {
        this.point = point;
        this.normalVector = normalVector;
        this.color = color;
        this.radius = radius;
    }

    public Plane(Point point, Direction normalVector, Color color) {
        this.point = point;
        this.normalVector = normalVector;
        this.color = color;
        this.radius = Double.POSITIVE_INFINITY;
    }

    @Override
    public Hit intersect(Ray r) {
        double t = Vector.dotProduct(Vector.subtract(point, r.x0()), Vector.normalize(normalVector))
                / Vector.dotProduct(r.d(), Vector.normalize(normalVector));
        if (r.isValid(t)) {
            Point position = r.pointAt(t);
            // Für alle Punkte x auf der Ebene mit Abstand von p kleiner r
            /* |x-p| < r */
            if (Vector.length((Vector.subtract(position, point))) < radius) {
                return new Hit(t, position, normalVector, color);
            }
        }
        return null;
    }

}
