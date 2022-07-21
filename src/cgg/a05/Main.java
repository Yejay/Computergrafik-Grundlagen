package cgg.a05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cgg.Image;
import cgtools.*;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;
        DiffusedMaterial anthraziMaterial = new DiffusedMaterial(Vector.color(0.05, 0.05, 0.05));
        DiffusedMaterial whiteMaterial = new DiffusedMaterial(Vector.color(1, 1, 1));
        DiffusedMaterial darkredMaterial = new DiffusedMaterial(Vector.color(0.35, 0, 0));
        DiffusedMaterial darkredMaterial2 = new DiffusedMaterial(Vector.color(0.1, 0, 0));

        // Prof
        Camera camera = new Camera(Math.PI / 3, width, height);
        Shape background = new Background(new BackgroundMaterial(Vector.white));
        Shape ground = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), darkredMaterial2);

        Shape globe1 = new Sphere(new Point(1.2, 0.0, -3.0), 0.5, darkredMaterial);
        Shape globe2 = new Sphere(new Point(0.0, -0.1, -3.0), 0.6, anthraziMaterial);
        Shape globe3 = new Sphere(new Point(-0.25, -0.4, -1.8), 0.15, darkredMaterial);
        Shape globe4 = new Sphere(new Point(0.65, 0.4, -2.5), 0.3, whiteMaterial);
        Shape globe5 = new Sphere(new Point(0.6, -0.3, -2.0), 0.2, anthraziMaterial);
        Shape globe6 = new Sphere(new Point(0.13, -0.35, -2.0), 0.18, whiteMaterial);
        Shape globe7 = new Sphere(new Point(0.8, -0.45, -1.6), 0.07, darkredMaterial);
        Shape globe8 = new Sphere(new Point(0.6, -0.45, -1.6), 0.06, whiteMaterial);
        Shape globe9 = new Sphere(new Point(0.4, -0.45, -1.6), 0.05, anthraziMaterial);
        // My stuff

        List<Shape> shapes = new ArrayList<>();

        Collections.addAll(shapes, background, ground, globe1, globe2, globe3, globe4, globe5, globe6, globe7, globe8,
                globe9);

        Group group = new Group(shapes);

        // This class instance defines the contents of the image.
        Raytracer content = new Raytracer(camera, group);

        // Raytracer content2 = new Raytracer(camera, scene);

        // Creates an image and iterates over all pixel positions inside the image
        Image image = new Image(width, height);

        image.sample(content, 10);

        final String filename = "doc/a05-diffuse-spheres.png";

        image.write(filename);

        System.out.println("Wrote image: " + filename);

    }

}
