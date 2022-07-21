/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;


import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A simple image writer that takes an array of pixel components and the image
 * size and writes the corresponding image in 16-bit PNG format with a linear
 * color space to the provided location.
 */
public class ImageWriter {

  public static void write(String filename, double[] data, int width, int height) {
    try {
      write(filename, data, width, height, true);
    } catch (IOException error) {
      System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
      System.exit(1);
    }
  }

  private ImageWriter() {
  }

  private static void write(String filename, double[] data, int width, int height, boolean linear) throws IOException {
    // setup an sRGB image with 16-bit components of the right size.
    int cs = ColorSpace.CS_sRGB;
    if (linear)
      cs = ColorSpace.CS_LINEAR_RGB;
    ComponentColorModel ccm = new ComponentColorModel(ColorSpace.getInstance(cs), false, false,
        ComponentColorModel.OPAQUE, DataBuffer.TYPE_USHORT);

    WritableRaster raster = Raster.createBandedRaster(DataBuffer.TYPE_USHORT, width, height, 3, null);
    BufferedImage image = new BufferedImage(ccm, raster, false, null);

    for (int y = 0; y != height; y++) {
      for (int x = 0; x != width; x++) {
        int i = (width * y + x) * 3;
        int[] rgb = { (int) (clamp(data[i + 0]) * 65535.0), (int) (clamp(data[i + 1]) * 65535.0),
            (int) (clamp(data[i + 2]) * 65535.0) };
        raster.setPixel(x, y, rgb);
      }
    }
    // This fails on Windows across drive boundaries.
    // File outputfile = File.createTempFile("image-", ".png");
    // ImageIO.write(image, "png", outputfile);
    // Files.move(Paths.get(outputfile.getAbsolutePath()), Paths.get(filename),
    // REPLACE_EXISTING);
    File file = new File(filename);
    file.getParentFile().mkdirs();
    ImageIO.write(image, "png", file);
  }

  private static double clamp(double v) {
    return Math.min(Math.max(0, v), 1);
  }
}
