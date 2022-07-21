package cgg.a12;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class PointLight implements Light {
    Point sourcePoint; 
    Color lightColor; 
    double radius;

    public PointLight(Point sourcePoint, Color lightColor, double radius) {
        this.sourcePoint = sourcePoint;
        this.lightColor = lightColor;
        this.radius = radius;
    }

    @Override
    public Color incomingIntensity(Hit hit, Shape scene) {
        Direction pos = Vector.subtract(sourcePoint, hit.trefferpunktX());
        double t = Vector.length(pos);
        Ray lightRay = new Ray(hit.trefferpunktX(), Vector.normalize(pos), 0.00001, t);
        Hit lightHit = scene.intersect(lightRay);
        if (lightHit != null && lightHit.strahlparameterT() < Double.POSITIVE_INFINITY) {
            return Vector.black;
        }
        double nom = (t / radius) * (t / radius);
        pos = Vector.normalize(pos);
        double rt = Vector.dotProduct(hit.normalenvektor(), pos);
        double f = 1 / nom;
        if (t > radius) {
            f = Math.pow(f, 1 / f);
        }
        return Vector.multiply(f * rt, lightColor);
    }
}
