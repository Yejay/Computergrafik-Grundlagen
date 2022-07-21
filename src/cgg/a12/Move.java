package cgg.a12;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public record Move(Animatable anim, Point start, Direction dir, double speed) implements Animator {

    @Override
    public void update(double time) {
        Point t = Vector.add(start, Vector.multiply(time * speed, dir));
        Matrix m = Matrix.translation(t);
        anim.setTransformation(m);
    }

}
