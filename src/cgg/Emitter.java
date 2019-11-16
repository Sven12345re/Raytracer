package cgg;
import cgtools.Color;

public class Emitter implements Material {

    private Color albedo;
    private Color emission;

    public Emitter(Color albedo, Color emission){
        this.albedo = albedo;
        this.emission = emission;
    }
    @Override
    public ReflectionProperties properties(Hit hit, Ray ray) {
        ReflectionProperties properties = new ReflectionProperties(hit, null, albedo, emission);
        return properties;
    }


    public Ray reflection(Hit hit) {
        return null;
    }
}
