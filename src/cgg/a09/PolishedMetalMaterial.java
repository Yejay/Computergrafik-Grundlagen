package cgg.a09;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

/**
 * Represents a polished metal material or mirror.
 * 
 * @author Yejay Demirkan
 * @param texture - Color of the material
 * @param reflectionCoefficient - Reflection coefficient of the material (between 0 and 1) (0 = no reflection, 1 = perfect mirror)
 */
public record PolishedMetalMaterial(Color color, double reflectionCoefficient) implements Material {

    @Override
    public Color emission(Ray ray, Hit hit) {
        return Vector.black;
    }

    @Override
    public Color albedo(Ray ray, Hit hit) {
        return color;
    }

    @Override
    public Ray scattered(Ray ray, Hit hit) {
        // Zufällige Richtung r = x * 2 - 1, y * 2 - 1, z * 2 - 1, siehe Foliensatz Stochastisches Raytracing S. 5
        double x = Random.random() * 2 - 1;
        double y = Random.random() * 2 - 1;
        double z = Random.random() * 2 - 1;
        // Reflektionsrichtung r
        // r = d + b + b, b = (-d * n)n
        // r = d - 2(d * n)n
        Direction reflection = Vector.subtract(ray.d(), Vector.multiply(2 * Vector.dotProduct(ray.d(), hit.normalenvektor()), hit.normalenvektor()));

        /** 
         * Creating a non-perfect mirror is done by adding a random direction to the reflection direction.
         * When the reflexion coefficient is not 0 (0 = perfect mirror), a new random direction is created and added to the reflection direction.
        */ 
        if (reflectionCoefficient != 0) {
            Direction randomD = Vector.direction(x, y, z);
            reflection = Vector.add(reflection, Vector.multiply(reflectionCoefficient, randomD));
        }

        /**
         * Creating a new ray with the new direction and the same origin as the original ray.
         * tmin is set to 0.001, to differentiate the ray from the original ray.
         */
        return new Ray(hit.trefferpunktX(), reflection, 0.0001, Double.POSITIVE_INFINITY);
    }

}
