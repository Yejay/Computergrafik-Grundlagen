package cgg.a04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cgg.Image;
import cgtools.*;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;
        Color darkgrey = Vector.color(128.0 / 256.0, 128.0 / 256.0, 128.0 / 256.0);
        Color darkdarkgrey = Vector.color(5.0 / 256.0, 5.0 / 256.0, 5.0 / 256.0);

        Color globe1Color = Vector.color(255.0 / 256.0, 72.0 / 256, 0.0 / 256);
        Color globe2Color = Vector.color(255.0 / 256.0, 121.0 / 256, 0.0 / 256);
        Color globe3Color = Vector.color(255.0 / 256.0, 182.0 / 256, 0.0 / 256);

        Color lavenderPurple = Vector.color(149.0 / 255.0, 125.0 / 256.0, 173.0 / 255.0);

        // Prof
        Camera camera = new Camera(Math.PI / 3, width, height);
        Shape background = new Background(darkgrey);
        Shape ground = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), Vector.gray);
        Shape globe1 = new Sphere(new Point(-1.0, -0.25, -2.5), 0.7, Vector.red);
        Shape globe2 = new Sphere(new Point(0.0, -0.25, -2.5), 0.5, Vector.green);
        Shape globe3 = new Sphere(new Point(1.0, -0.25, -2.5), 0.7, Vector.blue);

        // My stuff
        Camera camera2 = new Camera(Math.PI / 3, width, height);
        Shape background2 = new Background(darkdarkgrey);

        Shape globeA = new Sphere(new Point(-4, 0.5, -10), 0.4, lavenderPurple);

        Shape globeB = new Sphere(new Point(0.0, -0.25, -5), 1, globe1Color);
        Shape globeBplane = new Plane(new Point(0.0, -0.25, -5), new Direction(0, 1, 0), darkgrey, 2.2);

        Shape globeC = new Sphere(new Point(1.5, 0.5, -8), 0.4, globe2Color);
        Shape globeD = new Sphere(new Point(-1.8, -0.25, -5), 0.1, globe3Color);

        List<Shape> shapes = new ArrayList<>();
        List<Shape> shapes2 = new ArrayList<>();

        Collections.addAll(shapes, background, ground, globe1, globe2, globe3);
        Collections.addAll(shapes2, background2, globeA, globeB, globeC, globeD, globeBplane);

        Group group = new Group(shapes);
        Group group2 = new Group(shapes2);

        // This class instance defines the contents of the image.
        Raytracer content = new Raytracer(camera, group);
        Raytracer content2 = new Raytracer(camera2, group2);
        // Raytracer content2 = new Raytracer(camera, scene);

        // Creates an image and iterates over all pixel positions inside the image
        Image image = new Image(width, height);
        Image image2 = new Image(width, height);

        image.sample(content, 10);
        image2.sample(content2, 10);

        final String filename = "doc/a04-3-spheres.png";
        final String filename2 = "doc/a04-scene.png";

        image.write(filename);
        image2.write(filename2);
        System.out.println("Wrote image: " + filename);
        System.out.println("Wrote image: " + filename2);
    }

}
