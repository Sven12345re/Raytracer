package cgg;


import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import static cgtools.Vector.*;
import static java.lang.Double.POSITIVE_INFINITY;

public class Glass implements Material {

    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {

        Ray rayNew = scatteredRay(hit, ray);
        ReflectionProperties properties = new ReflectionProperties(hit, rayNew, Color.white, color(0,0,0));
        return properties;
    }

    public Ray scatteredRay(Hit hit, Ray ray) {
        Ray scattered = null;
        //Brechungsindex für Luft
        double n1 = 1;
        //Brechungsindex für Glas
        double n2 = 1.5;
        double n22 = 0;
        double n11 = 0;

        Direction normal = hit.normalVectorHitPoint;
        if (dotProduct(normal, ray.direction) > 0) {

            //Vertausche Werte von n1 mit n2
            n22 = n1;
            n11 = n2;
            n2 = n22;
            n1 = n11;
        }
        if (refract(hit, ray, n1, n2) != null) {
            if (Random.random() > schlick(n1, n2)) {
                scattered = refract(hit, ray, n1, n2);

            } else {

                scattered = reflect(hit, ray);

            }
        } else {

            scattered = reflect(hit, ray);
        }

        return scattered;
    }

    public double schlick(double n1, double n2) {

        double r0 = ((n1 - n2) / (n1 + n2)) * ((n1 - n2) / (n1 + n2));
        return r0;

    }

    public Ray reflect(Hit hit, Ray ray) {

        //Formel für den Perfekten Spiegel
        double nd = Direction.dotProduct(hit.normalVectorHitPoint,ray.direction);
        Direction perfectMirror = Direction.subtract(ray.direction,Direction.multiply(2*nd, hit.normalVectorHitPoint));
        Ray rayNew = new Ray(hit.positionHitPoint, perfectMirror, Math.pow(10, -4), POSITIVE_INFINITY);
        return rayNew;

    }

    public Ray refract(Hit hit, Ray ray, double n1, double n2) {

        //Brechungsgesetz
        double r = (n1 / n2);
        double c = (-1) * Direction.dotProduct(hit.normalVectorHitPoint, ray.direction);
        Direction rd = multiply(r,ray.direction);
        double sqrt = 1 - (r * r) * (1 - (c * c));

        //Totalreflexion
        if (sqrt < 0) {
            Ray rayNew = reflect(hit, ray);
            return rayNew;

        } else {
            Direction dt = add(rd,multiply(r*c-Math.sqrt(1-Math.pow(r,2)*(1-Math.pow(c,2))),hit.normalVectorHitPoint));
            Ray rayNew = new Ray(hit.positionHitPoint, dt, Math.pow(10, -4), POSITIVE_INFINITY);
            return rayNew;
        }
    }


}


