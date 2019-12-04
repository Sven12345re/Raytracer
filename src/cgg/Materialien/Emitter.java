package cgg.Materialien;
import cgg.Hit;
import cgg.Material;
import cgg.Ray;
import cgg.ReflectionProperties;
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

}
