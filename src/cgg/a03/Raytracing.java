package cgg.a03;

import java.util.ArrayList;

import cgtools.*;

public class Raytracing implements Sampler {
    private Camera camera;
    private ArrayList<Sphere> spheres;

    // WORKING EXAMPLE DONT DELETE
    // @Override
    // public Color getColor(double x, double y) {
    // Color bcolor = new Color(0, 0, 0);
    // Ray r = camera.generateRay(x, y);

    // Hit hit = sphere.intersect(r);
    // if (hit == null) {
    // return bcolor;
    // }
    // Color sphereColor = shade(hit.n(), sphere.color());
    // return sphereColor;
    // }

    // TODO
    @Override
    public Color getColor(double x, double y) {
        Color bcolor = Vector.black;
        Color sphereColor;
        Ray r = camera.generateRay(x, y);
        double tmax = Double.POSITIVE_INFINITY;
        for (Sphere sphere1 : spheres) {
            if (sphere1.intersect(r) != null) {
                if (tmax > sphere1.intersect(r).t()) {
                    tmax = sphere1.intersect(r).t();
                    sphereColor = shade(sphere1.intersect(r).n(), sphere1.color());
                    return sphereColor;
                }
            }
        }
        return bcolor;
    }

    public Raytracing(Camera camera, ArrayList<Sphere> spheres) {
        this.camera = camera;
        this.spheres = spheres;
    }

    public static Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.1, color);
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
        return Vector.add(ambient, diffuse);
    }
}
