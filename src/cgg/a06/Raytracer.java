package cgg.a06;

import cgtools.*;

public class Raytracer implements Sampler {
    private Camera camera;
    private Group scene;

    public Raytracer(Camera camera, Group scene) {
        this.camera = camera;
        this.scene = scene;
    }

    @Override
    public Color getColor(double x, double y) {
        // Color bcolor = Vector.black;
        Ray ray = camera.generateRay(x, y);
        Color color = null;
        Hit hit = scene.intersect(ray);

        if (hit != null) {
            color = calculateRadiance(scene, ray, 5);
        }

        return color;
    }

    public Color calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0) {
            return Vector.black;
        }
        Hit hit = scene.intersect(ray);
        Material material = hit.material();
        Color albedo = material.albedo(ray, hit);
        Color emission = material.emission(ray, hit);
        Ray scattered = material.scattered(ray, hit);
        if (hit.material().scattered(ray, hit) != null) {
            return Vector.add(emission, Vector.multiply(albedo, calculateRadiance(scene, scattered, --depth)));
        }
        return emission;
    }

    // public static Color shade(Direction normal, Color color) {
    //     Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
    //     Color ambient = Vector.multiply(0.1, color);
    //     Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
    //     return Vector.add(ambient, diffuse);
    // }
}
