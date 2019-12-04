package cgg;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Matrix;
import cgtools.Vector;

import static cgtools.Vector.*;

class Camera{
    Matrix transformation;
    double width;
    double heigth;
    double cameraAngle;
    Direction generateRay;

    public Camera(Matrix transformation, double width, double height, double cameraAngle){
        this.transformation = transformation;
        this.width = width;
        this.heigth = height;
        this.cameraAngle = cameraAngle;
    }

    public Ray generateRay(double xp, double yp) {

        double x = (xp - (width /2));
        double y = ((heigth / 2) - yp);
        double z = (-((width / 2) / Math.tan(cameraAngle / 2)));



        Direction transformDirection = Matrix.multiply(transformation, normalize(direction(x, y, z)));

        return new Ray(Matrix.multiply(transformation, point(0,0,0)),transformDirection,0,Double.POSITIVE_INFINITY);

    }

}
