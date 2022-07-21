package cgg.a12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cgg.Image;
import cgtools.*;

public class Main {
        public static void main(String[] args) {
                final int width = 1920;
                final int height = 1080;
                final int samplesPerPixel = 10;

                Point zero = new Point(0, 0, 0);
                Direction xAxis = new Direction(1, 0, 0);
                Direction yAxis = new Direction(0, 1, 0);
                Direction zAxis = new Direction(0, 0, 1);

                var animators = new ArrayList<Animator>();

                // Shapes--------------------------------------------------------------------------------------------------------------------
                // List<Shape> shapes = new ArrayList<>();

                DiffusedMaterial pink = new DiffusedMaterial(Vector.color(1, 0.6, 0.8));
                
                List<Shape> sphereslist = new ArrayList<>();
                // Sky
                // Shape sky = new Background(new BackgroundMaterial(Vector.gray));
                // Sky
                Matrix skyMatrix = Matrix.multiply(Matrix.scaling(2, 2, 0), Matrix.translation(0.415, 0.5, 0));
                TextureTransform skyTransform = new TextureTransform("doc/textures/stars.jpg", skyMatrix);
                Shape sky = new Background(new BackgroundMaterial(skyTransform));

                // Ground
                Point gPoint = new Point(0.0, -0.5, 0.0);
                Direction gDirection = new Direction(0, 1, 0);
                DiffusedMaterial gMaterial = new DiffusedMaterial(Vector.gray);
                PolishedMetalMaterial groundMirror = new PolishedMetalMaterial(0.05, Vector.color(0.5, 0.5, 1));
                Plane ground = new Plane(gPoint, gDirection, groundMirror, 100, 100);
                //

                Shape head = new Sphere(new Point(0, 0, 0), 1.8, new DiffusedMaterial("doc/textures/kirby2.jpg"));
                Shape armLeft = new Sphere(new Point(-1.5, 0.2, 0), 0.7, pink);
                Shape armtRight = new Sphere(new Point(1.5, 0.2, 0), 0.7, pink);
                Shape footLeft = new Sphere(new Point(-1, -1.5, 0), 0.8, new DiffusedMaterial(Vector.red));
                Shape footRight = new Sphere(new Point(1, -1.5, 0), 0.8, new DiffusedMaterial(Vector.red));
                Shape[] headShapes = { head, armLeft, armtRight, footLeft, footRight };

                int kirbyAmount = 20;
                double Xmin = -15;
                double Xmax = 15;
                double Ymin = 0.5;
                double Ymax = 20;
                double Zmin = -15;
                double Zmax = 5;
                Shape[] kirbys = new Shape[kirbyAmount];
                for (int i = 0; i < kirbyAmount; i++) {
                        double randX = new Random().nextDouble(Xmax - Xmin + 1) + Xmin;
                        double randY = new Random().nextDouble(Ymax - Ymin + 1) + Ymin;
                        double randZ = new Random().nextDouble(Zmax - Zmin + 1) + Zmin;
                        double randRadius = new Random().nextDouble(2 - 1) + 1;
                        double randomAngle = new Random().nextDouble(360 - 0 + 1) + 0;
                        Matrix translate = Matrix.translation(randX, randY, randZ);
                        Matrix rotation = Matrix.rotation(randX, randY, randZ, randomAngle);
                        Group k = new Group(new Transform(Matrix.multiply(translate, rotation)), headShapes);
                        kirbys[i] = k;
                }
                Group kirbyGroup = new Group(kirbys);

                int lightSphereAmount = 20;
                Shape[] lightSpheres = new Shape[lightSphereAmount];
                for (int i = 0; i < lightSphereAmount; i++) {
                        double randX = new Random().nextDouble(Xmax - Xmin + 1) + Xmin;
                        double randY = new Random().nextDouble(Ymax - Ymin + 1) + Ymin;
                        double randZ = new Random().nextDouble(Zmax - Zmin + 1) + Zmin;
                        double randRadius = new Random().nextDouble(1.1 - 0.8) + 0.8;
                        double randR = new Random().nextDouble(1 - 0.2) + 0.2;
                        double randB = new Random().nextDouble(1 - 0.2) + 0.2;
                        Shape lightSphere = new Sphere(new Point(randX, randY, randZ), randRadius,
                                        new BackgroundMaterial(new Color(randR, 0, randB)));
                        lightSpheres[i] = lightSphere;
                }
                Group lightSphereGroup = new Group(lightSpheres);


                Shape[] sceneShapes = { sky, ground, kirbyGroup, lightSphereGroup };
                Group scene = new Group(sceneShapes);
                // ----------------------------------------------------------------------------------------------------------------------------

                // Lights--------------------------------------------------------------------------------------------------------------------
                PointLight plight1 = new PointLight(new Point(0, 25, -5), Vector.red, 400);
                DirectionalLight dlight = new DirectionalLight(Vector.red, new Direction(-3, -0.3, -4), 0.5);
                DirectionalLight dlight2 = new DirectionalLight(Vector.blue, new Direction(3, -0.3, 4), 0.5);
                Light[] lights = {  };
                // ----------------------------------------------------------------------------------------------------------------------------

                // Settings-------------------------------------------------------------------------------------------------------------------- 
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
                // ----------------------------------------------------------------------------------------------------------------------------
                Camera camera = new Camera(Math.PI / 3, width, height, new Direction(5, 0, 0),
                                Matrix.translation(0, 5, 40));

                // animators.add(new Move(lightSphereGroup, zero, zAxis, -10));
                animators.add(new RotorLive(kirbyGroup, yAxis, 0, 45));
                animators.add(new RotorLive(lightSphereGroup, yAxis, 0, -45));

                var world = new World(scene, animators, lights);

                // This class instance defines the contents of the image.
                Raytracer raytracer = new Raytracer(camera, world);

                // Creates an image and iterates over all pixel positions inside the image
                Image image = new Image(width, height);

                try {
                        image.parallelization(raytracer, samplesPerPixel);
                } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                }

                // Animation
                Animation.render(0, 8, 30, world, camera, image, samplesPerPixel, "doc/a12-animation");
                final String filename = "doc/a12-1.png";

                image.write(filename);

                System.out.println("Wrote image: " + filename);
        }
}