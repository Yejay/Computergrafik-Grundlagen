package cgg.a02;

import java.util.ArrayList;

import cgtools.*;

public class ColoredDiscs implements Sampler {
    ArrayList<Disc> discs;
    int width;
    int height;
    int circleAmount;
    int radiusRange;

    public ColoredDiscs(int width, int height, int circleAmount, int radiusRange) {
        this.width = width;
        this.height = height;
        this.circleAmount = circleAmount;
        this.radiusRange = radiusRange;
        this.discs = generateDiscs();
    }

    private ArrayList<Disc> generateDiscs() {
        ArrayList<Disc> discs = new ArrayList<>();
        for (int i = 0; i < circleAmount; i++) {
            double randomX = Random.random() * width;
            double randomY = Random.random() * height;
            double randomRadius = Random.random() * radiusRange;
            Color randomColor = new Color(Math.random(), 0, 0);
            discs.add(new Disc(randomX, randomY, randomRadius, randomColor));
        }
        discs.sort((discA, discB) -> (int)(discA.radius) - (int)(discB.radius));
        return discs;
    }

    @Override
    public Color getColor(double x, double y) {
        for (Disc disc : discs) {
            if (disc.isPointInDisc(x, y)) {
                return disc.color;
            }
        }
        return new Color(0, 0, 0);
    }
}
