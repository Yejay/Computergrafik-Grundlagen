package cgg.a10;

import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Sampler;
import cgtools.Vector;

public class BackgroundMaterial implements Material {
    Sampler texture;

    /***
     * Default Constructor - Sets constant texture color to white.
     */
    public BackgroundMaterial() {
        this.texture = new Constant(Vector.white);
    }

    /***
     * Constructor with Sampler as parameter
     * 
     * @param texture - Sampler
     */
    public BackgroundMaterial(Sampler texture) {
        this.texture = texture;
    }

    /***
     * Constructor with Color as parameter
     * 
     * @param color
     */
    public BackgroundMaterial(Color color) {
        this.texture = new Constant(color);
    }

    /***
     * Constructor with String as parameter
     * 
     * @param filename
     */
    public BackgroundMaterial(String filename) {
        this.texture = new Texture(new ImageTexture(filename));
    }

    @Override
    public Color emission(Ray ray, Hit hit) {
        return texture.getColor(hit.texture().x(), hit.texture().y());
    }

    @Override
    public Color albedo(Ray ray, Hit hit) {
        return Vector.black;
    }

    @Override
    public Ray scattered(Ray ray, Hit hit) {
        return null;
    }

}
