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

        Diffuse diffuse = new Diffuse(color(0.05, 0.3,0.95));
        Diffuse diffuse1 = new Diffuse(Color.gray);
        Diffuse diffuse2 = new Diffuse(color(1, 0.81,0.349));
        Diffuse diffuse3 = new Diffuse(color(0.349, 1,0.5647));

        Emitter emitter = new Emitter(Color.black,Color.white);
        Shape background = new Background(emitter);

        Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0),diffuse1 );

        scene.add(ground);

        Shape globe1 = new Sphere(point(-0.2, 0.21, -1), 0.2, diffuse);
        scene.add(globe1);
        Shape globe2 = new Sphere(point(0 , -0.25 , -2.5), 0.3, diffuse2);
        scene.add(globe2);
        Shape globe3 = new Sphere(point(1, 0, -3), 0.5, diffuse3);
        scene.add(globe3);
        scene.add(background);

        Group group = new Group(scene);
        return group;
    }

}

