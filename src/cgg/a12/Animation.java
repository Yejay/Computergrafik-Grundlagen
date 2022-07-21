package cgg.a12;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import cgg.Image;

public class Animation {
    public static void render(
        double start, 
        double stop, 
        int fps, 
        World world, 
        Camera camera, 
        Image image, 
        int spp,
        String dir) {
            
        try {
            new ProcessBuilder("rm", "-r", dir).start().waitFor();
        } catch (IOException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        var frameTime = 1.0 / fps;
        var frameNumber = 0;

        for (double t = start; t <= stop; t += frameTime) {
            world.update(t);

            var raytracer = new Raytracer(camera, world);
            try {
                image.parallelization(raytracer, spp);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
            }

            var filename = String.format("%s/frame-%03d.png", dir, frameNumber++);
            image.write(filename);
        }

        String[] ffmpeg = {
                "ffmpeg",
                "-y",
                "-loglevel", "panic",
                "-r", Integer.valueOf(fps).toString(),
                "-start_number", "0",
                "-i", "frame-%03d.png",
                "-pix_fmt", "yuv420p",
                "-vcodec", "libx264",
                "-crf", "16",
                "-preset",
                "veryslow",
                "cgg-competition-ss-22-927077.mp4"
        };

        try {
            System.out.println(String.join(" ", ffmpeg));
            new ProcessBuilder(ffmpeg)
                    .directory(new File(dir))
                    .start()
                    .waitFor();
            new ProcessBuilder("C:\\Program Files (x86)\\K-Lite Codec Pack\\MPC-HC64\\mpc-hc64.exe", "cgg-competition-ss-22-927077.mp4")
                    .directory(new File(dir))
                    .start()
                    .waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
