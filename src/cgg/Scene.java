package cgg;


import java.util.ArrayList;
import java.util.List;

import cgg.Materialien.*;
import cgg.Shapes.Plane;
import cgg.Shapes.Sphere;
import cgg.Shapes.Cylinder;
import cgtools.*;

import static cgtools.Vector.color;
import static cgtools.Vector.point;
import static cgtools.Vector.direction;

public class Scene {

    public static Group scene1() {

        List<Shape> scene;
        scene = new ArrayList<>();
        Diffuse diffuse = new Diffuse(color(0.05, 0.3, 0.95));
        Diffuse diffuse1 = new Diffuse(Color.gray);
        Diffuse diffuseGround = new Diffuse(color(0,1,0));
        Diffuse diffuseBlack = new Diffuse(Color.black);
        Diffuse diffuse2 = new Diffuse(color(1, 0.81, 0.349));
        Diffuse diffuse3 = new Diffuse(color(0.349, 1, 0.5647));
        Diffuse diffuse4 = new Diffuse(color(0.93,0.02,0.01));
        Emitter emitter = new Emitter(Color.white, Color.white);
        Emitter emitter2 = new Emitter(Color.white, color(1,0.8,0));
        Emitter emitter3 = new Emitter(Color.white, color(1,0.5,0.3));

        Emitter emitterBlau = new Emitter(Color.white, color(0,0.533,1));
        Mirror mirror = new Mirror(2);
        Mirror mirror2 = new Mirror(0);
        Glass glass = new Glass();

        Shape background = new Background(emitter);
        scene.add(background);
        Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0), diffuse1);
        scene.add(ground);
        Shape globe1 = new Sphere(point(0.8, 0, -6), 0.6, diffuse4);
        //scene.add(globe1);
        Shape globe2 = new Sphere(point(0, 0.1, -2), 0.3, glass);
       // scene.add(globe2);
        Shape globe3 = new Sphere(point(-0.8, 0.2, -2), 0.2, diffuse3);
       // scene.add(globe3);
        Shape globe4 = new Sphere(point(1, 0, -2), 0.3 ,mirror);
       // scene.add(globe4);
        Cylinder cylinder = new Cylinder(point(-4.5, 0, -50), 0.5, 0.02, diffuse3);
        scene.add(cylinder);

        for(int i = 0; i < 200; i = i+ 3){

            Shape globeScene = new Sphere(point(1, 0, -i), 0.6, mirror2);
            scene.add(globeScene);
            Shape globeScene2 = new Sphere(point(-10, 0, -i), 0.6, mirror2);
            scene.add(globeScene2);
        }
        Group group = new Group(scene);
        return group;
    }

}

