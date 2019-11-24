package cgg;

import cgtools.Direction;
import cgtools.*;

import static cgtools.Vector.point;



public class Plane implements Shape {
    Point planeAncerPoint;
    Direction planeNormalVector;
    Material material;
    double t;

    public Plane(Point planeCenterPoint, Direction planeNormalVector, Material material) {
        this.planeAncerPoint = planeCenterPoint;
        this.planeNormalVector = planeNormalVector;
        this.material = material;

    }


    @Override
    public Hit intersectWith(Ray r) {

        double numerator = (Direction.dotProduct(planeAncerPoint, planeNormalVector)) - Direction.dotProduct(planeNormalVector, r.originPoint);
        double denominator = Direction.dotProduct(r.direction, planeNormalVector);
        t = numerator/denominator;

        if (r.tmin < t && r.tmax > t) {
            Direction positionHitPoint = Point.multiply(t, r.direction);
            Point point = point(positionHitPoint.x,positionHitPoint.y,positionHitPoint.z);
            Hit hit = new Hit(point, planeNormalVector, material, t);
            return hit;
        }else{
            return null;
        }

    }
}
