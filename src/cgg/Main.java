package cgg;
import cgtools.*;

import static cgtools.Color.add;
import static cgtools.Color.multiply;
import static cgtools.Vector.*;
import static cgtools.Vector.dotProduct;


public class Main {




        private static final int width = 320;
        private static final int height = 240;

        static Camera camera = new Camera(width, height, Math.PI / 3);
        private static Group group1 = Scene.scene1();


        @SuppressWarnings("null")
        public static void main(String[] args) {

            Image image = new Image(width, height);


            for (int x = 0; x != width; x++) {
                for (int y = 0; y != height; y++) {
                    Color color = Color.black;
                    int n = 20;
                    for (int xi = 0; xi < n; xi++) {
                        for (int yi = 0; yi < n; yi++) {
                            double rx = cgtools.Random.random();
                            double ry = cgtools.Random.random();
                            double xs = x + (xi + rx) / n;
                            double ys = y + (yi + ry) / n;

                            color = Color.add(color, pixelColor(xs, ys));
                        }
                    }
                    double x2 = x;
                    double prozent = x2 / 320 * 100;
                    int prozentInt = (int) prozent;
                    System.out.println("Bitte warten!" + prozentInt + "%");
                    image.setPixel(x, y, Color.divide(color, n * n));
                }
            }
            write(image, "doc/a04-3-spheres.png");
            write(image, "doc/a04-scene.png");


        }

        static Color pixelColor(double x, double y) {

            Direction rayDirection = camera.generateRay(x, y);
            Ray ray = new Ray(Point.point(0, 0, 0), rayDirection, 0, 1000000000);
            Hit hit = group1.intersectWith(ray);

            if (hit != null) {
                Color color = shade(hit.x, hit.n, hit.c);

                return color;
            } else return Color.gray;


        }

        static Color shade(Point position, Vector normal, Color color) {

            Direction bla = direction(1, 1, 0.5);

            Vector lightDir = Vector.normalize(bla);
            Color ambient = multiply(0.1, color);
            Color diffuse = multiply(0.9 * Math.max(0, dotProduct(lightDir, normal)), color);
            return add(ambient, diffuse);
        }

        static void write(Image image, String filename) {
            try {
                image.write(filename);
                System.out.println("Wrote image: " + filename);
            } catch (Exception error) {
                System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
            }
        }
    }

