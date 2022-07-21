package cgg.a08;

import cgtools.Matrix;

public class Transform {
    Matrix toWorld;
    Matrix fromWorld;
    Matrix toWorldN;

    Transform(Matrix toWorld) {
        this.toWorld = toWorld;
        this.fromWorld = Matrix.invert(toWorld);
        this.toWorldN = Matrix.transpose(toWorld);
    }

    Ray transform(Ray r) {
        return new Ray(Matrix.multiply(fromWorld, r.x0()), Matrix.multiply(fromWorld, r.d()), r.tmin(), r.tmax());
    }

    Hit transform(Hit h) {
        return new Hit(h.strahlparameterT(), Matrix.multiply(toWorld, h.trefferpunktX()), Matrix.multiply(toWorld, h.normalenvektor()), h.material());
    }

}
