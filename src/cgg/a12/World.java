package cgg.a12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    public List<Light> lights = new ArrayList<>();
    public List<Animator> animators = new ArrayList<>();
    public Group scene;

    public World(Group scene) {
        this.scene = scene;
    }

    public World(Group scene, Light... lights) {
        this.lights = Arrays.asList(lights);
        this.scene = scene;
    }

    public World(Group scene, List<Animator> animators, Light... lights) {
        this.lights = Arrays.asList(lights);
        this.animators = animators;
        this.scene = scene;
    }


    public void update(double time) {
        for (Animator animator : animators) {
            animator.update(time);
            // scene.bounds();
        }
    }

    public void addLight(Light light) {
        lights.add(light);
    }
}
