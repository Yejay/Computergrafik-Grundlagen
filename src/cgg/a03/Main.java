package cgg.a03;

import java.util.ArrayList;

import cgg.Image;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;
        final double cameraOpeningAngle = Math.PI / 2;

        /**
         * TESTS AUFGABE 3.2 LOCHKAMERA
         * (-1/sqrt(3), -1/sqrt(3), -1/sqrt(3) == (-0,58, -0,58, -0,58)
         */
        Camera camera = new Camera(Math.PI / 2, 10, 10);
        System.out.println("x0(R(x,y)) expected to be (0,0,0): ");
        System.out.println("TEST (x, y): " + camera.generateRay(5, 5).x0() + "\n");

        System.out.println("d normalized(R(0,0)) expected to be (-0,58, 0,58, -0,58):");
        System.out.println("TEST (0, 0): " + camera.generateRay(0, 0).d() + "\n");

        System.out.println("d normalized(R(5,5)) expected to be (0,0,-1):");
        System.out.println("TEST (5, 5): " + camera.generateRay(5, 5).d() + "\n");

        System.out.println("d normalized(R(10,10)) expected to be (0,58, -0,58, -0,58)):");
        System.out.println("TEST (10, 10): " + camera.generateRay(10, 10).d() + "\n");

        Sphere testSphere = new Sphere(1, Vector.green, new Point(0, 0, -2));
        Hit hit = testSphere
                .intersect(new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0, Double.POSITIVE_INFINITY));
        System.out.println("HIT x:" + hit.x());

        // TESTS ENDE

        Sphere greenSphere = new Sphere(1.5, Vector.green, new Point(0, 1, -6));
        Sphere blueSphere = new Sphere(1.5, Vector.blue, new Point(3, 0, -6));
        Sphere redSphere = new Sphere(1.5, Vector.red, new Point(-3, 0, -6));
        ArrayList<Sphere> spheres = new ArrayList<>();
        spheres.add(greenSphere);
        spheres.add(blueSphere);
        spheres.add(redSphere);

        // This class instance defines the contents of the image.
        Raytracing content = new Raytracing(new Camera(cameraOpeningAngle, width, height), spheres);

        // Creates an image and iterates over all pixel positions inside the image
        Image image = new Image(width, height);

        image.sample(content, 10);
        final String filename = "doc/a03-three-spheres.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);
    }

}
