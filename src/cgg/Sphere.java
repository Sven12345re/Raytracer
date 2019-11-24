package cgg;

import cgtools.*;


import static cgtools.Vector.*;
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

        //Mittelpunkt = c
        //Radius = r
        //Ursprung = x0
        //Richtung = d

        //x0-c Verschiebung der Kugel in den Nullpunkt
        Direction oc = Direction.subtract(r.originPoint, sphereCenterPoint);

        //Quadratische Gleichung
        double a = Direction.dotProduct(r.direction, r.direction);
        double b = 2 * Direction.dotProduct(oc, r.direction);
        double c = Direction.dotProduct(oc, oc) - sphereRadius * sphereRadius;

        //Wurzel von abc Formel berechnen
        double discriminant = b * b - 4 * a * c;

        //weil nicht durch 0 geteilt werden darf (a==0) und man keine Wurzel aus negativen Zahlen ziehen darf
        if (a == 0 || discriminant < 0) {
            return null;
        } else {
            double t1 = (-b - sqrt(discriminant)) / 2*a;
            double t2 = (-b + sqrt(discriminant)) / 2*a;

            //Es zählt das kleinere t
            if (t1 < t2) {
                t = t1;
            } else {
                t = t2;
            }

            if (r.tmin < t && r.tmax > t) {


                //Schnittpuntk mit dem Strahl (x0 + t*d)
                Point hitPoint= add(r.originPoint, multiply(t,r.direction));

                //Normalenvektor Kugel (x-c)/r
               Direction normal = Direction.divide(Direction.subtract(hitPoint, sphereCenterPoint), sphereRadius);


                Hit hit = new Hit(hitPoint, normal, material, t);
                return hit;

            }
            return null;
        }
    }
}
