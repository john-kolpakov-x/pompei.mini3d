package pompei.mini3d;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Kulanno {
  
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
    
    long startedAt = System.currentTimeMillis();
    
    {
      TreeDraw td = new TreeDraw();
      td.screen = rgbArray;
      td.width = width;
      td.height = height;
      td.scansize = scansize;
      
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
    }
    
    {
      bi.setRGB(0, 0, width, height, rgbArray, 0, scansize);
      
      ImageIO.write(bi, "png", new File("build/kulanno.png"));
      
      System.out.println("OK for " + (System.currentTimeMillis() - startedAt));
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
