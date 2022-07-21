package cgg.a07;

import cgtools.*;

/**
 * Represents solid glass material.
 * Not window glass.
 */
public class GlassMaterial implements Material {
    // Color of the material
    Color color;
    // Refractive indices n1 and n2 of the material (Air = 1, Glass = 1.5, Water = 1.33)
    double n1;
    double n2;

    /**
     * Constructor for the glass material.
     * 
     * @param texture - Color of the material
     * @param n1 - Refractive index of the material
     * @param n2 - Refractive index of the material
     */
    public GlassMaterial(double n2) {
        this.color = Vector.white;
        this.n1 = 1.0;
        this.n2 = n2;
    }

    public GlassMaterial(double n2, Color color) {
        this.color = color;
        this.n1 = 1.0;
        this.n2 = n2;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return color;
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        Direction scattered = null;
        // Normalized vector of the direction of the ray
        Direction normal = Vector.normalize(h.normalenvektor());
        // Direction of the ray
        Direction direction = r.d();
        /**
         * If dot product of direction and normal is positive, the ray is coming from the inside (negative from the outside), 
         * so we need to negate the normal vector and swap the refractive indices.
         */
        if (Vector.dotProduct(direction, normal) > 0) {
            // negate normal vector
            normal = Vector.negate(normal);
            // normal = Vector.multiply(normal, -1);

            // swap refractive indices
            double temp = n1;
            n1 = n2;
            n2 = temp;
        }

        if (refract(direction, normal, n1, n2) != null) {
            if (Random.random() > schlick(direction, normal, n1, n2)) {
                scattered = refract(direction, normal, n1, n2);
            } else {
                scattered = reflect(direction, normal);
            }
        } else {
            scattered = reflect(direction, normal);
        }
        return new Ray(h.trefferpunktX(), scattered, 0.0001, Double.POSITIVE_INFINITY);
    }

    /**
     * Calculates the refraction(brechung des lichts) vector of a ray.
     * 
     * @param direction - Direction of the ray
     * @param normal - Normal vector of the surface
     * @param n1 - Refractive index of the material
     * @param n2 - Refractive index of the material
     * @return - Refraction vector of the ray
     */
    private Direction refract(Direction direction, Direction normal, double n1, double n2) {
        double r = n1 / n2;
        double c = Vector.dotProduct(Vector.negate(normal), direction);
        // discriminant
        double discriminant = 1.0 - r * r * (1.0 - c * c);
        // double dt = ((1 - Math.pow(r, 2) * (1 - Math.pow(c, 2))));

        // if dt is negative, the ray is not refracted, but reflected - total reflection
        if (discriminant > 0) {
            // r * direction + (r * c - Math.sqrt(1 - r * r (1 - c * c)) * normal)
            Direction dt = Vector.add(Vector.multiply(r, direction),Vector.multiply(r * c - Math.sqrt(discriminant), normal));
            return dt;
        }
        return null;
    }

    private double schlick(Direction direction, Direction normal, double n1, double n2) {
        // Speculiar reflection factor := R(0) = r0 + (1 - r0) * (1 - cos(theta))^5 = r0 + (1 - r0) * (1 + normal * direction)^5
        double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);
        double speculiarReflectionFactor = r0 + (1 - r0) * Math.pow((1 + Vector.dotProduct(normal, direction)), 5);
        return speculiarReflectionFactor;
    }

    /**
     * Calculates the reflection vector of a ray.
     * 
     * @param direction
     * @param normal
     * @return - Reflection vector of the ray
     */
    private Direction reflect(Direction direction, Direction normal) {
        return Vector.subtract(direction,
                (Vector.multiply(2, Vector.multiply(Vector.dotProduct(normal, direction), normal))));
    }
}
