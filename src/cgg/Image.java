package cgg;

import cgtools.*;

public class Image {
    private int width;

    private int height;

    private double[] doubles;

    public Image(int width, int height) {

        this.doubles = new double[width * height *3];
        this.width = width;
        this.height = height;

    }

    public void setPixel(int x, int y, Color color) {

        int index = (width* y * 3) + (x * 3);
        doubles[index] = Math.pow(color.r, 1 /2.2);
        doubles[index + 1] = Math.pow(color.g, 1 /2.2);
        doubles[index + 2] = Math.pow(color.b, 1 /2.2);
    }

    public void write(String filename) {

        cgtools.ImageWriter.write(filename, doubles, width, height);
    }

    private void notYetImplemented() {
        System.out.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
        System.exit(1);
    }
}
