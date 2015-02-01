package pompei.mini3d.probe;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import pompei.mini3d.PointBuffer;
import pompei.mini3d.tri.TriColorDraw;

public class ProbeManyTriesInManyThreads {
  
  public static void main(String[] args) throws Exception {
    int width = 1200;
    int height = 600;
    
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    
    int scansize = width;
    
    int[] rgbArray = new int[width * height];
    
    for (int i = 0, C = rgbArray.length; i < C; i++) {
      rgbArray[i] = rgbToInt(100, 100, 100);
    }
    
    for (int x = 10; x < 100; x++) {
      for (int y = 10; y < 100; y++) {
        int pos = x + y * scansize;
        int color = rgbToInt(0, 0, 150);
        
        rgbArray[pos] = color;
      }
    }
    
    {
      int color = rgbToInt(255, 255, 255);
      for (int x = 0; x < width; x++) {
        rgbArray[x + 0 * scansize] = color;
        rgbArray[x + (height - 1) * scansize] = color;
      }
      
      for (int y = 0; y < height; y++) {
        rgbArray[0 + y * scansize] = color;
        rgbArray[(width - 1) + y * scansize] = color;
      }
    }
    
    final PointBuffer pointBuffer = new PointBuffer();
    pointBuffer.screen = rgbArray;
    pointBuffer.width = width;
    pointBuffer.height = height;
    pointBuffer.scansize = scansize;
    
    pointBuffer.initDeepBuffer();
    
    Thread[] threads = new Thread[8];
    for (int i = 0, C = threads.length; i < C; i++) {
      threads[i] = new Thread(new Runnable() {
        @Override
        public void run() {
          TriColorDraw td = new TriColorDraw(pointBuffer);
          
          for (int k = 0; k < 1000; k++) {
            td.x1 = 0.25;
            td.y1 = 0.625;
            td.z1 = 0.1;
            
            td.x2 = 0.75;
            td.y2 = 0.125;
            td.z2 = 0.1;
            
            td.x3 = 0.875;
            td.y3 = 0.75;
            td.z3 = 0.1;
            
            td.color = rgbToInt(0, 255, 0);
            
            td.draw();
            
            td.x1 = 0.25;
            td.y1 = 0.625;
            td.z1 = 0.1;
            
            td.x2 = 0.5;
            td.y2 = 1;
            td.z2 = 0.1;
            
            td.x3 = 0.875;
            td.y3 = 0.75;
            td.z3 = 0.1;
            
            td.color = rgbToInt(0, 200, 0);
            
            td.draw();
          }
        }
      });
    }
    
    long startedAt = System.currentTimeMillis();
    
    for (Thread t : threads) {
      t.start();
      System.out.println("Started " + t);
    }
    for (Thread t : threads) {
      t.join();
      System.out.println("Finished " + t);
    }
    
    long time = System.currentTimeMillis() - startedAt;
    
    {
      bi.setRGB(0, 0, width, height, rgbArray, 0, scansize);
      
      ImageIO.write(bi, "png", new File("build/kulanno.png"));
      
      System.out.println("OK for " + time);
    }
  }
  
  private static int rgbToInt(int r, int g, int b) {
    return 0 //
        | ((r & 0xFF) << (2 * 8)) //
        | ((g & 0xFF) << (1 * 8)) //
        | ((b & 0xFF) << (0 * 8)) //
        | ((0xFF) << (3 * 8));
  }
}
