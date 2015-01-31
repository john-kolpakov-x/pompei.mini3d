package pompei.mini3d;

import static pompei.mini3d.ColorUtil.rgbToInt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.SecureRandom;
import java.util.Random;

import javax.imageio.ImageIO;

import pompei.mini3d.mat.Mat4;
import pompei.mini3d.tri.TriDraw;

public class RotatingAfricanHead {
  
  private static int colors[];
  
  public static void main(String[] args) throws Exception {
    Model3D model = new Model3D();
    model.loadFromUrl(RotatingAfricanHead.class.getResource("african_head.obj"));
    
    Random rnd = new SecureRandom();
    colors = new int[model.faceCount];
    for (int i = 0, C = colors.length; i < C; i++) {
      colors[i] = ColorUtil.rgbToInt(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
    
    Model3D m = model.copy();
    
    for (int i = 0; i < 36; i++) {
      draw(m, i);
    }
    
    System.out.println("OK");
  }
  
  private static void draw(Model3D model, int rotIndex) throws Exception {
    {
      Mat4 u = new Mat4().setRotateZ(Math.toRadians(10 * rotIndex));
      double v[] = model.vertices;
      for (int i = 0, C = model.verticesCount; i < C; i++) {
        u.mul3(v, 3 * i, v, 3 * i);
      }
    }
    {
      Mat4 u = new Mat4().setPngMatrix();
      double v[] = model.vertices;
      for (int i = 0, C = model.verticesCount; i < C; i++) {
        u.mul3(v, 3 * i, v, 3 * i);
      }
    }
    
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
    
    TriDraw td = new TriDraw(pointBuffer);
    
    for (int i = 0; i < model.faceCount; i++) {
      int index1 = model.faces[3 * i + 0];
      int index2 = model.faces[3 * i + 1];
      int index3 = model.faces[3 * i + 2];
      
      td.x1 = model.vertices[3 * (index1 - 1) + 0];
      td.y1 = model.vertices[3 * (index1 - 1) + 1];
      td.z1 = model.vertices[3 * (index1 - 1) + 2];
      
      td.x2 = model.vertices[3 * (index2 - 1) + 0];
      td.y2 = model.vertices[3 * (index2 - 1) + 1];
      td.z2 = model.vertices[3 * (index2 - 1) + 2];
      
      td.x3 = model.vertices[3 * (index3 - 1) + 0];
      td.y3 = model.vertices[3 * (index3 - 1) + 1];
      td.z3 = model.vertices[3 * (index3 - 1) + 2];
      
      td.color = colors[i];
      
      td.draw();
    }
    
    {
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      bi.setRGB(0, 0, width, height, rgbArray, 0, scansize);
      String s = "" + rotIndex;
      while (s.length() < 3) {
        s = "0" + s;
      }
      String fname = "build/DrawAfricanHead_" + s + ".png";
      ImageIO.write(bi, "png", new File(fname));
      System.out.println("saved " + fname);
    }
  }
}
