package cgg;

import cgtools.Direction;

import static cgtools.Vector.direction;

class Camera{
    double width;
    double heigth;
    double cameraAngle;
    Direction generateRay;

    public Camera(double width, double height, double cameraAngle){
        this.width = width;
        this.heigth = height;
        this.cameraAngle = cameraAngle;
    }

    public Direction generateRay(double xp, double yp) {

        double x = (xp - (width /2));
        double y = ((heigth / 2) - yp);
        double z = (-((width / 2) / Math.tan(cameraAngle / 2)));

        generateRay = direction(x, y, z);

        return Direction.normalize(generateRay);

    }

}
