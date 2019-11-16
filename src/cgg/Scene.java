package cgg;


import java.util.ArrayList;
import java.util.List;
import cgtools.*;

import static cgtools.Vector.color;
import static cgtools.Vector.point;
import static cgtools.Vector.direction;

public class Scene {

    public static Group scene1() {

        List<Shape> scene;
        scene = new ArrayList<Shape>();

        Diffuse diffuse = new Diffuse(color(0.05, 0.3, 0.95));
        Diffuse diffuse1 = new Diffuse(Color.gray);
        Diffuse diffuse2 = new Diffuse(color(1, 0.81, 0.349));
        Diffuse diffuse3 = new Diffuse(color(0.349, 1, 0.5647));
        Diffuse diffuse4 = new Diffuse(color(0.93,0.02,0.01));
        Emitter emitter = new Emitter(Color.white, color(1,1,1));
        Mirror mirror = new Mirror(Color.white,0);
        Mirror mirror2 = new Mirror(Color.white,1);

        Glass glass = new Glass();

        Shape background = new Background(emitter);
        scene.add(background);


        Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0), diffuse1);
        scene.add(ground);

        Shape globe1 = new Sphere(point(-0.4, 0.21, -4), 0.6, glass);
        scene.add(globe1);

        Shape globe2 = new Sphere(point(0, 0.5, -1.5), 0.3, mirror);
        scene.add(globe2);

        Shape globe3 = new Sphere(point(-1, 0, -3), 0.5, diffuse);
        scene.add(globe3);

        Shape globe4 = new Sphere(point(1, 0, -2), 0.2 ,mirror);
        scene.add(globe4);

        Shape globe5 = new Sphere(point(-0.5, -0, 1), 0.8, diffuse);
        scene.add(globe5);

        Group group = new Group(scene);
        return group;
    }

}

