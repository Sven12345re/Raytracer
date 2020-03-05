package cgg;

import cgtools.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;
import static cgtools.Vector.*;

public class Main {


    private static Camera camera;
    private static Group group1 = Scene.scene1();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Image image1;
        final int width = 640;
        final int height = 480;
        camera=makeCamera();
        image1 = new Image(width, height);

        Callable<Color> calculateOnePixel = () -> {

            Color color = color(0, 0, 0);
            int n = 1;
            //Stratified Sampling
            for (int xi = 0; xi < n; xi++) {
                for (int yi = 0; yi < n; yi++) {


                    final int xFinal = xi;
                    final int yFinal = yi;

                    double rx = Random.random();
                    double ry = Random.random();
                    double xs = xFinal + (xi + rx) / n;
                    double ys = yFinal + (yi + ry) / n;
                    color = color(0, 0, 0);
                    color = Color.add(color, pixelColor(xs, ys));

                    image1.setPixel(xFinal, yFinal, Color.divide(color, n * n));
                }
            }

            return color;
        };

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {

                // Pool creation
                ExecutorService pool = Executors.newFixedThreadPool(4);
                // Task submission. Calculation starts immediately.
                pool.submit(calculateOnePixel);

                pool.shutdown();

            }
        }

        write(image1, "doc/a09-benchmark-scene.png");

        long startTime = System.currentTimeMillis();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("ElapsedTime:"+ elapsedTime);

    }


    static Color pixelColor(double x, double y) {

        Ray rayDirection = camera.generateRay(x, y);
       // Ray ray = new Ray(point(0, 0, 0), rayDirection, 0, 1000000000);
        Color color = calculateRadiance(group1,rayDirection, 7);
        return color;
    }

    //Rekursives Raytracing
    static Color calculateRadiance(Shape scene, Ray ray, int depth) {
        //Check for maximum recursion depth
        if (depth == 0){
            return Color.black;
        }
        //Intersect ray with scene
        Hit hit = scene.intersectWith(ray);
        //Query material at hit point
        ReflectionProperties properties = hit.materialHit.properties(hit ,ray);

        if(properties.ray != null) {
            //Combine emission and reflection
            Color color = Color.add(properties.emission(), Color.multiply(properties.albedo(), calculateRadiance(scene, properties.ray, depth - 1)));
            return color;
        }
        else{
            return properties.emission();
        }

    }

    static void write(Image image, String filename) {
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }

    public static Camera makeCamera(){

        Matrix transform = Matrix.multiply(Matrix.rotation(direction(1,0,0),-20),
                            Matrix.rotation(direction(0,1,0),-10),
                            Matrix.translation(direction(-10,30,80)));



        return new Camera(transform, 640, 480, Math.PI / 3);
    }

}
