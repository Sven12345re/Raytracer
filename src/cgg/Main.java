package cgg;

import cgtools.Random;
import cgtools.*;

import static cgtools.Vector.color;
import static cgtools.Vector.point;
import static cgtools.Vector.direction;
import java.io.IOException;


public class Main {
    private static Camera camera = new Camera(1600, 900, Math.PI / 3);
    private static Group group1 = Scene.scene1();

    public static void main(String[] args) {

        Image image1;
        final int width = 1600;
        final int height = 900;

        image1 = new Image(width, height);

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                Color color = color(0, 0, 0);
                int n = 2;
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
        write(image1, "doc/a05-diffuse-spheres.png");


    }

    static Color pixelColor(double x, double y) {


        Direction rayDirection = camera.generateRay(x, y);

        Ray ray = new Ray(point(0, 0, 0), rayDirection, 0, 1000000000);

        Hit hit = group1.intersectWith(ray);
        if (hit != null) {


            Color color = calculateRadiance(group1,ray, 5);

            // Vec3 color = lightSurface(hit.positionHitPoint, hit.normalVectorHitPoint, hit.colorHitPoint);
            return color;
        } else return Color.white;

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

                Ray reflection = material.reflection(hit);
                if(reflection != null) {
                    Color color = Color.add(proberties.emission(), Color.multiply(proberties.albedo(), calculateRadiance(scene, reflection, depth - 1)));
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
        } catch (Exception error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }

}
