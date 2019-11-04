package cgg;

import cgtools.*;

import static cgtools.Vector.point;

class Sphere implements Shape{
    Point mittelpunkt;
    double radius;
    Color color;

    public Sphere(Point mittelpunkt, double radius, Color color){
        this.mittelpunkt = mittelpunkt;
        this.radius = radius;
        this.color = color;

    }

    public Hit intersectWith(Ray ray) {
        Vector oc = Vector.subtract(ray.urpsrung,mittelpunkt);
        double a = Vector.dotProduct(ray.direction, ray.direction);
        double b = 2 * Vector.dotProduct(ray.direction, oc);
        double c = Vector.dotProduct(oc,oc)-radius*radius;
        double discriminant = b * b - 4 * a * c;

        if(a == 0 || discriminant < 0){

            return null;
        }else{
            double t1 = (-b - Math.sqrt(discriminant)) /2;
            double t2 = (-b + Math.sqrt(discriminant)) /2;
            double t = (t1 < t2) ? t1 : t2;

            if(ray.tMin < t && ray.tMax > t){

                Vector test = Vector.multiply(t, ray.direction);

                double pointX = test.x;
                double pointY = test.y;
                double pointZ = test.z;

                Point x = point(pointX,pointY,pointZ);


                Vector normal = Vector.divide(Vector.subtract(x,mittelpunkt),radius);
                Hit hit = new Hit(t,x,normal,color);
                return hit;
            }

            return null;
        }


    }
}