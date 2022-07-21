package cgg.a11;

import cgtools.Color;
import cgtools.Sampler;

public class CheckerBoard implements Sampler {
    Color color1;
    Color color2;
    private int n = 0;

    /***
     * Constructor with changeable colors.
     * 
     * @param color1
     * @param color2
     * @param n
     */
    public CheckerBoard(Color color1, Color color2, int n) {
        this.color1 = color1;
        this.color2 = color2;
        this.n = n;
    }

    /***
     * Constructor with default colors white and black.
     * 
     * @param n
     */
    public CheckerBoard(int n) {
        this.n = n;
        this.color1 = new Color(1, 1, 1);
        this.color2 = new Color(0, 0, 0);
    }

    @Override
    public Color getColor(double u, double v) {
        double ui = (int) ((u % 1) * n);
        double vi = (int) ((v % 1) * n);
        if ((ui + vi) % 2 == 0) {
            return color1;
        } else {
            return color2;
        }
    }

  

}
