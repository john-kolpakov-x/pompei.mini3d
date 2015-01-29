package pompei.mini3d;

public class ColorUtil {

  public static int rgbToInt(int r, int g, int b) {
    return 0 //
        | ((r & 0xFF) << (2 * 8)) //
        | ((g & 0xFF) << (1 * 8)) //
        | ((b & 0xFF) << (0 * 8)) //
        | ((0xFF) << (3 * 8));
  }
  
}
