package cgg;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;
import static cgtools.Vector.*;

import javax.naming.directory.DirContext;

import java.io.IOException;

import static cgtools.Color.*;

public class A03 {
    public static void main(String[] args) throws IOException {
        final int width = 160;
        final int height = 90;
        final String filename34 = "doc/a03-one-sphere.png";




        //diese Klasse austauschen
        class Ray{
            Point urpsrung;
            Direction direction;
            int tMin;
            int tMax;

            public Ray(Point ursprung, Direction direction, int tMin, int tMax){
                this.urpsrung = ursprung;
                this.direction = direction;
                this.tMin = tMin;
                this.tMax = tMax;
            }


            public Vector pointAt(double t){

                Vector strahlpunkt = Vector.add(urpsrung, Vector.multiply(t,direction));

                return strahlpunkt;
            }
        }

        class Hit{
            double t;
            Point x;
            Vector n;
            Color c;

            public Hit(double t, Point x, Vector n, Color c){
                this.t = t;
                this.x = x;
                this.n = n;
                this.c = c;
            }

            public Point getX() {
                return x;
            }

            public Vector getN() {
                return n;
            }

            public int compareTo(Hit o) {

                if (this.t < o.t) {
                    return -1;
                }else if (this.t > o.t) {
                    return 1;
                }else {
                    return 0;
                }
            }
        }

         class Camera{
            double width;
            double heigth;
            double cameraAngle;
            Direction generateRay;

            public Camera(double width, double height, double cameraAngle){
                this.width = width;
                this.heigth = height;
                this.cameraAngle = cameraAngle;
            }

            public Direction generateRay(double xp, double yp) {

                double x = (xp - (width /2));
                double y = ((heigth / 2) - yp);
                double z = (-((width / 2) / Math.tan(cameraAngle / 2)));

                generateRay = direction(x, y, z);

                return Direction.normalize(generateRay);

            }

        }

        class Sphere{
            Point mittelpunkt;
            double radius;
            Color color;

            public Sphere(Point mittelpunkt, double radius, Color color){
                this.mittelpunkt = mittelpunkt;
                this.radius = radius;
                this.color = color;

            }

            public Hit intersectWith(Ray ray) {

                Vector oc = Vector.subtract(ray.urpsrung,mittelpunkt);
                double a = Vector.dotProduct(ray.direction, ray.direction);
                double b = 2 * Vector.dotProduct(ray.direction, oc);
                double c = Vector.dotProduct(oc,oc)-radius*radius;
                double discriminant = b * b - 4 * a * c;

                if(a == 0 || discriminant < 0){

                    return null;
                }else{
                    double t1 = (-b - Math.sqrt(discriminant)) /2;
                    double t2 = (-b + Math.sqrt(discriminant)) /2;
                    double t = (t1 < t2) ? t1 : t2;

                    if(ray.tMin < t && ray.tMax > t){

                        Vector test = Vector.multiply(t, ray.direction);

                        double pointX = test.x;
                        double pointY = test.y;
                        double pointZ = test.z;

                        Point x = point(pointX,pointY,pointZ);


                        Vector normal = Vector.divide(Vector.subtract(x,mittelpunkt),radius);
                        Hit hit = new Hit(t,x,normal,color);
                        return hit;
                    }

                    return null;
                }


            }
        }

        class TestValues {
            public void cameraTest(){

                Camera camera = new Camera(10, 10,Math.PI/2);
                System.out.println("------------------------------");
                System.out.println("Kamera-Test");
                System.out.println(camera.generateRay(0, 0));
                System.out.println(camera.generateRay(5, 5));
                System.out.println(camera.generateRay(10, 10));
                System.out.println("------------------------------");
                System.out.println("Kugel-Test");
                double radius = 1;

                try {
                    Point mittelpunkt = point(0,0,-2);


                    Sphere sphere = new Sphere(mittelpunkt, radius,red);
                    Ray ray2 = new Ray(point(0,0,0), direction(0,0,-1),0,100000000);

                    Hit saveHit = sphere.intersectWith(ray2);
                    System.out.println("Hitpoint:" + saveHit.getX());
                    System.out.println("Normalenvektor: " + saveHit.getN());
                }catch(Exception e){
                    System.out.println("null");
                }

                try {
                    Point mittelpunkt = point(0,0,-2);
                    Ray ray3 = new Ray(point(0,0,0), direction(0,1,-1),0,100000000);
                    Sphere sphere = new Sphere(mittelpunkt, radius,red);
                    Hit saveHit = sphere.intersectWith(ray3);
                    System.out.println("Hitpoint:" + saveHit.getX());
                    System.out.println("Normalenvektor: " + saveHit.getN());
                }catch(Exception e){
                    System.out.println("null");
                }

                try {
                    Point mittelpunkt = point(0,-1,-2);
                    Sphere sphere = new Sphere(mittelpunkt, radius,red);
                    Ray ray2 = new Ray(point(0,0,0), direction(0,0,-1),0,100000000);
                    Hit saveHit = sphere.intersectWith(ray2);
                    System.out.println("Hitpoint:" + saveHit.getX());
                    System.out.println("Normalenvektor: " + saveHit.getN());


                }catch(Exception e){
                    System.out.println("null");
                }

                try {
                    Point mittelpunkt = point(0,0,-2);
                    Sphere bullet = new Sphere(mittelpunkt, radius,red);
                    Ray ray2 = new Ray(point(0,0,-4) , direction(0,0,-1),0,100000000);
                    Hit saveHit = bullet.intersectWith(ray2);
                    System.out.println("Hitpoint:" + saveHit.getX());
                    System.out.println("Normalenvektor: " + saveHit.getN());


                }catch(Exception e){
                    System.out.println("null");
                }

                try {
                    Point mittelpunkt = point(0,0,-4);
                    Sphere bullet = new Sphere(mittelpunkt, radius,red);
                    Ray ray2 = new Ray(point(0,0,0) , direction(0,0,-1),0,2);
                    Hit saveHit = bullet.intersectWith(ray2);
                    System.out.println("Hitpoint:" + saveHit.getX());
                    System.out.println("Normalenvektor: " + saveHit.getN());


                }catch(Exception e){
                    System.out.println("null");
                }

                System.out.println("----------------------------");
            }
        }

        class DrawSpehre{

             Color pixelColor(double x, double y) {

                double radius = 1;
                 Point mittelpunkt = point(0,0,-2);
                 Camera camera = new Camera(160, 90,Math.PI/2);
                 Sphere sphere = new Sphere(mittelpunkt, radius,red);

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

        //diese Klasse austauschen

        Image image = new Image(width, height);
        DrawSpehre test23 = new DrawSpehre();
        TestValues test = new TestValues();
        test.cameraTest();

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                Color color = color(0,0,0);
                int n = 20;
                for (int xi = 0; xi < n; xi++) {
                    for (int yi = 0; yi < n; yi++) {
                        double rx = cgtools.Random.random();
                        double ry = cgtools.Random.random();
                        double xs = x + (xi + rx) / n;
                        double ys = y + (yi + ry) / n;

                        color = Color.add(color, test23.pixelColor(xs,ys));
                    }
                }
                double x2 = x;
                double prozent = x2/width*100;
                int prozentInt = (int)prozent;
                System.out.println("Bitte warten!" + prozentInt + "%");
                image.setPixel(x, y, Color.divide(color, n*n));

            }
        }

        image.write(filename34);
        System.out.println("Wrote image: " + filename34);
    }
}

