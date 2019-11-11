package cgg;

import cgtools.*;

public interface Material {

    ReflectionProperties properties(Hit hit, Ray ray);
    public Ray reflection(Hit hit);
}
