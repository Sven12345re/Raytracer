package cgg;


import cgtools.Random;
import cgtools.*;
import cgtools.Vector.*;

import static cgtools.Random.random;
import static cgtools.Vector.color;
import static java.lang.Double.POSITIVE_INFINITY;

public class Mirror implements Material {


    private Color emission;
    double roughness;
    Ray ray;

    public Mirror(Color emission, double roughness) {
        this.emission = emission;
        this.roughness = roughness;
    }

    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {
        double b = (Vector.dotProduct(Vector.multiply(-1, hit.normalVectorHitPoint), ray.direction));
        Direction B = Vector.multiply(hit.normalVectorHitPoint, b);
        Direction r = Vector.add(ray.direction, Direction.multiply(2, B));

        //Roughness
        if (roughness > 0) {

            Direction xrnd = Vector.direction(Random.random()*roughness/10, random() * roughness/10, random() * roughness/10);
            Direction rrough = Vector.add(r, Vector.multiply(roughness, xrnd));
            r = rrough;
        }

        Ray rayNew = new Ray(hit.positionHitPoint, r, Math.pow(10, -4), POSITIVE_INFINITY);
        ReflectionProperties properties = new ReflectionProperties(hit, rayNew, Color.gray, color(0,0,0));
        return properties;
    }


    public Ray reflection(Hit hit) {
        //b = -n*d

        return null;
    }
}

