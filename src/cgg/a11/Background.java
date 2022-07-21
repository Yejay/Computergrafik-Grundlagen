package cgg.a11;

import cgtools.*;


public class Background implements Shape {
    Material material;

    public Background(Material material) {
        this.material = material;
    }

    @Override
    public Hit intersect(Ray ray) {

        double t = Double.POSITIVE_INFINITY;
        Point point = ray.pointAt(t);
        // uv
        double inclination = Math.acos(ray.d().y());
        double azimut = Math.PI + Math.atan2(ray.d().x(), ray.d().z());
        double u = azimut / (2 * Math.PI);
        double v = inclination / Math.PI;
        Direction uv = new Direction(u, v, 0);
        return new Hit(t, point, Vector.negate(ray.d()), material, uv);
    }

    @Override
    public BoundingBox bounds() {
        // TODO Auto-generated method stub
        return null;
    }

}
