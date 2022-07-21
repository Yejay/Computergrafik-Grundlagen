package cgg.a07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cgg.Image;
import cgtools.*;

public class Main {
        public static void main(String[] args) {
                // Default: 480
                final int width = 1920;
                // Default: 270
                final int height = 1080;
                DiffusedMaterial anthraziMaterial = new DiffusedMaterial(Vector.color(0.05, 0.05, 0.05));

                PolishedMetalMaterial polishedMetalMaterial = new PolishedMetalMaterial(Vector.color(0.8, 0.8, 0.8), 0);
                PolishedMetalMaterial polishedMetalMaterial2 = new PolishedMetalMaterial(Vector.color(1, 1, 1), 0.1);
                BackgroundMaterial pinkLight = new BackgroundMaterial(Vector.color(1, 0.6, 0.8));
                BackgroundMaterial darkPinkLight = new BackgroundMaterial(Vector.color(1, 0.4, 0.70196));
                BackgroundMaterial blueLight = new BackgroundMaterial(Vector.color(0, 0, 0.5));
                BackgroundMaterial redLight = new BackgroundMaterial(Vector.color(1, 0, 0));
                BackgroundMaterial light = new BackgroundMaterial(Vector.color(1, 1, 1));

                // Scene 1
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

                Camera camera = new Camera(Math.PI / 3, width, height, new Direction(0, 0, 0),
                                Matrix.translation(0, 2, 6));

                Camera camera2 = new Camera(Math.PI / 3, width, height, new Direction(-15, 0, 0),
                                Matrix.translation(0, 5, 10));

                Shape background = new Background(new BackgroundMaterial(Vector.black));
                Shape ground = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), polishedMetalMaterial2);

                Shape planet1 = new Plane(new Point(0, 0.2, -8), new Direction(0, 0, -6), redLight, 6);

                Shape cylinder1 = new Cylinder(new Direction(-3.5, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder2 = new Cylinder(new Direction(-2.5, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder3 = new Cylinder(new Direction(-1.5, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder4 = new Cylinder(new Direction(-0.75, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder5 = new Cylinder(new Direction(-0, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder6 = new Cylinder(new Direction(0.75, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder7 = new Cylinder(new Direction(1.5, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder8 = new Cylinder(new Direction(2.5, 0, -5), 0.1, 0.8, anthraziMaterial);
                Shape cylinder9 = new Cylinder(new Direction(3.5, 0, -5), 0.1, 0.8, anthraziMaterial);

                Shape cylinder10 = new Cylinder(new Direction(-3.5, 0, -1), 0.1, 4, blueLight);
                Shape cylinder11 = new Cylinder(new Direction(-3.5, 0, -2), 0.1, 4, blueLight);
                Shape cylinder12 = new Cylinder(new Direction(-3.5, 0, -3), 0.1, 4, blueLight);

                Shape cylinder13 = new Cylinder(new Direction(3.5, 0, -1), 0.1, 4, blueLight);
                Shape cylinder14 = new Cylinder(new Direction(3.5, 0, -2), 0.1, 4, blueLight);
                Shape cylinder15 = new Cylinder(new Direction(3.5, 0, -3), 0.1, 4, blueLight);

                Shape globe1 = new Sphere(new Point(-3.5, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe2 = new Sphere(new Point(-2.5, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe3 = new Sphere(new Point(-1.5, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe4 = new Sphere(new Point(-0.75, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe5 = new Sphere(new Point(-0, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe6 = new Sphere(new Point(0.75, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe7 = new Sphere(new Point(1.5, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe8 = new Sphere(new Point(2.5, 0.4, -5), 0.2, polishedMetalMaterial);
                Shape globe9 = new Sphere(new Point(3.5, 0.4, -5), 0.2, polishedMetalMaterial);

                Shape globe10 = new Sphere(new Point(-3.5, -0.2, -5), 0.2, light);
                Shape globe12 = new Sphere(new Point(-2.5, -0.2, -5), 0.2, light);
                Shape globe13 = new Sphere(new Point(-1.5, -0.2, -5), 0.2, light);
                Shape globe14 = new Sphere(new Point(-0.75, -0.2, -5), 0.2, light);
                Shape globe15 = new Sphere(new Point(-0, -0.2, -5), 0.2, light);
                Shape globe16 = new Sphere(new Point(0.75, -0.2, -5), 0.2, light);
                Shape globe17 = new Sphere(new Point(1.5, -0.2, -5), 0.2, light);
                Shape globe18 = new Sphere(new Point(2.5, -0.2, -5), 0.2, light);
                Shape globe19 = new Sphere(new Point(3.5, -0.2, -5), 0.2, light);

                Shape globe20 = new Sphere(new Point(-3.5, 1, -5), 0.4, light);
                Shape globe21 = new Sphere(new Point(-2.5, 1, -5), 0.4, light);
                Shape globe22 = new Sphere(new Point(-1.5, 1, -5), 0.4, light);
                Shape globe23 = new Sphere(new Point(-0.75, 1, -5), 0.4, light);
                Shape globe24 = new Sphere(new Point(-0, 1, -5), 0.4, light);
                Shape globe25 = new Sphere(new Point(0.75, 1, -5), 0.4, light);
                Shape globe26 = new Sphere(new Point(1.5, 1, -5), 0.4, light);
                Shape globe27 = new Sphere(new Point(2.5, 1, -5), 0.4, light);
                Shape globe28 = new Sphere(new Point(3.5, 1, -5), 0.4, light);

                Shape head = new Sphere(new Point(0, 0.1, -2), 0.5, pinkLight);
                Shape eyeLeft = new Sphere(new Point(-0.15, 0.3, -1.6), 0.1, light);
                Shape eyeRight = new Sphere(new Point(0.15, 0.3, -1.6), 0.1, light);
                Shape pupilLeft = new Sphere(new Point(-0.15, 0.3, -1.5), 0.03, anthraziMaterial);
                Shape pupilRight = new Sphere(new Point(0.15, 0.3, -1.5), 0.03, anthraziMaterial);
                Shape mouth = new Sphere(new Point(0, 0, -1.65), 0.2, darkPinkLight);
                Shape armLeft = new Sphere(new Point(-0.45, 0.1, -2), 0.2, pinkLight);
                Shape armtRight = new Sphere(new Point(0.45, 0.1, -2), 0.2, pinkLight);
                Shape footLeft = new Sphere(new Point(-0.4, -0.4, -2), 0.2, redLight);
                Shape footRight = new Sphere(new Point(0.4, -0.4, -2), 0.2, redLight);

                // Scene 2

                List<Shape> shapes = new ArrayList<>();
                List<Shape> shapes2 = new ArrayList<>();

                Collections.addAll(shapes, background, ground, planet1, cylinder1, cylinder2,
                                cylinder3, cylinder4, cylinder5,
                                cylinder6,
                                cylinder7, cylinder8, cylinder9, cylinder10, cylinder11, cylinder12, cylinder13,
                                cylinder14, cylinder15,
                                head,
                                footLeft, footRight, armLeft,
                                armtRight, eyeLeft, eyeRight, mouth, pupilLeft, pupilRight, globe1, globe2, globe3,
                                globe4, globe5,
                                globe6, globe7, globe8, globe9, globe10, globe12, globe13, globe14, globe15, globe16,
                                globe17, globe18,
                                globe19, globe20, globe21, globe22, globe23, globe24, globe25, globe26, globe27,
                                globe28);

                Collections.addAll(shapes2, background, ground, planet1, cylinder1, cylinder2,
                                cylinder3, cylinder4, cylinder5,
                                cylinder6,
                                cylinder7, cylinder8, cylinder9, cylinder10, cylinder11, cylinder12, cylinder13,
                                cylinder14, cylinder15,
                                head,
                                footLeft, footRight, armLeft,
                                armtRight, eyeLeft, eyeRight, mouth, pupilLeft, pupilRight, globe1, globe2, globe3,
                                globe4, globe5,
                                globe6, globe7, globe8, globe9, globe10, globe12, globe13, globe14, globe15, globe16,
                                globe17, globe18,
                                globe19, globe20, globe21, globe22, globe23, globe24, globe25, globe26, globe27,
                                globe28);

                Group group = new Group(shapes);
                Group group2 = new Group(shapes2);

                // This class instance defines the contents of the image.
                Raytracer content = new Raytracer(camera, group);
                Raytracer content2 = new Raytracer(camera2, group2);

                // Creates an image and iterates over all pixel positions inside the image
                Image image = new Image(width, height);
                Image image2 = new Image(width, height);

                image.sample(content, 10);
                image2.sample(content2, 10);

                final String filename = "doc/a07-1.png";
                final String filename2 = "doc/a07-2.png";

                image.write(filename);
                image2.write(filename2);

                System.out.println("Wrote image: " + filename);
                System.out.println("Wrote image: " + filename2);

        }

}
