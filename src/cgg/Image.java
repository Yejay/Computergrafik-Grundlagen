/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cgg.a09.OnePixel;
import cgtools.*;

public class Image {

  double[] canvas;
  int width;
  int height;

  // Constructor
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    canvas = new double[width * height * 3];
  }

  // 2.3 Gammakorrektur
  public void setPixel(int x, int y, Color color) {
    int index = (y * width + x) * 3;
    canvas[index] = Math.pow(color.r(), 1 / 2.2);
    canvas[index + 1] = Math.pow(color.g(), 1 / 2.2);
    canvas[index + 2] = Math.pow(color.b(), 1 / 2.2);
  }

  public void write(String filename) {
    ImageWriter.write(filename, canvas, width, height);
  }

  // TODO: Implement this method
  public void sample(Sampler s) {
    System.out.println("STATUS: Loading image please wait...");
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {

        setPixel(x, y, s.getColor(x, y));
      }
    }
  }

  public void sample(Sampler raytracer, int n) {
    System.out.println("STATUS: Loading image please wait...");
    // Prozentcounter
    double ins = width * height;
    double count = 0;
    double status = 0;
    int percent = 0;
    //
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        Color color = new Color(0, 0, 0);
        for (int xi = 0; xi < n; xi++) {
          for (int yi = 0; yi < n; yi++) {
            double rx = Random.random();
            double ry = Random.random();
            double xs = x + (xi + rx) / n;
            double ys = y + (yi + ry) / n;
            color = Vector.add(color, raytracer.getColor(xs, ys));
          }
        }
        count += 1;
        percent = (int) (count / ins * 100);
        if (status < percent) {
          status = percent;
          System.out.println("STATUS: " + status + "%");
        }
        setPixel(x, y, Vector.divide(color, n * n));
      }
    }

  }

  public void parallelization(Sampler raytracer, int n) throws InterruptedException, ExecutionException {
    System.out.println("STATUS: Loading image please wait...");
    // Prozentcounter
    double canvasSize = width * height;
    double count = 0;
    double status = 0;
    int percent = 0;
    //
    int cores = Runtime.getRuntime().availableProcessors();
    System.out.println("STATUS: You have the power of " + cores + " cores on your side.");
    ExecutorService pool = Executors.newFixedThreadPool(cores);
    List<Future<Color>> pixels = new ArrayList<>();
    System.out.println("STATUS: Filling pool please wait...");
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        pixels.add(pool.submit(new OnePixel(raytracer, x, y, n)));
      }
    }
    System.out.println("STATUS: Pool filled.");

    System.out.println("STATUS: Setting color please wait...");
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Prozentcounter
        count += 1;
        percent = (int) (count / canvasSize * 100);
        if (status < percent) {
          status = percent;
          System.out.println("STATUS: " + status + "%");
        }
        //
        Color color = pixels.get(x * height + y).get();
        // Future<Color> color = pixels.remove(0);
        setPixel(x, y, color);
      }
    }
    System.out.println("STATUS: Color set.");
    pool.shutdown();
  }

}
