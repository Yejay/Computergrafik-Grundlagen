package cgg.a08;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Ray(Point x0, Direction d, double tmin, double tmax) {
    // x(t) = x0 + td
    // Strahlparameter t
    public Point pointAt(double t) {
        return Vector.add(Vector.multiply(d, t), x0);
    }

    public boolean isValid(double t) {
        if (tmin < t && t <= tmax) {
            return true;
        } else {
            return false;
        }
    }

}
