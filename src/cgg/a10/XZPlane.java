package cgg.a10;

import cgtools.*;

public class XZPlane implements Shape {
    Point anchorPoint;
    Direction normalVector;
    Material material;
    int width;
    int height;
    double radius;
    // might not be needed
    Color surfaceColor = Vector.gray;

    public XZPlane(Point anchorPoint, Direction normalVector, Material material, int width, int height, double radius) {
        this.anchorPoint = anchorPoint;
        this.normalVector = normalVector;
        this.material = material;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    public XZPlane(Point anchorPoint, Direction normalVector, Material material, int width, int height) {
        this.anchorPoint = anchorPoint;
        this.normalVector = normalVector;
        this.material = material;
        this.width = width;
        this.height = height;
        this.radius = Double.POSITIVE_INFINITY;
    }

    @Override
    public Hit intersect(Ray ray) {
        double nd = Vector.dotProduct(ray.d(), normalVector);
        if (nd == 0) {
            return null;
        }

        double t = Vector.dotProduct(Vector.subtract(anchorPoint, ray.origin()), normalVector) / nd;
        if (!ray.contains(t)) {
            return null;
        }

        Point position = ray.pointAt(t);
        if (Math.abs(position.x()) > width / 2 || Math.abs(position.z()) > height / 2) {
            return null;
        }
        Direction textureStretched = new Direction(position.x() / width + 0.5, position.z() / height + 0.5, 0);

        // position.x() / width + 0.5, position.z() / height + 0.5
        return new Hit(t, position, normalVector, material, textureStretched);

    }

    @Override
    public BoundingBox bounds() {
        // TODO Auto-generated method stub
        return null;
    }

}
