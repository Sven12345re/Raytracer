package cgg;

import cgtools.*;
import static java.lang.Float.POSITIVE_INFINITY;

public class Background implements Shape {

    Material material;


    public Background(Material material) {
        this.material = material;

    }

    @Override
    public Hit intersectWith(Ray r) {

        Point t0 = r.originPoint;
        Direction t1 = Direction.multiply(r.direction, POSITIVE_INFINITY);
        Hit hit = new Hit(t0, t1, material, POSITIVE_INFINITY);

        return hit;
    }



}

