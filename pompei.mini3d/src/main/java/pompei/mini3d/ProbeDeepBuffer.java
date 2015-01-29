package pompei.mini3d;

import static pompei.mini3d.ColorUtil.rgbToInt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProbeDeepBuffer {
  
  public static void main(String[] args) throws Exception {
    createFile(0);
    createFile(1);
    createFile(2);
    createFile(3);
    createFile(4);
    createFile(5);
    createFile(6);
    createFile(7);
    createFile(8);
    createFile(9);
    
    System.out.println("OK");
  }
  
  private static void createFile(int U) throws IOException {
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
    
    pointBuffer.initDeepBuffer(0, +1);
    
    TreeDraw td = new TreeDraw(pointBuffer);
    
    for (int k = 0; k < 1; k++) {
      td.x1 = 0.125;
      td.y1 = 0.625;
      td.z1 = 0.9;
      
      td.x2 = 0.75;
      td.y2 = 0.125;
      td.z2 = 0.1;
      
      td.x3 = 0.875;
      td.y3 = 0.75;
      td.z3 = 0.1;
      
      td.color = rgbToInt(0, 255, 0);
      
      td.draw();
      
      td.x1 = 0.125;
      td.y1 = 0.875 - U * 0.125 / 2.0;
      td.z1 = 0.1;
      
      td.x2 = 0.375;
      td.y2 = 0.125;
      td.z2 = 0.9;
      
      td.x3 = 0.875;
      td.y3 = 0.375;
      td.z3 = 0.7;
      
      td.color = rgbToInt(200, 0, 0);
      
      td.draw();
    }
    
    {
      bi.setRGB(0, 0, width, height, rgbArray, 0, scansize);
      
      String S = "" + U;
      while (S.length() < 2) {
        S = "0" + S;
      }
      String fname = "build/Probe05-" + S + ".png";
      ImageIO.write(bi, "png", new File(fname));
      
      System.out.println("Complete " + fname);
    }
  }
}
