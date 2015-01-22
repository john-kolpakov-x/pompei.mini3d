package pompei.mini3d;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Kulanno {
  
  public static void main(String[] args) throws Exception {
    int width = 800;
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
    
    {
      bi.setRGB(0, 0, width, height, rgbArray, 0, scansize);
      
      ImageIO.write(bi, "png", new File("build/kulanno.png"));
      
      System.out.println("Hi");
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
