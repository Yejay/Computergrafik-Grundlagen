package cgg.a12;

import cgtools.*;

public class DirectionalLight implements Light {
    private Color color;
    private Direction lightDirection;
    double intensity;

    public DirectionalLight(Color color, Direction lightDirection, double intensity) {
        this.color = color;
        this.lightDirection = lightDirection;
        this.intensity = intensity;
    }

    public Color incomingIntensity(Hit hit, Shape scene) {
        Ray lightRay = new Ray(hit.trefferpunktX(), Vector.negate(lightDirection), 0.00001, Double.POSITIVE_INFINITY);
        Hit lightHit = scene.intersect(lightRay);
        if (lightHit != null && lightHit.strahlparameterT() < Double.POSITIVE_INFINITY) {
            return Vector.black;
        }
        Color ambient = Vector.multiply(0.1 * intensity, color);
        Color diffuse = Vector.multiply(
                0.9 * intensity * Vector.dotProduct(Vector.negate(lightDirection), hit.normalenvektor()), color);
        return Vector.add(ambient, diffuse);
    }
}
