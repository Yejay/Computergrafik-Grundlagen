package cgg.a10;

public interface Shape {
    public Hit intersect(Ray ray);
    public BoundingBox bounds();
}
