package pompei.mini3d.probe;

import static pompei.mini3d.ColorUtil.rgbToInt;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import pompei.mini3d.ColorUtil;
import pompei.mini3d.PointBuffer;
import pompei.mini3d.model.Model3D;
import pompei.mini3d.model.TextureModel;
import pompei.mini3d.tri.TriTextureDraw;

public class DrawAfricanHeadWithTexture {
  public static void main(String[] args) throws Exception {
    Model3D m = new Model3D();
    m.loadFromUrl(DrawAfricanHeadWithTexture.class.getResource("african_head.obj"));
    
    final TextureModel texture;
    {
      BufferedImage image = ImageIO.read(DrawAfricanHeadWithTexture.class
          .getResource("african_head_diffuse.png"));
      texture = TextureModel.from(image);
    }
    
    int width = 600, height = 600;
    
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
    
    pointBuffer.initDeepBuffer();
    
    TriTextureDraw td = new TriTextureDraw(pointBuffer);
    td.texture = texture.data;
    td.textureWidth = texture.width;
    td.textureHeight = texture.height;
    
    td.color = ColorUtil.rgbToInt(0, 0, 0);
    
    for (int i = 0; i < m.faceCount; i++) {
      {
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
      }
      
      {
        int index1 = m.uvIndexes[3 * i + 0];
        int index2 = m.uvIndexes[3 * i + 1];
        int index3 = m.uvIndexes[3 * i + 2];
        
        td.u1 = m.uv[3 * (index1 - 1) + 0];
        td.v1 = m.uv[3 * (index1 - 1) + 1];
        
        td.u2 = m.uv[3 * (index2 - 1) + 0];
        td.v2 = m.uv[3 * (index2 - 1) + 1];
        
        td.u3 = m.uv[3 * (index3 - 1) + 0];
        td.v3 = m.uv[3 * (index3 - 1) + 1];
      }
      
      td.draw();
    }
    
    {
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      bi.setRGB(0, 0, width, height, rgbArray, 0, scansize);
      String fname = "build/DrawAfricanHeadWithTexture.png";
      ImageIO.write(bi, "png", new File(fname));
    }
    
    System.out.println("OK");
  }
}
