package cgg;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

public class Transformation {

    public Matrix transformation;
    public Matrix transformationInverse;


    public Transformation(Matrix transformation) {
        this.transformation = transformation;
        transformationInverse = transformation.invert(transformation);
    }

    public Point pointObject(Point point) {
        return transformation.multiply(transformation, point);
    }

    public Point pointWorld(Point point) {
        return transformationInverse.multiply(transformationInverse, point);
    }

    public Direction directionObject(Direction dir) {
        return transformation.multiply(transformation, dir);
    }

    public Direction normalWorld(Direction normal) {
        return transformationInverse.invert(transformation).transpose(transformationInverse).multiply(transformationInverse, normal);
    }


}
