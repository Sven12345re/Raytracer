package cgg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import cgtools.Matrix;


public class Group implements Shape {

    List<Shape> shapes;
    Hit hit;
    Transformation transformation;



    public Group(Matrix rotate) {
        transformation = new Transformation(rotate);
        shapes = new ArrayList<>();
    }

    public void addShapes(Shape... shape) {
        shapes.addAll(Arrays.asList(shape));

    }

    @Override
    public Hit intersectWith(Ray r) {

        List<Hit> hitList = new ArrayList<>();
        for (Shape shape : shapes) {

            //Transforqm Ray
            Ray newRay = new Ray(transformation.pointObject(r.originPoint), transformation.directionObject(r.direction), r.tmin, r.tmax);

            hit = shape.intersectWith(newRay);
            if (hit != null) {
                hitList.add(hit);
            }
        }
        if (!hitList.isEmpty()) {
            Collections.sort(hitList);
            Hit hit2 = hitList.get(0);
            return new Hit(transformation.pointWorld(hit2.positionHitPoint),transformation.normalWorld(hit2.normalVectorHitPoint),hit2.materialHit,hit2.t );
        } else {
            return null;
        }
    }
}




