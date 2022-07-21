package cgg.a11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cgg.Image;
import cgtools.*;

public class Main {
        public static void main(String[] args) {
                final int width = 3840;
                final int height = 2160;

                List<Shape> sceneShapes = new ArrayList<>();
                List<Light> lightslist = new ArrayList<>();

                // Sky
                Shape sky = new Background(new BackgroundMaterial(Vector.gray));

                // Ground
                Point wpPoint = new Point(0.0, -0.5, 0.0);
                Direction wpDirection = new Direction(0, 1, 0);
                DiffusedMaterial wpMaterial = new DiffusedMaterial(Vector.gray);
                Plane waterPlane = new Plane(wpPoint, wpDirection, wpMaterial, 200, 200);
                //

        

                Shape sphere1 = new Sphere(new Point(-5, 1, 0), 2, new GlassMaterial(1, "doc/textures/kirby2.jpg"));

                PointLight plight1 = new PointLight(new Point(-5, 5, -5), Vector.red, 5);

                Shape sphere2 = new Sphere(new Point(5, 1, 0), 2, new GlassMaterial(1, "doc/textures/kirby2.jpg"));

                PointLight plight2 = new PointLight(new Point(5, 5, -5), Vector.blue, 5);

                Shape sphere3 = new Sphere(new Point(0, 3, 0), 2, new GlassMaterial(1, "doc/textures/kirby2.jpg"));

                PointLight plight3 = new PointLight(new Point(0, 5, -10), Vector.green, 5);
               
                Collections.addAll(sceneShapes, sky, waterPlane, sphere1, sphere2, sphere3);

                /* ------------------------------------------------------------------------------------------------------------------------------------------ */
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
                Camera camera = new Camera(Math.PI / 3, width, height, new Direction(5, 0, 0),
                                Matrix.translation(0, 5, 30));
                Camera camera2 = new Camera(Math.PI / 3, width, height, new Direction(-45, 0, 0),
                                Matrix.translation(0, 30, 25));

                Group sceneOneGroup = new Group(sceneShapes, new Transform(Matrix.translation(0, 0, 0)));

                //
                
                DirectionalLight dlight = new DirectionalLight(Vector.red, new Direction(-3, -0.3, -4), 0.5);
                DirectionalLight dlight2 = new DirectionalLight(Vector.white, new Direction(3, -0.3, 4), 0.5);
                lightslist.add(plight1);
                lightslist.add(plight2);
                lightslist.add(plight3);
                lightslist.add(dlight);
                lightslist.add(dlight2);
                World world = new World(lightslist, sceneOneGroup);
                //


                // This class instance defines the contents of the image.
                Raytracer content = new Raytracer(camera, world);
                Raytracer content2 = new Raytracer(camera2, world);

                // Creates an image and iterates over all pixel positions inside the image
                Image image = new Image(width, height);
                Image image2 = new Image(width, height);

                try {
                        image.parallelization(content, 10);
                        image2.parallelization(content2, 10);
                } catch (InterruptedException | ExecutionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                final String filename = "doc/a11-1.png";
                final String filename2 = "doc/a11-2.png";

                image.write(filename);
                image2.write(filename2);

                System.out.println("Wrote image: " + filename);
                System.out.println("Wrote image: " + filename2);
        }
}
