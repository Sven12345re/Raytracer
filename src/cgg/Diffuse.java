package cgg;

import static cgtools.Random.random;
import static cgtools.Vector.length;
import static java.lang.Double.POSITIVE_INFINITY;
import cgtools.*;

import static cgtools.Vector.color;
import static cgtools.Vector.point;
import static cgtools.Vector.direction;



public class Diffuse implements Material {

    Color albedo;

    public Diffuse(Color albedo) {
        this.albedo = albedo;
        ;
    }

    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {
        ReflectionProperties properties = new ReflectionProperties(hit, ray, albedo, Color.black);
        return properties;
    }

    public Ray reflection(Hit hit) {
        Point r = point(random() * 2 - 1, random() * 2 - 1, random() * 2 - 1);
        while (length(r) >1) {
            r = point(random() * 2 - 1, random() * 2 - 1, random() * 2 - 1);
        }

        Direction addDirection1 = direction(hit.normalVectorHitPoint.x, hit.normalVectorHitPoint.y, hit.normalVectorHitPoint.z);
        Direction addDirection2 = direction(r.x,r.y,r.z);
        Direction direction = Direction.normalize(Direction.add(addDirection1,addDirection2));
        Ray ray = new Ray(hit.positionHitPoint, direction, Math.pow(10, -4), POSITIVE_INFINITY);
        return ray;


    }
}
