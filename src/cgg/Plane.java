package cgg;

import cgtools.Direction;
import cgtools.*;

import static cgtools.Vector.point;



public class Plane implements Shape {
    Point planeCenterPoint;
    Direction planeNormalVector;
    Material material;
    double t;

    public Plane(Point planeCenterPoint, Direction planeNormalVector, Material material) {
        this.planeCenterPoint = planeCenterPoint;
        this.planeNormalVector = planeNormalVector;
        this.material = material;

    }


    @Override
    public Hit intersectWith(Ray r) {

        Direction oc = Point.subtract(r.originPoint, planeCenterPoint); // x0

        // t = (p*n - n*x0)/d*n
        // t = Schnittpunkt, p = Ankerpunkt , n = Normalenvektor, d = rayDirection,

        //Vec3 numerator = Vec3.subtract(Vec3.dotProduct(planeCenterPoint, planeNormalVector),Vec3.dotProduct(planeNormalVector,oc)); //nenner
        double numerator = (Direction.dotProduct(planeCenterPoint, planeNormalVector)) - Direction.dotProduct(planeNormalVector, r.originPoint);
        double denominator = Direction.dotProduct(r.direction, planeNormalVector); //zähler

        if (denominator == 0) {
            return null;
        }
        else {
            t = numerator/denominator;

            if (r.tmin < t && r.tmax > t) {
                Direction positionHitPoint = Point.multiply(t, r.direction);
                Point point = point(positionHitPoint.x,positionHitPoint.y,positionHitPoint.z);
                Hit hit = new Hit(point, material, planeNormalVector, t);
                return hit;

            }
        }



        return null;
    }
}
