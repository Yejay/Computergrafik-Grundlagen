package cgg.a06;

import cgtools.*;
// Outdated: use GlassMaterial.java instead
public class GlassMaterial2 implements Material{
    Color color;
    double n1 = 1.0;
    double n2;

    public GlassMaterial2(double n2) {
        this.color = Vector.white;
        this.n2 = n2;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        // TODO Auto-generated method stub
        return Vector.black;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        // TODO Auto-generated method stub
        return color;
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        Ray ray;
        Direction n = Vector.normalize(h.normalenvektor());

        // If Ray origin is from INSIDE
        if (Vector.dotProduct(n, r.d()) > 0) {
            // Negate normal vector
            n = Vector.multiply(-1, n);
            // Swap refractive indices
            double temp = n1;
            n1 = n2;
            n2 = temp;
        }

        //Snell's Law: change in dir of a beam as it passes through another medium.
        double c = Vector.dotProduct(Vector.multiply(-1, n), r.d());
        double ratio = n1 / n2;
        double disk = 1 - Math.pow(ratio, 2) * (1 - Math.pow(c, 2));

        // Schlick Approximation: Ratio of reflection to transmisison on the surface.
        // Equivalent to Fresnel reflection & transmission, but shorter.
        double r0 = Math.pow(((n1 - n2) / (n1 + n2)), 2);
        double schlick = r0 + (1 - r0) * Math.pow(1 + Vector.dotProduct(n, r.d()), 5);

        if (disk >= 0 && Random.random() > schlick) {
            // Direction of scattered Ray after refraction
            Direction scatDir = Vector.add(Vector.multiply(ratio, r.d()), Vector.multiply((ratio * c - Math.sqrt(disk)), n));
            ray = new Ray(h.trefferpunktX(), scatDir, 0.0001, Double.POSITIVE_INFINITY);
        } else {
            // Total reflexion
            Direction d = Vector.subtract(r.d(), Vector.multiply(2 * Vector.dotProduct(n, r.d()), n));
            ray = new Ray(h.trefferpunktX(), d, 0.0001, Double.POSITIVE_INFINITY);
        }
        return ray;
    }
    
}
