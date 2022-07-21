package cgg.a10;

import cgtools.*;

public class DiffusedMaterial implements Material {
    Sampler texture;

    /***
     * Default Constructor - Sets constant texture color to blue.
     */
    public DiffusedMaterial(){
        this.texture = new Constant(Vector.blue);
    }
    /***
     * Constructor with Sampler as parameter
     * 
     * @param texture - Sampler
     */
    public DiffusedMaterial(Sampler texture) {
        this.texture = texture;
    }

    /***
     * Constructor with Color as parameter
     * 
     * @param color
     */
    public DiffusedMaterial(Color color){
        this.texture = new Constant(color);
    }

    /***
     * Constructor with String as parameter
     * 
     * @param filename
     */
    public DiffusedMaterial(String filename){
        this.texture = new Texture(new ImageTexture(filename));
    }

    //lampe 111 für weiss
    @Override
    public Color emission(Ray ray, Hit hit) {
        return Vector.black;
    }

    // farbe fürs objekt
    @Override
    public Color albedo(Ray ray, Hit hit) {
        return texture.getColor(hit.texture().x(), hit.texture().y());
    }

    /**
     * @param ray the ray
     * @param hit the hit
     * @return the scattered ray
     */
    @Override
    public Ray scattered(Ray ray, Hit hit) {
        double x = Random.random() * 2 - 1;
        double y = Random.random() * 2 - 1;
        double z = Random.random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = Random.random() * 2 - 1;
            y = Random.random() * 2 - 1;
            z = Random.random() * 2 - 1;
        }
        Direction scatteredDirection = Vector.normalize(Vector.add(hit.normalenvektor(), Vector.direction(x, y, z)));
        return new Ray(hit.trefferpunktX(), scatteredDirection, 0.000001, Double.POSITIVE_INFINITY);
    }

}
