package cgg;


import java.util.ArrayList;
import java.util.List;

import cgtools.Vector;
import cgtools.*;



public class Scene {

    public static Group scene1() {

        ///Shape background = new Background(Color.black);
        Shape ground = new Plane(Point.point(0.0, -0.5, 0.0), Direction.direction(0, 1, 0), Color.gray);
        Shape globe1 = new Sphere(Point.point(-1.0, -0.25, -2.5), 0.7, Color.red);
        Shape globe2 = new Sphere(Point.point(0.0, -0.25, -2.5), 0.5, Color.green);
        Shape globe3 = new Sphere(Point.point(1.0, -0.25, -2.5), 0.7, Color.blue);

        List<Shape> scene;
        scene = new ArrayList<Shape>();

        //scene.add(background);
        scene.add(ground);
        scene.add(globe1);
        scene.add(globe2);
        scene.add(globe3);

        Group group = new Group(scene);
        return group;
    }

    public static Group scene2() {

        List<Shape> scene;
        scene = new ArrayList<Shape>();
        //Shape background = new Background(white);

        Shape ground = new Plane(Point.point(0.0, -0.5, 0.0), Direction.direction(0.00, 1, 0),
                        Color.blue);

        Shape globe1 = new Sphere(Point.point(-1.0, -0.25, -5), 0.6, Color.red);
        Shape globe2 = new Sphere(Point.point(0.0, 0.25, -2.5), 0.2, Color.green);

        Shape globe3 = new Sphere(Point.point(1.0, -0.25, -3), 0.7, Color.blue);
        Shape globe4 = new Sphere(Point.point(-4, 1, -8), 0.8, Color.white);



        //scene.add(background);
        scene.add(ground);
        scene.add(globe1);
        scene.add(globe2);
        scene.add(globe4);
        scene.add(globe3);

        Group group = new Group(scene);
        return group;
    }
}
