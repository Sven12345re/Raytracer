package cgg;

import java.util.*;

public class Group implements Shape{

    //Atribute

    double t;
    List<Shape> shapes;
    Hit hit;


    //Konstruktor

    public Group(List<Shape> shapes) {

        this.shapes = shapes;
    }

    public Hit intersectWith(Ray ray) {

        List<Hit> hitList = new ArrayList<Hit>();

        for (Shape shape : shapes) {
            hit = shape.intersectWith(ray);
            if(hit != null) {
                hitList.add(hit);
            }
        }
        if(!hitList.isEmpty()) {

            //hitList.sort(Comparator.comparing(Hit::getX));


            return hitList.get(0);
        }else {
            return null;
        }


    }
}

