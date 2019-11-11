package cgg;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;
import static cgtools.Vector.*;
import static cgtools.Vector.point;

import java.util.Comparator;

public class Hit implements Comparable<Hit> {

    public Point positionHitPoint;
    public Color colorHitPoint;
    public Direction normalVectorHitPoint;
    public Material materialHit;
    double t; //Strahlparameter t


    public Hit(Point positionHitPoint, Material materialHit, Direction normalVectorHitPoint, double t) {

        this.positionHitPoint = positionHitPoint;
        this.normalVectorHitPoint = normalVectorHitPoint;
        this.t = t;
        this.materialHit = materialHit;


    }

    public String toString() {
        String returnString = "Hitpoint: " + positionHitPoint +
                "\nnormalVectorHitPoint: " + normalVectorHitPoint;
        return returnString;
    }

    public double getT() {
        return t;
    }


    @Override
    public int compareTo(Hit o) {

        if (this.t < o.t) {
            return -1;
        } else if (this.t > o.t) {
            return 1;
        } else {
            return 0;
        }

    }

}
