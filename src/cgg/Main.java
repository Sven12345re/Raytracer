package cgg;

import cgtools.Direction;
import cgtools.Random;
import cgtools.Color;
import cgtools.Point;
import static cgtools.Vector.point;
import static cgtools.Vector.color;

import java.io.IOException;

import static cgtools.Vector.direction;
import static cgtools.Vector.multiply;



public class Main {


    private static Camera camera = new Camera(640, 480, Math.PI / 3);
    private static Group group1 = Scene.scene1();

    public static void main(String[] args) {

        Image image1;
        final int width = 640;
        final int height = 480;

        image1 = new Image(width, height);

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                Color color = color(0, 0, 0);
                int n = 10;
                for (int xi = 0; xi < n; xi++) {
                    for (int yi = 0; yi < n; yi++) {

                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / n;
                        double ys = y + (yi + ry) / n;

                        color = Color.add(color, pixelColor(xs, ys));
                    }
                }
                image1.setPixel(x, y, Color.divide(color, n * n));
            }
        }
        write(image1, "doc/a06-mirrors-glass-2.png");


    }

    static Color pixelColor(double x, double y) {


        Direction rayDirection = camera.generateRay(x, y);

        Ray ray = new Ray(point(0, 0, 0), rayDirection, 0, Double.POSITIVE_INFINITY);

//        Hit hit = group1.intersect(ray);
//        if (hit != null) {


        Color color = calculateRadiance(group1,ray, 7);

        // Vec3 color = lightSurface(hit.positionHitPoint, hit.normalVectorHitPoint, hit.colorHitPoint);
        return color;
        //      } else return Vec3.white;

    }

    static Color lightSurface(Point position, Direction normal, Color color) {
        Direction lightDir = Direction.normalize(direction(1, 1, 0.5));
        Color ambient = Color.multiply(0.1, color);
        Color diffuse = Color.multiply(0.9 * Math.max(0, Direction.dotProduct(lightDir, normal)), color);
        return Color.add(ambient, diffuse);
    }

    static Color calculateRadiance(Shape scene, Ray ray, int depth) {
        // # Check for maximum recursion depth
        if (depth == 0){
            return Color.black;
        }
        else{
            // # Intersect ray with scene
            Hit hit = scene.intersectWith(ray);
            if (hit != null) {

                Material material = hit.materialHit;

                ReflectionProperties proberties = material.properties(hit, ray);


                if(proberties.ray != null) {
                    Color color = Color.add(proberties.emission(), Color.multiply(proberties.albedo(), calculateRadiance(scene, proberties.ray, depth - 1)));

                    return color;
                }
                else{
                    return proberties.emission();
                }
            }
        }



        return null;
    }

    static void write(Image image, String filename) {
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }

}
