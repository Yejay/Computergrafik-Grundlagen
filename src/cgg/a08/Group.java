package cgg.a08;

import java.util.List;

public class Group implements Shape {
    List<Shape> shapes;
    Transform transform;

    public Group(List<Shape> shapes, Transform transform) {
        this.shapes = shapes;
        this.transform = transform;
    }

    @Override
    public Hit intersect(Ray r) {
        Hit hit = null;
        r = transform.transform(r);
        for (Shape shape : shapes) {
            Hit tempHit = shape.intersect(r);
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

}
