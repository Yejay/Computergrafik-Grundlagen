/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import static cgtools.Vector.*;
import cgg.*;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;
    final int radius = 16;
    
    
    // This class instance defines the contents of the image.
    ConstantColor content = new ConstantColor(gray);
    CircleColor content2 = new CircleColor(red, width, height, radius);
    PolkadotsColor content3 = new PolkadotsColor(blue, width, height, radius);
    
    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    Image image2 = new Image(width, height);
    Image image3 = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
        image2.setPixel(x, y, content2.getColor(x, y));
        image3.setPixel(x, y, content3.getColor(x, y));
      }
    }
    
    // Write the image to disk.
    final String filename = "doc/a01-image.png";
    final String filename2 = "doc/a01-disc.png";
    final String filename3 = "doc/a01-polka-dots.png";
    image.write(filename);
    image2.write(filename2);
    image3.write(filename3);
    System.out.println("Wrote image: " + filename);
    System.out.println("Wrote image: " + filename2);
    System.out.println("Wrote image: " + filename3);
  }
}
