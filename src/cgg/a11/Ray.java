package cgg.a11;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Ray(Point origin, Direction d, double tmin, double tmax) {
    // x(t) = x0 + td
    // Strahlparameter t
    public Point pointAt(double t) {
        return Vector.add(Vector.multiply(d, t), origin);
    }

    public boolean isValid(double t) {
        if (tmin < t && t <= tmax) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(double t) {
        return tmin < t && t <= tmax;
    }

}
