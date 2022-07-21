package cgg.a11;

import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public class Raytracer implements Sampler {
    private Camera camera;
    private Group scene;
    private World world;

    public Raytracer(Camera camera, Group scene) {
        this.camera = camera;
        this.scene = scene;
        this.world = new World(null, scene);
    }

    public Raytracer(Camera camera, World world) {
        this.world = world;
        this.camera = camera;
        this.scene = world.scene;
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

        Ray secondaryScatteredRay = material.scattered(ray, hit);
        if (secondaryScatteredRay != null) {
            Color incomingIntensity = Vector.black;
            if (world.lights != null) {
                for (Light light : world.lights) {
                    Color intensity = light.incomingIntensity(hit, scene);
                    intensity = Vector.multiply(intensity, albedo);
                    incomingIntensity = Vector.add(incomingIntensity, intensity);
                }
            }
            Color a1 = Vector.add(albedo, emission);
            Color radiance = Vector.multiply(a1, calculateRadiance(scene, secondaryScatteredRay, depth - 1));

            if (incomingIntensity != null) {
                radiance = Vector.add(radiance, incomingIntensity);
            }
            return radiance;
        }
        return emission;
    }
}

// public static Color shade(Direction normal, Color color) {
//     Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
//     Color ambient = Vector.multiply(0.1, color);
//     Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
//     return Vector.add(ambient, diffuse);
// }
