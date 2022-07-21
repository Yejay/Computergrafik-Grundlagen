package cgg.a12;

import cgtools.Color;

public interface Light {
    public Color incomingIntensity(Hit hit, Shape scene);
    
}
