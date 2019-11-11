package cgg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Group implements Shape {
    double t;
    List<Shape> shapes;
    Hit hit;


    public Group(List<Shape> shapes) {
        this.shapes = shapes;

    }

    @Override
    public Hit intersectWith(Ray r) {

        List<Hit> hitList = new ArrayList<Hit>();
        for (Shape shape : shapes) {

            hit = shape.intersectWith(r);
            if (hit != null) {
                hitList.add(hit);
            }
        }

        if (!hitList.isEmpty()){
            Collections.sort(hitList);
            return hitList.get(0);
        }
        else {
            return null;
        }


    }
}




