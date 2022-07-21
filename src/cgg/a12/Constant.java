package cgg.a12;

import cgtools.Color;
import cgtools.Sampler;

public class Constant implements Sampler {
    Color color;

    public Constant(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor(double x, double y) {
        return color;
    }

}
