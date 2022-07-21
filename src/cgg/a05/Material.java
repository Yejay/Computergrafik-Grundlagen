package cgg.a05;

import cgtools.*;

public interface Material {
    Color emission(Ray r, Hit h);

    Color albedo(Ray r, Hit h);

    Ray scattered(Ray r, Hit h);
}
