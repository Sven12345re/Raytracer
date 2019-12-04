package cgg;

import cgtools.Direction;
import cgtools.Point;

public class Ray {

    //Attribute
    public Point originPoint; //Ursprung des Strahls
    public Direction direction;   //Richtung des Strahls
    public double tmin;      //Minimale Länge
    public double tmax;      //Maximale Länge

    //Konstruktor
    public Ray(Point originPoint, Direction direction, double tmin, double tmax) {
        this.originPoint = originPoint;
        this.direction = direction;
        this.tmin = tmin;
        this.tmax = tmax;
    }


    public Point pointAt(double t) {

        Point pointAt;
        pointAt = Point.add(originPoint, (Point.multiply(t, direction)));
        return pointAt;
    }

    public boolean contains(double t) {
        return tmin < t && t <= tmax;
    }




}
