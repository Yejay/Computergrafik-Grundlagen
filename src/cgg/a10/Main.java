package cgg.a10;

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

                // Sky
                Matrix skyMatrix = Matrix.multiply(Matrix.scaling(2, 2, 0), Matrix.translation(0.415, 0.5, 0));
                TextureTransform skyTransform = new TextureTransform("doc/textures/stars.jpg", skyMatrix);
                Shape sky = new Background(new BackgroundMaterial(skyTransform));

                // Ground
                Point wpPoint = new Point(0.0, -0.5, 0.0);
                Direction wpDirection = new Direction(0, 1, 0);
                BackgroundMaterial wpMaterial = new BackgroundMaterial("doc/textures/water3.jpg");
                Plane waterPlane = new Plane(wpPoint, wpDirection, wpMaterial, 200, 200);
                //

                // Standing plane with picture
                Point pwpPoint = new Point(0.0, -0.5, 0.0);
                Direction pwpDirection = new Direction(0, 1, 0);
                Matrix pwpMatrix = Matrix.scaling(1, 1, 0);
                TextureTransform pwpTransform = new TextureTransform("doc/textures/japan.jpg", pwpMatrix);
                BackgroundMaterial pwpMaterial = new BackgroundMaterial(pwpTransform);
                Plane planeWithPicture = new Plane(pwpPoint, pwpDirection, pwpMaterial, 30, 30, 5);
                Matrix planeTransform = Matrix.multiply(Matrix.translation(new Direction(0, 6, -6)),
                                Matrix.rotation(new Direction(1, 0, 0), 90));
                Group plane = new Group(planeWithPicture, new Transform(planeTransform));
                //

                // Left light
                Direction llDirection = new Direction(-10, 7, -10);
                Matrix llMatrix = Matrix.scaling(1, 1, 0);
                TextureTransform llTransform = new TextureTransform("doc/textures/lights.jpg", llMatrix);
                BackgroundMaterial llMaterial = new BackgroundMaterial(llTransform);
                Shape lightLeft = new Cylinder(llDirection, 2, 2, llMaterial);
                //

                // Right light
                Direction lrDirection = new Direction(10, 3, -10);
                Matrix lrMatrix = Matrix.scaling(1, 1, 0);
                TextureTransform lrTransform = new TextureTransform("doc/textures/lights.jpg", lrMatrix);
                BackgroundMaterial lrMaterial = new BackgroundMaterial(lrTransform);
                Shape lightRight = new Cylinder(lrDirection, 2, 2, lrMaterial);
                //

                // Polkadots cylinder left
                Direction polkaLeftDir = new Direction(-10, 3, -10);
                Matrix polkaLeftMatrix = Matrix.scaling(1, 1, 0);
                PolkadotsV2 polkaLeft = new PolkadotsV2(Vector.red, Vector.black, 0.03);
                TextureTransform polkaLeftTransform = new TextureTransform(polkaLeft, polkaLeftMatrix);
                DiffusedMaterial polkaLeftMaterial = new DiffusedMaterial(polkaLeftTransform);
                Shape polkaLeftCyl = new Cylinder(polkaLeftDir, 2, 2, polkaLeftMaterial);
                //

                // Checkerboard cylinder right
                Direction checkRightDir = new Direction(10, 7, -10);
                Matrix checkRightMatrix = Matrix.scaling(1, 1, 0);
                CheckerBoard checkRight = new CheckerBoard(Vector.red, Vector.black, 10);
                TextureTransform checkRightTransform = new TextureTransform(checkRight, checkRightMatrix);
                DiffusedMaterial checkRightMaterial = new DiffusedMaterial(checkRightTransform);
                Shape checkRightCyl = new Cylinder(checkRightDir, 2, 2, checkRightMaterial);
                //

                // Kirby cylinder
                Direction kirbyCylDir = new Direction(0, 1, 0);
                DiffusedMaterial kirbyCylMaterial = new DiffusedMaterial("doc/textures/kirby2.jpg");
                Shape kirbyCyl = new Cylinder(kirbyCylDir, 2, 2, kirbyCylMaterial);
                //

                // Pokelball
                Point pokeballPoint = new Point(5, 0.7, 1);
                DiffusedMaterial pokeballMaterial = new DiffusedMaterial("doc/textures/pokeball.jpg");
                Shape pokeball = new Sphere(pokeballPoint, 2, pokeballMaterial);
                //

                //
                Point kirbyPoint = new Point(-5, 0.7, 1);
                DiffusedMaterial kirbyMaterial = new DiffusedMaterial("doc/textures/kirby2.jpg");
                Shape kirby = new Sphere(kirbyPoint, 2, kirbyMaterial);
                //
                //
                Point mirrorKirbyPoint = new Point(-10, 0.5, 2);
                PolishedMetalMaterial mirrorKirbyMaterial = new PolishedMetalMaterial(0, "doc/textures/kirby2.jpg");
                Shape mirrorKirby = new Sphere(mirrorKirbyPoint, 2, mirrorKirbyMaterial);
                //
                //
                Point glassKirbyPoint = new Point(10, 0.5, 2);
                GlassMaterial glassKirbyMaterial = new GlassMaterial(1.5, "doc/textures/kirby2.jpg");
                Shape glassKirby = new Sphere(glassKirbyPoint, 2, glassKirbyMaterial);
                //

                Collections.addAll(sceneShapes, sky, waterPlane, plane, lightLeft, lightRight, polkaLeftCyl,
                                checkRightCyl, kirbyCyl, pokeball, kirby, mirrorKirby, glassKirby);

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

                // This class instance defines the contents of the image.
                Raytracer content = new Raytracer(camera, sceneOneGroup);
                Raytracer content2 = new Raytracer(camera2, sceneOneGroup);

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

                final String filename = "doc/a10-1.png";
                final String filename2 = "doc/a10-2.png";

                image.write(filename);
                image2.write(filename2);

                System.out.println("Wrote image: " + filename);
                System.out.println("Wrote image: " + filename2);
        }
}
