package cgg;

import cgtools.*;
import static cgtools.Vector.point;
import static cgtools.Vector.direction;
import static java.lang.Float.POSITIVE_INFINITY;

public class Background implements Shape {

    Color backgroundColor;
    Point positionHitPoint;
    Direction normalVectorHitPoint;
    Material material;


    public Background(Material material) {
        this.material = material;

    }

    @Override
    public Hit intersectWith(Ray r) {


        Direction x = Direction.multiply(r.direction, POSITIVE_INFINITY);
        Point norm = r.originPoint;
        Hit hit = new Hit(norm, material, x, POSITIVE_INFINITY);

        return hit;
    }



}

