package cgg.a09;

import cgtools.Color;

public record BackgroundMaterial(Color backgroundColor) implements Material {

    @Override
    public Color emission(Ray ray, Hit hit) {
        return backgroundColor;
    }

    @Override
    public Color albedo(Ray ray, Hit hit) {
        return null;
    }

    @Override
    public Ray scattered(Ray ray, Hit hit) {
        return null;
    }

}
