package cgg.a08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cgg.Image;
import cgtools.*;

public class Main {
        public static void main(String[] args) {
                final int width = 1920;
                final int height = 1080;

                List<Shape> sceneShapes = new ArrayList<>();
                List<Shape> kurby = new ArrayList<>();

                // Materials
                DiffusedMaterial anthraziMaterial = new DiffusedMaterial(Vector.color(0.05, 0.05, 0.05));
                DiffusedMaterial pink = new DiffusedMaterial(Vector.color(1, 0.6, 0.8));
                DiffusedMaterial darkPink = new DiffusedMaterial(Vector.color(1, 0.4, 0.70196));
                DiffusedMaterial whiteMaterial = new DiffusedMaterial(Vector.color(1, 1, 1));
                DiffusedMaterial redMaterial = new DiffusedMaterial(Vector.color(1, 0, 0));
                PolishedMetalMaterial lightGreenRoughMirror = new PolishedMetalMaterial(Vector.color(0.5, 1, 0.5), 0);
                PolishedMetalMaterial groundMirror = new PolishedMetalMaterial(Vector.color(0.5, 0.5, 1), 0.05);
                
                // Shapes
                Shape background = new Background(new BackgroundMaterial(Vector.color(1, 0.6, 0.8)));
                Shape ground = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0),
                                groundMirror);

                Collections.addAll(sceneShapes, background, ground);

                Shape head = new Sphere(new Point(0, 0.1, -2), 0.5, pink);
                Shape eyeLeft = new Sphere(new Point(-0.15, 0.3, -1.6), 0.1, whiteMaterial);
                Shape eyeRight = new Sphere(new Point(0.15, 0.3, -1.6), 0.1, whiteMaterial);
                Shape pupilLeft = new Sphere(new Point(-0.15, 0.3, -1.5), 0.03, anthraziMaterial);
                Shape pupilRight = new Sphere(new Point(0.15, 0.3, -1.5), 0.03, anthraziMaterial);
                Shape mouth = new Sphere(new Point(0, 0, -1.65), 0.2, darkPink);
                Shape armLeft = new Sphere(new Point(-0.45, 0.1, -2), 0.2, pink);
                Shape armtRight = new Sphere(new Point(0.45, 0.1, -2), 0.2, pink);
                Shape footLeft = new Sphere(new Point(-0.4, -0.4, -2), 0.2, redMaterial);
                Shape footRight = new Sphere(new Point(0.4, -0.4, -2), 0.2, redMaterial);

                Collections.addAll(kurby, head, eyeLeft, eyeRight, pupilLeft, pupilRight, mouth, armLeft, armtRight,
                                footLeft, footRight);

                double sphereAmount = 50;
                double Xmin = -8;
                double Xmax = 8;
                double Ymin = -0.2;
                double Ymax = 3.5;
                double Zmin = -30;
                double Zmax = 1.5;
                for (int i = 0; i < sphereAmount; i++) {
                        // new Random().nextInt(max - min + 1) + min
                        double randX = new Random().nextDouble(Xmax - Xmin + 1) + Xmin;
                        double randY = new Random().nextDouble(Ymax - Ymin + 1) + Ymin;
                        double randZ = new Random().nextDouble(Zmax - Zmin + 1) + Zmin;
                        double randRadius = new Random().nextDouble(0.3 - 0.1) + 0.1;
                        Shape sphere = new Sphere(new Point(randX, randY, randZ), randRadius, lightGreenRoughMirror);
                        sceneShapes.add(sphere);

                }

                for (int i = 0; i < sphereAmount; i++) {
                        // new Random().nextInt(max - min + 1) + min
                        double randX = new Random().nextDouble(Xmax - Xmin + 1) + Xmin;
                        double randY = new Random().nextDouble(Ymax - Ymin + 1) + Ymin;
                        double randZ = new Random().nextDouble(Zmax - Zmin + 1) + Zmin;
                        double randomAngle = new Random().nextDouble(360 - 0 + 1) + 0;
                        Matrix translate = Matrix.translation(randX, randY, randZ);
                        Matrix rotation = Matrix.rotation(randX, randY, randZ, randomAngle);
                        Group kurbies = new Group(kurby, new Transform((Matrix.multiply(translate, rotation))));
                        Collections.addAll(sceneShapes, kurbies);
                }

                // Settings

                /**
                * translation
                * x: negativ = links, positiv = rechts
                * y: negativ = oben, positiv = unten
                * z: negativ = vorne, positiv = hinten
                * 
                * rotation/direction
                * x: negativ = neigung nach unten, positiv = neigung nach oben
                * -90 = Vogelperspektive
                * y: negativ = rotation nach links, positiv = rotation nach rechts
                * z: negativ = rotation nach vorne, positiv = rotation nach hinten
                */
                Camera camera = new Camera(Math.PI / 3, width, height, new Direction(-5, 0, 0),
                                Matrix.translation(0, 3.5, 6));
                Camera camera2 = new Camera(Math.PI / 3, width, height, new Direction(-55, 0, 0),
                                Matrix.translation(0, 30, 6));

                Group sceneOneGroup = new Group(sceneShapes, new Transform(Matrix.translation(0, 0, -4)));
                Group sceneTwoGroup = new Group(sceneShapes, new Transform(Matrix.translation(0, 0, -4)));

                // This class instance defines the contents of the image.
                Raytracer content = new Raytracer(camera, sceneOneGroup);
                Raytracer content2 = new Raytracer(camera2, sceneTwoGroup);

                // Creates an image and iterates over all pixel positions inside the image
                Image image = new Image(width, height);
                Image image2 = new Image(width, height);

                image.sample(content, 10);
                image2.sample(content2, 10);

                final String filename = "doc/a08-1.png";
                final String filename2 = "doc/a08-2.png";

                image.write(filename);
                image2.write(filename2);

                System.out.println("Wrote image: " + filename);
                System.out.println("Wrote image: " + filename2);

        }

}
