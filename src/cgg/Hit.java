package cgg;

import cgtools.Color;
import cgtools.Point;
import cgtools.Vector;

class Hit {
    double t;
    Point x;
    Vector n;
    Color c;

    public Hit(double t, Point x, Vector n, Color c) {
        this.t = t;
        this.x = x;
        this.n = n;
        this.c = c;
    }

    public double getT() {
        return t;
    }

    public Point getX() {
        return x;
    }

    public Vector getN() {
        return n;
    }

    public Color getC() {
        return c;
    }
}