package cgg;

import java.util.List;
import cgtools.*;

import static cgtools.Vector.color;
import static cgtools.Vector.point;
import static cgtools.Vector.direction;


public class Emitter implements Material {

    private Color albedo;
    private Color emission;

    public Emitter(Color albedo, Color emission){
        this.albedo = albedo;
        this.emission = emission;
    }
    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {
        ReflectionProperties properties = new ReflectionProperties(hit, ray, albedo, emission);
        return properties;
    }

    @Override
    public Ray reflection(Hit hit) {
        return null;
    }
}
