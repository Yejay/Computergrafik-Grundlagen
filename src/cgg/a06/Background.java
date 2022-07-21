package cgg.a06;

import cgtools.*;


public class Background implements Shape {
    Material material;

    public Background(Material material) {
        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {
        double t = Double.POSITIVE_INFINITY;
        Point point = r.pointAt(t);
        return new Hit(t, point, Vector.negate(r.d()), material);
    }

}
