package cgg.a05;

import cgtools.Color;

public record BackgroundMaterial(Color backgroundColor) implements Material {

    @Override
    public Color emission(Ray r, Hit h) {
        // TODO Auto-generated method stub
        return backgroundColor;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
