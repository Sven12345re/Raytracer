package cgg;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

import static cgtools.Color.*;
import static cgtools.Vector.*;

class DrawSpehre{

    Color pixelColor(double x, double y) {

        double radius = 1;
        Point mittelpunkt = point(0,0,-2);
        Camera camera = new Camera(160, 90,Math.PI/2);
        Sphere sphere = new Sphere(mittelpunkt, radius,blue);

        Direction rayDirection = camera.generateRay(x, y);
        Ray ray2 = new Ray(point(0,0,0), rayDirection,0,100000000);

        Hit saveHit = sphere.intersectWith(ray2);



        if(saveHit != null) {

            Color color = shade(saveHit.x, saveHit.n, saveHit.c);

            return color;

        }else return Color.black;



    }

    Color shade(Point position, Vector normal, Color color) {

        Direction bla = direction(1,1,0.5);

        Vector lightDir = Vector.normalize(bla);
        Color ambient = multiply(0.1, color);
        Color diffuse = multiply(0.9 * Math.max(0, dotProduct(lightDir, normal)), color);
        return add(ambient, diffuse);
    }

}
