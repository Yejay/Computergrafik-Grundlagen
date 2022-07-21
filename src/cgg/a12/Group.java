package cgg.a12;

import java.util.List;

import cgtools.Matrix;

public class Group implements Shape, Animatable {
    final Shape[] shapes;
    Transform transform;

    public Group(Transform transform, Shape... shapes) {
        this.shapes = shapes;
        this.transform = transform;
    }

    public Group(Transform transform, List<? extends Shape> list) {
        this.shapes = list.toArray(new Shape[0]);
        this.transform = transform;
    }

    public Group(Shape... shapes) {
        this.shapes = shapes;
        this.transform = new Transform(Matrix.identity());
    }

    @Override
    public Hit intersect(Ray ray) {
        Hit hit = null;
        ray = transform.transform(ray);
        for (Shape shape : shapes) {
            Hit tempHit = shape.intersect(ray);
            if (tempHit == null) {
                continue;
            } else {
                if (hit == null) {
                    hit = tempHit;
                } else {
                    if (tempHit.strahlparameterT() < hit.strahlparameterT()) {
                        hit = tempHit;
                    }
                }
            }
        }
        if (hit != null) {
            hit = transform.transform(hit);
        }
        return hit;
    }

    @Override
    public BoundingBox bounds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTransformation(Matrix m) {
        transform = new Transform(m);
        // bounds = calculateBounds();        
    }

}
