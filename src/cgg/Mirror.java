package cgg;


import cgtools.Random;
import cgtools.*;
import static cgtools.Random.random;
import static cgtools.Vector.color;
import static java.lang.Double.POSITIVE_INFINITY;

public class Mirror implements Material {

    double roughness;

    public Mirror(double roughness) {

        this.roughness = roughness;
    }

    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {

        //Formel für den Perfekten Spiegel(r = d -2(n*d)n

        //n*d
        double nd = Direction.dotProduct(hit.normalVectorHitPoint,ray.direction);
        //d-2(n*d)n
        Direction perfectMirror = Direction.subtract(ray.direction,Direction.multiply(2*nd, hit.normalVectorHitPoint));


        //Matter Spiegel
        //Zufälliger Punkt in Kugel
        Direction xrnd = Vector.direction(Random.random()*roughness/20, random() * roughness/20, random() * roughness/20);
        //r+s*xrnd
        Direction mirror = Direction.add(perfectMirror,Vector.multiply(roughness,xrnd));


        Ray rayNew = new Ray(hit.positionHitPoint, mirror, 0, POSITIVE_INFINITY);
        ReflectionProperties properties = new ReflectionProperties(hit, rayNew,Color.gray , color(0,0,0));
        return properties;
    }

}

