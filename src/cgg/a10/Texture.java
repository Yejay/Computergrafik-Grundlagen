package cgg.a10;

import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Sampler;

public class Texture implements Sampler {
    Sampler texture;
    Color color;

    public Texture(ImageTexture texture) {
        this.texture = texture;
    }

    public Texture(Sampler texture){
        this.texture = texture;
    }

    @Override
    public Color getColor(double u, double v) {
        color = texture.getColor(u, v);
        double r = Math.pow(color.r(), 2.2);
        double g = Math.pow(color.g(), 2.2);
        double b = Math.pow(color.b(), 2.2);

        return new Color(r, g, b);

    }

}
