package cgg.Materialien;

import cgg.Hit;
import cgg.Material;
import cgg.Ray;
import cgg.ReflectionProperties;
import cgtools.Color;
import cgtools.Direction;
import static cgtools.Random.random;
import static cgtools.Vector.*;
import static java.lang.Double.POSITIVE_INFINITY;

public class Diffuse implements Material {

    Color albedo;

    public Diffuse(Color albedo) {
        this.albedo = albedo;

    }

    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {

        Direction r = direction(random()*2-1 , random()*2-1 , random()*2-1 );
        while (length(r) <= 1) {
            r = direction(random()*2-1 , random()*2-1, random()*2-1 );
        }

        //Addiere Zufällige Richtung r zu Oberflächennormale n
        Direction direction = Direction.normalize(Direction.add(hit.normalVectorHitPoint, r));

        //Keine Selbsttreffer zulassen, daher Ursprung des Sekundärstrahls ist gleich dem Trefferpunkt(10'-4)
        ray = new Ray(hit.positionHitPoint, direction, Math.pow(10, -4), POSITIVE_INFINITY);

        ReflectionProperties properties = new ReflectionProperties(hit, ray, albedo, Color.black);
        return properties;
    }

}

