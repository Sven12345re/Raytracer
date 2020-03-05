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

        Diffuse diffuse = new Diffuse(color(0.05, 0.3, 0.95));
        Diffuse diffuse1 = new Diffuse(Color.gray);
        Diffuse diffuseGround = new Diffuse(color(0,1,0));
        Diffuse diffuseBlack = new Diffuse(Color.black);
        Diffuse diffuse2 = new Diffuse(color(1, 0.81, 0.349));
        Diffuse diffuse3 = new Diffuse(color(0.349, 1, 0.5647));
        Diffuse diffuse4 = new Diffuse(color(0.93,0.02,0.01));
        Emitter emitter = new Emitter(Color.black, Color.gray);
        Emitter emitter2 = new Emitter(Color.white, color(1,0.8,0));
        Emitter emitter3 = new Emitter(Color.white, color(1,0.5,0.3));
        Emitter emitterBlau = new Emitter(Color.white, color(0,0.533,1));
        Mirror mirror = new Mirror(2);
        Mirror mirror2 = new Mirror(0);
        Glass glass = new Glass();
        Shape background = new Background(emitter);
        Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0), diffuse1);


        //Übergeordnete Group
        Matrix matrixGroup = Matrix.multiply(Matrix.rotation(direction(1, 0, 0), -0) , (Matrix.translation(direction(0, 0, 0))));
        Group group = new Group(matrixGroup);

        //Untergeordnete Group
        Matrix matrix = Matrix.multiply(Matrix.rotation(direction(0, 1, 0), 0) ,Matrix.translation(direction(0, 0, 0)));
        Group sphereGroup = new Group(matrix);
        for(int i = 0; i < 10; i++){

            Shape sphere = new Sphere(point(1, 0, -i), 0.6, mirror2);
            sphereGroup.addShapes(sphere);
            Shape sphere2 = new Sphere(point(-10, 0, -i), 0.6, mirror2);
            sphereGroup.addShapes(sphere2);
        }

        Matrix matrix2 = Matrix.multiply(Matrix.rotation(direction(1, 0, 0), -90),
                            Matrix.translation(direction(0, 0, 150)),
                            Matrix.rotation(direction(0,0,1) , -45));
        Group sphereGroup2 = new Group(matrix2);
        Matrix matrix3 = Matrix.multiply(Matrix.rotation(direction(1, 0, 0), -90),
                            Matrix.translation(direction(7, 10, 150)),
                            Matrix.rotation(direction(0,0,1) , 45));
        Group sphereGroup3 = new Group(matrix3);
        for(int i = 0; i < 10; i ++){

            Shape sphere = new Sphere(point(1, 0, -i), 0.6, emitter3);
            sphereGroup2.addShapes(sphere);
            Shape sphere2 = new Sphere(point(0, 0, -i), 0.6, emitter3);
            sphereGroup3.addShapes(sphere2);
        }

        Group a082 = new Group(matrixGroup);

        a082.addShapes(sphereGroup, sphereGroup2, sphereGroup3);


        group.addShapes(background,ground, a082);
        return group;
    }

}

