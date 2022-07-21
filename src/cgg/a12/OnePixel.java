package cgg.a12;

import java.util.concurrent.Callable;

import cgtools.Color;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

public record OnePixel(Sampler sample, int x, int y, int n) implements Callable<Color> {
    public Color call() {
        Color color = new Color(0, 0, 0);
        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                double rx = Random.random();
                double ry = Random.random();
                double xs = x + (xi + rx) / n;
                double ys = y + (yi + ry) / n;
                color = Vector.add(color, sample.getColor(xs, ys));
            }
        }
        color = Vector.divide(color, n * n);
        return color;
    }
}
