package cgg;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

class Ray{
    Point urpsrung;
    Direction direction;
    int tMin;
    int tMax;

    public Ray(Point ursprung, Direction direction, int tMin, int tMax){
        this.urpsrung = ursprung;
        this.direction = direction;
        this.tMin = tMin;
        this.tMax = tMax;
    }


    public Vector pointAt(double t){

        Vector strahlpunkt = Vector.add(urpsrung, Vector.multiply(t,direction));

        return strahlpunkt;
    }
}
