package cgg;

import cgtools.*;

public interface Material {

    ReflectionProperties properties(Hit hit, Ray ray);

}
