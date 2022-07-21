package cgg.a11;

import cgtools.*;

public interface Material {
    /**
     * Emissision how much light is emitted by the material
     * 
     * @param r - Ray
     * @param h - Hit
     * @return Color - Color of the material at the hit point of the ray
     */
    Color emission(Ray ray, Hit hit);

    /**
     * Albedo(= Weiße) (Maß für Rückstrahlvermögen, Reflexionsstrahlung) - proportion of light reflected by the material 
     * 
     * @param r - Ray
     * @param h - Hit
     * @return Color - Color of the material at the hit point of the ray
     */
    Color albedo(Ray ray, Hit hit);

    /**
     * Scattered ray from the material - the ray and its direction that is scattered from the material
     * 
     * @param r - Ray
     * @param h - Hit
     * @return Ray - Ray that is scattered from the hit point of the ray
     */
    Ray scattered(Ray ray, Hit hit);
}
