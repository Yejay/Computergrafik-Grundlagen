package cgg.a12;

public interface Shape {
    public Hit intersect(Ray ray);
    public BoundingBox bounds();
}
