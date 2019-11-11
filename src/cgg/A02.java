package cgg;

import cgtools.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static cgtools.Color.*;

public class A02 {

    public static void main(String[] args) throws IOException {
        final int width = 160;
        final int height = 90;
        final String filename21 = "doc/a02-discs.png";
        final String filename23 = "doc/a02-supersampling.png";
        final String filename = "doc/test.png";
        int a = 0;


        class Circle {
            Color color;
            double radius;
            double positionX;
            double positionY;


            Circle(Color color, int radius, double positionX, double positionY) {
                this.color = color;
                this.radius = radius;
                this.positionX = positionX;
                this.positionY = positionY;
            }

            Color getColor(double x, double y) {

                if (isPointCircle(x, y)) {
                    return color;
                } else {
                    return null;
                }
            }

            public double getRadius() {
                return radius;
            }

            public boolean isPointCircle(double x, double y) {

                if ((Math.pow((x - positionX), 2) + Math.pow((y - positionY), 2)) < Math.pow(radius, 2)) {
                    return true;
                } else {
                    return false;
                }

            }
        }

        Image image = new Image(width, height);

        List<Circle> circles = new ArrayList<>();
        Random random = new Random();

        // Circle circleTest = new Circle(red,40,60,45);


        for (int i = 0; i < 100; i++) {
            int randomRadius = random.nextInt((40 - 5) + 1) + 5;
            int randomX = random.nextInt((160 - 0) + 1) + 0;
            int randomY = random.nextInt((90 - 0) + 1) + 0;
            Color color = new Color(random.nextInt(), random.nextInt(), random.nextInt());
            circles.add(new Circle(color, randomRadius, randomX, randomY));


        }
        Comparator<Circle> byRadius = Comparator.comparing(Circle::getRadius);
        Collections.sort(circles, byRadius.reversed());


        for (int i = 0; i < circles.size(); i++) {

            for (int x = 0; x != width; x++) {
                for (int y = 0; y != height; y++) {

                    int n = 100;

                    if(circles.get(i).getColor(x,y) != null){

                        image.setPixel(x, y, circles.get(i).getColor(x, y));

                    }


                }
            }
        }
        Circle circleTest = new Circle(red, 40, 60, 45);


        image.write(filename23);
        System.out.println("Wrote image: " + filename23);

    }
}
