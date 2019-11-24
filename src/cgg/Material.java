package cgg;

public interface Material {

    ReflectionProperties properties(Hit hit, Ray ray);

}
