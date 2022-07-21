package cgg.a04;

import cgtools.*;

public class Raytracer implements Sampler {
    private Camera camera;
    private Group scene;

    public Raytracer(Camera camera, Group scene) {
        this.camera = camera;
        this.scene = scene;
    }

    // TODO
    @Override
    public Color getColor(double x, double y) {
        // Color bcolor = Vector.black;
        Color sphereColor = Vector.black;
        Ray r = camera.generateRay(x, y);
        Hit hit = scene.intersect(r);

        if (hit != null) {
            sphereColor = shade(hit.n(), hit.c());
            // return sphereColor;
        }

        return sphereColor;
    }

    public static Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.1, color);
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
        return Vector.add(ambient, diffuse);
    }
}
