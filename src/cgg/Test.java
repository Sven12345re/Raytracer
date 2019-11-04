package cgg;

import cgtools.Point;

import static cgtools.Color.red;
import static cgtools.Vector.direction;
import static cgtools.Vector.point;

class Test {
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
