package cgg.a10;

import cgtools.Color;
import cgtools.Direction;
import cgtools.ImageTexture;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

/**
 * Represents a polished metal material or mirror.
 * 
 * @author Yejay Demirkan
 * @param texture - Color of the material
 * @param reflectionCoefficient - Reflection coefficient of the material (between 0 and 1) (0 = no reflection, 1 = perfect mirror)
 */
public class PolishedMetalMaterial implements Material {
    Sampler texture;
    double reflectionCoefficient;

    /***
     * Default Constructor for perfect mirror with constant color white.
     */
    public PolishedMetalMaterial() {
        this.texture = new Constant(Vector.white);
        this.reflectionCoefficient = 0;
    }

    /***
     * Constructor for the glass material.
     * 
     * @param texture - Color of the material
     * @param reflectionCoefficient - Reflection coefficient of the material (between 0 and 1) (0 = no reflection, 1 = perfect mirror)
     */
    public PolishedMetalMaterial(double reflectionCoefficient) {
        this.texture = new Constant(Vector.white);
        this.reflectionCoefficient = reflectionCoefficient;
    }

    /***
     * Constructor with reflection coefficient and color as parameter
     * 
     * @param reflectionCoefficient - Reflection coefficient of the material (between 0 and 1) (0 = no reflection, 1 = perfect mirror) 
     * @param color - color of the mirror
     */
    public PolishedMetalMaterial(double reflectionCoefficient, Color color) {
        this.texture = new Constant(color);
        this.reflectionCoefficient = reflectionCoefficient;
    }

    /***
     * Constructor with reflection coefficient and color as parameter
     * 
     * @param reflectionCoefficient - Reflection coefficient of the material (between 0 and 1) (0 = no reflection, 1 = perfect mirror) 
     * @param texture - texture of the mirror
     */
    public PolishedMetalMaterial(double reflectionCoefficient, Sampler texture) {
        this.texture = texture;
        this.reflectionCoefficient = reflectionCoefficient;
    }

    /***
     * Constructor with reflection coefficient and color as parameter
     * 
     * @param reflectionCoefficient - Reflection coefficient of the material (between 0 and 1) (0 = no reflection, 1 = perfect mirror) 
     * @param filename - path to the file to be used as texture
     */
    public PolishedMetalMaterial(double reflectionCoefficient, String filename) {
        this.texture = new Texture(new ImageTexture(filename));
        this.reflectionCoefficient = reflectionCoefficient;
    }

    @Override
    public Color emission(Ray ray, Hit hit) {
        return Vector.black;
    }

    @Override
    public Color albedo(Ray ray, Hit hit) {
        return texture.getColor(hit.texture().x(), hit.texture().y());
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
        Direction reflection = Vector.subtract(ray.d(),
                Vector.multiply(2 * Vector.dotProduct(ray.d(), hit.normalenvektor()), hit.normalenvektor()));

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
