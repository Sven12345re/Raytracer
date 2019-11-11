package cgg;

import cgtools.ImageWriter;
import cgtools.Color;
import java.io.IOException;

public class Image {

    int width;
    int height;
    int pixel;
    double data[];

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        data = new double [width*height*3];
    }

    public void setPixel(int x, int y, Color color) {

        // Mit Gamma Korrektur
        pixel = y*width*3 + x*3;
        data[pixel]= Math.pow(color.r, 1/2.2);
        data[pixel+1] = Math.pow(color.g,1/2.2);
        data[pixel + 2] = Math.pow(color.b, 1/2.2);

        //////// Ohne Gamma Korrektur
//		pixel = y*width*3 + x*3;
//		data[pixel]= color.x;
//		data[pixel+1] = color.y;
//		data[pixel + 2] = color.z;


    }

    public void write(String filename) throws IOException {

        ImageWriter.write(filename, data, width, height);

    }
}
