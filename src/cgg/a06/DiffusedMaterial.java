package cgg.a06;

import cgtools.*;

public record DiffusedMaterial(Color color) implements Material {

    //lampe 111 für weiss
    @Override
    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }
    // farbe fürs objekt
    @Override
    public Color albedo(Ray r, Hit h) {
        return color;
    }

    /**
     * @param r the ray
     * @param h the hit
     * @return the scattered ray
     */
    @Override
    public Ray scattered(Ray r, Hit h) {
        double x = Random.random() * 2 - 1;
        double y = Random.random() * 2 - 1;
        double z = Random.random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = Random.random() * 2 - 1;
            y = Random.random() * 2 - 1;
            z = Random.random() * 2 - 1;
        }
        Direction d = Vector.normalize(Vector.add(h.normalenvektor(), Vector.direction(x, y, z)));
        return new Ray(h.trefferpunktX(), d, 0.001, Double.POSITIVE_INFINITY);
    }

}
