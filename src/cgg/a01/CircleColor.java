package cgg.a01;

import cgtools.*;

public record CircleColor(Color circle, int width, int height, int radius) implements Sampler {

    // (x - center_x)² + (y - center_y)² < radius²
    public Color getColor(double x, double y) {
        double center_x = width / 2;
        double center_y = height / 2;
        double dx = x - center_x;
        double dy = y - center_y;
        
        // >= inside circle, < outside circle
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        if (radius >= distance) {
            return circle;
        }

        return new Color(0, 0, 0);
    }
}
