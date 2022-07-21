package cgg.a09;

public interface Shape {
    public Hit intersect(Ray ray);
    public BoundingBox bounds();
}
