package pompei.mini3d;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Util {
  
  public static final double DEG = Math.PI / 180;
  
  public static String steamToStr(InputStream in) {
    if (in == null) return null;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte[] buf = new byte[1024 * 8];
    try {
      while (true) {
        int count = in.read(buf);
        if (count < 0) {
          in.close();
          return out.toString("UTF-8");
        }
        out.write(buf, 0, count);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static String fileToStr(File file) {
    if (!file.exists()) return null;
    try {
      return steamToStr(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static String fileToStr(String filename) {
    return fileToStr(new File(filename));
  }
}
