package cgg.a02;

import cgg.*;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;
        final int circleAmount = 100;
        final int radiusRange = 50;

        // This class instance defines the contents of the image.
        ColoredDiscs content = new ColoredDiscs(width, height, circleAmount, radiusRange);

        // Creates an image and iterates over all pixel positions inside the image
        Image image = new Image(width, height);
        Image image2 = new Image(width, height);

        image.sample(content);
        // 10 because n * n, 10 * 10 = 100
        image2.sample(content, 10);

        final String filename = "doc/a02-discs.png";
        final String filename2 = "doc/a02-discs-supersampling.png";
        image.write(filename);
        image2.write(filename2);
        System.out.println("Wrote image: " + filename);
        System.out.println("Wrote image: " + filename2);
    }
}