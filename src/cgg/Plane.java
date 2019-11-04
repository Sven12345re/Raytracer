package cgg;

import cgtools.Direction;
import cgtools.*;

import static cgtools.Vector.point;

public class Plane implements Shape {
    Point planeCenterPoint;
    Direction planeNormalVector;
    Color planeColor;
    double t;


    public Plane(Point planeCenterPoint, Direction planeNormalVector, Color planeColor) {
        this.planeCenterPoint = planeCenterPoint;
        this.planeNormalVector = planeNormalVector;
        this.planeColor = planeColor;

    }


    @Override
    public Hit intersectWith(Ray ray) {

        @SuppressWarnings({ "unused", "static-access" })
        Vector oc = Vector.subtract(ray.urpsrung, planeCenterPoint); // x0

        // t = (p*n - n*x0)/d*n
        // t = Schnittpunkt, p = Ankerpunkt , n = Normalenvektor, d = rayDirection,

        //Vec3 numerator = Vec3.subtract(Vec3.dotProduct(planeCenterPoint, planeNormalVector),Vec3.dotProduct(planeNormalVector,oc)); //nenner
        @SuppressWarnings("static-access")
        double numerator = (Vector.dotProduct(planeCenterPoint, planeNormalVector)) - Vector.dotProduct(planeNormalVector, ray.urpsrung);
        @SuppressWarnings("static-access")
        double denominator = Vector.dotProduct(ray.direction, planeNormalVector); //zähler

        if (denominator == 0) {
            return null;
        }
        else {
            t = numerator/denominator;

            if (ray.tMin < t && ray.tMax > t) {
                @SuppressWarnings("static-access")

                //Vec3 normalHit = Vec3.divide(subtract(positionHitPoint, planeCenterPoint), sphereRadius);
                // double t = dotProduct(positionHitPoint, planeNormalVector) / denominator;

                Vector test = Vector.multiply(ray.direction, Double.POSITIVE_INFINITY);

                double pointX = test.x;
                double pointY = test.y;
                double pointZ = test.z;
                Point positionHitPoint = point(pointX,pointY,pointZ);

                Hit hit = new Hit(t,positionHitPoint,planeNormalVector,planeColor);
                return hit;

            }
        }



        return null;
    }
}
