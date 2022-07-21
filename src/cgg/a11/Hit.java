package cgg.a11;

import cgtools.*;

// dem Strahlparameter t, 
// der Position des Trefferpunkts x, 
// dem Normalenvektor n und 
// der Farbe der Oberfläche c im Trefferpunkt
public record Hit(double strahlparameterT, Point trefferpunktX, Direction normalenvektor, Material material, Direction texture) {
    
}
