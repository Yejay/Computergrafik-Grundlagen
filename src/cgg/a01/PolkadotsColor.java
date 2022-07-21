package cgg.a01;

import cgtools.*;
// circleAmount never used I think
public record PolkadotsColor(Color circle, int width, int height, int radius) implements Sampler {
    // (x - center_x)² + (y - center_y)² < radius²
    public Color getColor(double x, double y) {
        double kasten = (width + radius) / 7;
        double x_strich = x % kasten;
        double y_strich = y % kasten;
        double cx = kasten / 2;
        double dx = x_strich - cx;
        double dy = y_strich - cx;

        // >= inside circle, < outside circle
        // hsv von x abhängig machen um die Farbe zu ändern
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        if (radius >= distance) {
            // hsv (color, saturation, intensity)
            return Vector.hsvToRgb(new Color(0.1 + x / width, 0.3, 1));
        }

        return new Color(0, 0, 0);
    }
}
