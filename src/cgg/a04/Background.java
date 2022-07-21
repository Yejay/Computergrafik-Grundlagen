package cgg.a04;

import cgtools.*;

public class Background implements Shape {
    Color color;

    public Background(Color color) {
        this.color = color;
    }

    @Override
    public Hit intersect(Ray r) {
        // TODO Auto-generated method stub
        return new Hit(Double.POSITIVE_INFINITY, r.pointAt(Double.POSITIVE_INFINITY), Vector.negate(r.d()), color);
    }

}
