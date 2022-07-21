package cgg.a10;

import java.util.ArrayList;
import java.util.List;

public class Group implements Shape {
    List<Shape> shapes;
    Transform transform;

    public Group(List<Shape> shapes, Transform transform) {
        this.shapes = shapes;
        this.transform = transform;
    }

    public Group(Shape shape, Transform transform){
        this.shapes = new ArrayList<>();
        shapes.add(shape);
        this.transform = transform;
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

}
