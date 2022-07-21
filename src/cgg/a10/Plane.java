package cgg.a10;

import cgtools.*;

public class Plane implements Shape {
    Point anchorPoint;
    Direction normalVector;
    Material material;
    int width;
    int height;
    double radius;
    

    public Plane(Point anchorPoint, Direction normalVector, Material material, int width, int height, double radius) {
        this.anchorPoint = anchorPoint;
        this.normalVector = normalVector;
        this.material = material;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    public Plane(Point anchorPoint, Direction normalVector, Material material, int width, int height) {
        this.anchorPoint = anchorPoint;
        this.normalVector = normalVector;
        this.material = material;
        this.width = width;
        this.height = height;
        this.radius = Double.POSITIVE_INFINITY;
    }

    @Override
    public Hit intersect(Ray ray) {
        double t = Vector.dotProduct(Vector.subtract(anchorPoint, ray.origin()), Vector.normalize(normalVector))
                / Vector.dotProduct(ray.d(), Vector.normalize(normalVector));
        if (ray.isValid(t)) {
            Point position = ray.pointAt(t);
            // Für alle Punkte x auf der Ebene mit Abstand von p kleiner r
            /* |x-p| < r */
            if (Vector.length((Vector.subtract(position, anchorPoint))) < radius) {
                // TODO: check if this is correct wegen texture mapping uv
                Direction textureStretched = new Direction(position.x() / width + 0.5, position.z() / height + 0.5, 0);


                // Direction repeated = new Direction(position.x() - Math.floor(position.x()),
                //         position.z() - Math.floor(position.z()), 0);

                return new Hit(t, position, normalVector, material, textureStretched);
                // return new Hit(t, position, normalVector, material, repeated);
            }
        }
        return null;
    }

 

    @Override
    public BoundingBox bounds() {
        // TODO Auto-generated method stub
        return null;
    }

}
