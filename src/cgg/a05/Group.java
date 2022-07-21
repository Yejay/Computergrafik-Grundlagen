package cgg.a05;


import java.util.List;

public class Group implements Shape {
    List<Shape> shapes;

    public Group(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public Hit intersect(Ray r) {
        double tmax = Double.POSITIVE_INFINITY;
        Hit temp = shapes.get(0).intersect(r);
        // Hit temp = null;
        for (Shape shape : shapes) {
            if (shape.intersect(r) != null) {
                if (tmax >= shape.intersect(r).strahlparameterT()) {
                    tmax = shape.intersect(r).strahlparameterT();
                    temp = shape.intersect(r);
                }
            }
        }
        if (temp == null) {
            System.out.println("TESSSSST");
            return null;
        }
        return temp;
    }

}
