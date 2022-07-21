package cgg.a11;

public interface Shape {
    public Hit intersect(Ray ray);
    public BoundingBox bounds();
}
