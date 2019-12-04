package cgg.Shapes;

import cgg.Hit;
import cgg.Material;
import cgg.Ray;
import cgg.Shape;
import cgtools.*;
import static cgtools.Vector.*;
import static java.lang.Math.sqrt;


public class Cylinder implements Shape {

    private Point origin;
    private double radius;
    private double height;
    private Material material;
    double t;

    public Cylinder(Point origin, double radius, double height, Material material) {
        this.origin = origin;
        this.radius = radius;
        this.height = height;
        this.material = material;
    }

    @Override
    public Hit intersectWith(Ray r) {

        Point originR2D = point(r.originPoint.x, 0, r.originPoint.z);
        Direction dirR2D = direction(r.direction.x, 0, r.direction.z);
        Point origin2D = point(origin.x, 0, origin.z);

        Direction shift = subtract(originR2D, origin2D);
        double a = dotProduct(dirR2D, dirR2D);
        double b = 2 * dotProduct(shift, dirR2D);
        double c = dotProduct(shift, shift) - Math.pow(radius, 2);


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

            if (r.tmin < t && r.tmax > t && r.direction.y < height) {


                //Schnittpuntk mit dem Strahl (x0 + t*d)
                Point hitPoint= add(r.originPoint, multiply(t,r.direction));

                //Normalenvektor Kugel (x-c)/r
                Direction normal = Direction.divide(Direction.subtract(hitPoint, origin), radius);


                Hit hit = new Hit(hitPoint, normal, material, t);
                return hit;

            }
            return null;
        }
    }

}