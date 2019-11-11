package cgg;

import cgtools.*;
import static cgtools.Vector.point;
import static cgtools.Vector.direction;


import static java.lang.Math.sqrt;

public class Sphere implements Shape {

    double sphereRadius;
    Point sphereCenterPoint;
    Material material;
    double t;

    public Sphere(Point sphereCenterPoint, double sphereRadius, Material material) {
        this.sphereRadius = sphereRadius;
        this.sphereCenterPoint = sphereCenterPoint;
        this.material = material;
    }

    @Override
    public Hit intersectWith(Ray r) {

        Direction oc = Direction.subtract(r.originPoint, sphereCenterPoint); //x0
        Direction direction = r.direction; //d

        // Folie 72
        // a = d^2
        double a = Direction.dotProduct(r.direction, r.direction);

        // b = 2 * x0 * d
        double b = 2 * Direction.dotProduct(r.direction, oc);

        // c = x0^2-r^2
        double c = Direction.dotProduct(oc, oc) - sphereRadius * sphereRadius;

        //Wurzel von abc Formel berechnen
        double discriminant = b * b - 4 * a * c;

        //weil nicht durch 0 geteilt werden darf (a==0) und man keine Wurzel aus negativen Zahlen ziehen darf
        if (a == 0 || discriminant < 0) {
            return null;
        } else {
            double t1 = (-b - sqrt(discriminant)) / 2;
            double t2 = (-b + sqrt(discriminant)) / 2;


            if (t1 < t2) {
                t = t1;
            } else {
                t = t2;
            }

            //mit Schnittpunkt Hit erzeugen

            //Testen ob in t zwischen tmin und tmax liegt
            if (r.tmin < t && r.tmax > t) {


                Direction positionHitPoint = Point.multiply(t, r.direction);
                Direction direction2 = direction(positionHitPoint.x,
                                        positionHitPoint.y, positionHitPoint.z);
                Direction direction3 = direction(sphereCenterPoint.x, sphereCenterPoint.y, sphereCenterPoint.z);
                Direction normal = Direction.divide(Direction.subtract(direction2, direction3), sphereRadius); //divide(subtract(x, center), radius);

                Point point = point(positionHitPoint.x, positionHitPoint.y, positionHitPoint.z);
                Hit hit = new Hit(point, material, normal, t);
                return hit;

            }
            return null;
        }
    }
}
