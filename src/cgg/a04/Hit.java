package cgg.a04;

import cgtools.*;

// dem Strahlparameter t, 
// der Position des Trefferpunkts x, 
// dem Normalenvektor n und 
// der Farbe der Oberfläche c im Trefferpunkt
public record Hit(double t, Point x, Direction n, Color c) {
    
}
