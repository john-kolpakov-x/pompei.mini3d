package pompei.mini3d.model;

import java.awt.image.BufferedImage;

public class TextureModel {
  public int[] data;
  public int width, height;
  
  public static TextureModel from(BufferedImage image) {
    TextureModel ret = new TextureModel();
    ret.readFrom(image);
    return ret;
  }
  
  private void readFrom(BufferedImage image) {
    width = image.getWidth();
    height = image.getHeight();
    data = new int[width * height];
    image.getRGB(0, 0, width, height, data, 0, width);
  }
  
  @Override
  public String toString() {
    return getClass().getSimpleName() + ", size: " + width + "x" + height + ", data:" + data;
  }
}
