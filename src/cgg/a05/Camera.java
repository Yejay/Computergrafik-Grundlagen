package cgg.a05;

import cgtools.*;

public record Camera(double cameraOpeningAngle, double width, double height) {
    public Ray generateRay(double x, double y) {
        Point x0 = new Point(0, 0, 0);
        double xVec = x - width / 2;
        double yVec = -(y - height / 2);
        double zVec = -((width / 2) / Math.tan(cameraOpeningAngle / 2));
        Direction d = Vector.normalize(new Direction(xVec, yVec, zVec));
        Ray ray = new Ray(x0, d, 0, Double.POSITIVE_INFINITY);
        return ray;
    }
}
