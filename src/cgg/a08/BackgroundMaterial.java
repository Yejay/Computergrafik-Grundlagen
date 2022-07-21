package cgg.a08;

import cgtools.Color;

public record BackgroundMaterial(Color backgroundColor) implements Material {

    @Override
    public Color emission(Ray r, Hit h) {
        return backgroundColor;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return null;
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        return null;
    }

}
