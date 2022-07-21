package cgg.a12;

import cgtools.Direction;
import cgtools.Matrix;

public record RotorLive(Animatable anim, Direction axis, double start, double speed) implements Animator{

    @Override
    public void update(double time) {
        double angle = start + time * speed;
        Matrix m = Matrix.rotation(axis, angle);
        anim.setTransformation(m);
    }
}
