package cgg.a11;

import java.util.List;

public class World {
    public final List<Light> lights;
    public final Group scene;

    public World(List<Light> lights, Group scene) {
        this.lights = lights;
        this.scene = scene;
    }

    public void addLight(Light light) {
        lights.add(light);
    }
}
