package cgg;

import cgtools.*;

public class ReflectionProperties {
    Hit hit;
    Ray ray;
    private Color albedo;
    private Color emission;

    public ReflectionProperties(Hit hit, Ray ray, Color albedo, Color emission) {
        this.hit = hit;
        this.ray = ray;
        this.albedo = albedo;
        this.emission = emission;
    }


    public Color emission() {
        return emission;
    }

    public Color albedo() {
        return albedo;

    }
}
