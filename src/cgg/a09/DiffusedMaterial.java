package cgg.a09;

import cgtools.*;

public record DiffusedMaterial(Color color) implements Material {

    //lampe 111 für weiss
    @Override
    public Color emission(Ray ray, Hit hit) {
        return Vector.black;
    }
    // farbe fürs objekt
    @Override
    public Color albedo(Ray ray, Hit hit) {
        return color;
    }

    /**
     * @param ray the ray
     * @param hit the hit
     * @return the scattered ray
     */
    @Override
    public Ray scattered(Ray ray, Hit hit) {
        double x = Random.random() * 2 - 1;
        double y = Random.random() * 2 - 1;
        double z = Random.random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = Random.random() * 2 - 1;
            y = Random.random() * 2 - 1;
            z = Random.random() * 2 - 1;
        }
        Direction scatteredDirection = Vector.normalize(Vector.add(hit.normalenvektor(), Vector.direction(x, y, z)));
        return new Ray(hit.trefferpunktX(), scatteredDirection, 0.001, Double.POSITIVE_INFINITY);
    }

}
