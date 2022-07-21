package cgg.a10;

import cgtools.*;

public record Camera(double cameraOpeningAngle, double width, double height, Direction rotation, Matrix translation) {

    public Ray generateRay(double x, double y) {
        Matrix xRotation = Matrix.rotation(new Direction(1, 0, 0), rotation.x());
        Matrix yRotation = Matrix.rotation(new Direction(0, 1, 0), rotation.y());
        Matrix zRotation = Matrix.rotation(new Direction(0, 0, 1), rotation.z());

        double xVec = x - width / 2;
        double yVec = -(y - height / 2);
        double zVec = -((width / 2) / Math.tan(cameraOpeningAngle / 2));
        Direction temp = Vector.normalize(new Direction(xVec, yVec, zVec));

        Direction direction = Matrix.multiply(xRotation, temp);
        direction = Matrix.multiply(yRotation, direction);
        direction = Matrix.multiply(zRotation, direction);

        Point x0 = Matrix.multiply(translation, new Point(0, 0, 0));

        Ray ray = new Ray(x0, direction, 0, Double.POSITIVE_INFINITY);
        return ray;

    }
}
