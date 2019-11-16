package cgg;


import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

import static cgtools.Random.random;
import static cgtools.Vector.*;
import static java.lang.Double.POSITIVE_INFINITY;

public class Glass implements Material {

    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {


        Ray rayNew = scatteredRay(hit, ray);

        ReflectionProperties properties = new ReflectionProperties(hit, rayNew, Color.gray, color(0,0,0));
        return properties;
    }

    public Ray scatteredRay(Hit hit, Ray ray) {
        Ray scattered = null;

        double n1 = 1;
        double n2 = 1.5;
        double n22 = 0;
        double n11 = 0;

        //if (n•d > 0):
        Direction normal = hit.normalVectorHitPoint;
        if (dotProduct(normal, ray.direction) > 0) {
            //n = -n
            normal = multiply(normal, -1);

            //swap(n1, n2)
            n22 = n1;
            n11 = n2;
            n2 = n22;
            n1 = n11;
        }
        //if refract(d, n, n1, n2):
        if (refract(hit, ray, n1, n2) != null) {
            if (Random.random() > schlick(ray.direction, normal, n1, n2)) {
                scattered = refract(hit, ray, n1, n2);

            } else {

                scattered = reflect(hit, ray);
            }
        } else {

            scattered = reflect(hit, ray);
        }

        return scattered;
    }

    public double schlick(Direction d, Direction n, double n1, double n2) {
        double nenner = n1 + n2;

        double r0 = ((n1 - n2) / nenner) * ((n1 - n2) / nenner);
        double r00 = r0 + (1 - r0) * Math.pow(1 + Direction.dotProduct(n, d), 5);
        return r00;

    }

    public Ray reflect(Hit hit, Ray ray) {

        Direction reflect = subtract(ray.direction, (multiply(2, multiply(dotProduct(ray.direction, hit.normalVectorHitPoint), hit.normalVectorHitPoint))));
        Ray rayNew = new Ray(hit.positionHitPoint, reflect, Math.pow(10, -4), POSITIVE_INFINITY);
        return rayNew;
    }

    public Ray reflect2(Hit hit, Ray ray) {
        double b = (Direction.dotProduct(Direction.multiply(-1, hit.normalVectorHitPoint), ray.direction));
        Direction B = Direction.multiply(hit.normalVectorHitPoint, b);
        Direction rayMirror = Direction.add(ray.direction, Direction.multiply(2, B));
        Ray rayNew = new Ray(hit.positionHitPoint, rayMirror, Math.pow(10, -4), POSITIVE_INFINITY);
        return rayNew;
    }

    public Ray refract(Hit hit, Ray ray, double n1, double n2) {
        double r = (n1 / n2);

        double c = (-1) * Direction.dotProduct(hit.normalVectorHitPoint, ray.direction);

        //dt
        double sqrt = 1 - (r * r) * (1 - (c * c));
        if (sqrt < 0) {
            Ray rayNew = reflect(hit, ray);

            return rayNew;

        } else {
            double brackets = r * c - Math.sqrt(sqrt);
            Direction dt = add(multiply(r, ray.direction), multiply(brackets, hit.normalVectorHitPoint));
            Ray rayNew = new Ray(hit.positionHitPoint, dt, Math.pow(10, -4), POSITIVE_INFINITY);
            return rayNew;
        }
    }

    public Ray refract2(Hit hit, Ray ray, double n1, double n2) {

        double r = n1 / n2;
        double c = -Direction.dotProduct(hit.normalVectorHitPoint, ray.direction);
        double sqrt = 1 - (Math.pow(r, 2)) * (1 - (Math.pow(c, 2)));

        if (sqrt < 0) {
            return reflect(hit, ray);
        } else {
            Direction dt = Direction.add(Direction.multiply(r, ray.direction), Direction.multiply((r * c) - Math.sqrt(sqrt), hit.normalVectorHitPoint));
            Ray rayNew = new Ray(hit.positionHitPoint, dt, Math.pow(10, -4), POSITIVE_INFINITY);
            return rayNew;
        }
    }
}


