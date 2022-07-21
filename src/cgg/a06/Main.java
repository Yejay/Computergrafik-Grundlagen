package cgg.a06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cgg.Image;
import cgtools.*;

public class Main {
    public static void main(String[] args) {
        // Default: 480
        final int width = 3840;
        // Default: 270
        final int height = 2160;
        DiffusedMaterial anthraziMaterial = new DiffusedMaterial(Vector.color(0.05, 0.05, 0.05));
        DiffusedMaterial darkgreyMaterial = new DiffusedMaterial(Vector.color(0.1, 0.1, 0.1));
        DiffusedMaterial darkredMaterial = new DiffusedMaterial(Vector.color(0.35, 0, 0));
        DiffusedMaterial paleGreenMaterial = new DiffusedMaterial(Vector.color(0.1, 0.8, 0.1));
        PolishedMetalMaterial polishedMetalMaterial = new PolishedMetalMaterial(Vector.color(0.8, 0.8, 0.8), 0);
        PolishedMetalMaterial polishedMetalMaterial2 = new PolishedMetalMaterial(Vector.color(1, 1, 1), 0.3);
        GlassMaterial glassMaterial = new GlassMaterial(1.5);

        // Scene 1
        Camera camera = new Camera(Math.PI / 3, width, height);
        Shape background = new Background(new BackgroundMaterial(Vector.white));
        Shape ground = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), darkgreyMaterial);

        Shape globe1 = new Sphere(new Point(1.2, 0.0, -3.0), 0.2, darkredMaterial);
        Shape globe2 = new Sphere(new Point(0.0, -0.1, -3.0), 0.2, anthraziMaterial);
        Shape globe3 = new Sphere(new Point(-0.5, -0.4, -1.8), 0.18, polishedMetalMaterial);
        // Shape globe3 = new Sphere(new Point(0.3, -0.1, -1.8), 0.4, glassMaterial);
        // Shape globe4 = new Sphere(new Point(0.65, 0.4, -2.5), 0.3, whiteMaterial);
        Shape globe4 = new Sphere(new Point(-1.0, 0.2, -2.5), 0.3, glassMaterial);
        Shape globe5 = new Sphere(new Point(0.3, -0.1, -1.5), 0.2, glassMaterial);
        Shape globe6 = new Sphere(new Point(0.13, -0.35, -3.0), 0.2, polishedMetalMaterial2);
        Shape globe7 = new Sphere(new Point(0.8, -0.45, -3.0), 0.1, darkredMaterial);
        Shape globe8 = new Sphere(new Point(0.6, -0.45, -3.0), 0.1, anthraziMaterial);
        Shape globe9 = new Sphere(new Point(0.4, -0.45, -3.0), 0.1, paleGreenMaterial);

        // Scene 2
        Camera camera2 = new Camera(Math.PI / 3, width, height);
        Shape background2 = new Background(new BackgroundMaterial(Vector.white));
        Shape ground2 = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), paleGreenMaterial);
        Shape g1 = new Sphere(new Point(1.0, 0.0, -2.5), 0.3, polishedMetalMaterial2);
        Shape g2 = new Sphere(new Point(-0.3, 0.0, -3.5), 0.3, anthraziMaterial);
        Shape g3 = new Sphere(new Point(0, -0.2, -2.0), 0.25, glassMaterial);
        Shape g4 = new Sphere(new Point(0.3, -0.2, -3.5), 0.3, darkredMaterial);
        Shape g5 = new Sphere(new Point(-1.0, 0.0, -3.0), 0.4, polishedMetalMaterial);
        // Shape g5 = new Sphere(new Point(-1.0, 0.0, -2.5), 0.4, glassMaterial);
        // Shape g6 = new Sphere(new Point(-1.0, 0.0, -2.5), 0.2, glassMaterial);

        List<Shape> shapes = new ArrayList<>();
        List<Shape> shapes2 = new ArrayList<>();

        Collections.addAll(shapes, background, ground, globe1, globe2, globe3, globe4, globe5, globe6, globe7, globe8,
                globe9);

        Collections.addAll(shapes2, background2, ground2, g1, g2, g3, g4, g5);

        Group group = new Group(shapes);
        Group group2 = new Group(shapes2);

        // This class instance defines the contents of the image.
        Raytracer content = new Raytracer(camera, group);
        Raytracer content2 = new Raytracer(camera2, group2);
        // Raytracer content2 = new Raytracer(camera, scene);

        // Creates an image and iterates over all pixel positions inside the image
        Image image = new Image(width, height);
        Image image2 = new Image(width, height);

        image.sample(content, 15);
        image2.sample(content2, 15);

        final String filename = "doc/a06-mirrors-glass-1.png";
        final String filename2 = "doc/a06-mirrors-glass-2.png";

        image.write(filename);
        image2.write(filename2);

        System.out.println("Wrote image: " + filename);
        System.out.println("Wrote image: " + filename2);

    }

}
