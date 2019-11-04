package cgg;

import cgtools.*;
import static cgtools.Vector.point;

public class Background implements Shape {

    private Color color;


/*  public static Background of(Vec3 color, Material material) {
    return new Background(color, material);
  }*/

    public Background(Color color) {
        this.color = color;

    }


    public Hit intersectWith(Ray ray) {
        @SuppressWarnings("static-access")

        Vector test = Vector.multiply(ray.direction, Double.POSITIVE_INFINITY);

        double pointX = test.x;
        double pointY = test.y;
        double pointZ = test.z;


        Point x = point(pointX,pointY,pointZ);
        Vector one = Vector.zero;
        Double a = Double.POSITIVE_INFINITY;
         Hit hit = new Hit(a, x, one , color);

        return hit;
    }
}
