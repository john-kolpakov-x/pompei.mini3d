package pompei.mini3d;

import static pompei.mini3d.ColorUtil.rgbToInt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.SecureRandom;
import java.util.Random;

import javax.imageio.ImageIO;

public class DrawAfricanHead {
  public static void main(String[] args) throws Exception {
    Model3D m = new Model3D();
    m.loadFromUrl(DrawAfricanHead.class.getResource("african_head.obj"));
    
    int width = 600;
    int height = 600;
    
    int scansize = width;
    
    int[] rgbArray = new int[width * height];
    
    for (int i = 0, C = rgbArray.length; i < C; i++) {
      rgbArray[i] = rgbToInt(100, 100, 100);
    }
    
    final PointBuffer pointBuffer = new PointBuffer();
    pointBuffer.screen = rgbArray;
    pointBuffer.width = width;
    pointBuffer.height = height;
    pointBuffer.scansize = scansize;
    
    pointBuffer.initDeepBuffer(0, +1);
    
    TreeDraw td = new TreeDraw(pointBuffer);
    
    Random rnd = new SecureRandom();
    
    for (int i = 0; i < m.faceCount; i++) {
      int index1 = m.faces[3 * i + 0];
      int index2 = m.faces[3 * i + 1];
      int index3 = m.faces[3 * i + 2];
      
      td.x1 = m.vertices[3 * (index1 - 1) + 0];
      td.y1 = m.vertices[3 * (index1 - 1) + 1];
      td.z1 = m.vertices[3 * (index1 - 1) + 2];
      
      td.x2 = m.vertices[3 * (index2 - 1) + 0];
      td.y2 = m.vertices[3 * (index2 - 1) + 1];
      td.z2 = m.vertices[3 * (index2 - 1) + 2];
      
      td.x3 = m.vertices[3 * (index3 - 1) + 0];
      td.y3 = m.vertices[3 * (index3 - 1) + 1];
      td.z3 = m.vertices[3 * (index3 - 1) + 2];
      
      td.color = ColorUtil.rgbToInt(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
      
      td.draw();
    }
    
    {
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      bi.setRGB(0, 0, width, height, rgbArray, 0, scansize);
      String fname = "build/DrawAfricanHead.png";
      ImageIO.write(bi, "png", new File(fname));
    }
    
    System.out.println("OK");
  }
}
