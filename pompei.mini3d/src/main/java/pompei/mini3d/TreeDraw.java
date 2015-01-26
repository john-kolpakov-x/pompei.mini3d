package pompei.mini3d;

import static java.lang.Math.ceil;

public class TreeDraw {
  
  public int width, height, scansize;
  public int[] screen;
  
  public double x1, y1, z1;
  public double x2, y2, z2;
  public double x3, y3, z3;
  
  public int color;
  
  public void draw() {
    {
      double tmp;
      if (x1 > x2) {
        tmp = x1;
        x1 = x2;
        x2 = tmp;
        
        tmp = y1;
        y1 = y2;
        y2 = tmp;
        
        tmp = z1;
        z1 = z2;
        z2 = tmp;
      }
      boolean swap = false;
      if (x2 > x3) {
        swap = true;
        
        tmp = x2;
        x2 = x3;
        x3 = tmp;
        
        tmp = y2;
        y2 = y3;
        y3 = tmp;
        
        tmp = z2;
        z2 = z3;
        z3 = tmp;
      }
      if (swap && x1 > x2) {
        tmp = x1;
        x1 = x2;
        x2 = tmp;
        
        tmp = y1;
        y1 = y2;
        y2 = tmp;
        
        tmp = z1;
        z1 = z2;
        z2 = tmp;
      }
    }
    
    if (x3 == x1) return;
    double K = (x2 - x1) / (x3 - x1);
    
    double y4 = y1 + (y3 - y1) * K;
    double z4 = z1 + (z3 - z1) * K;
    
    drawTra(x1, x2, y1, y2, y1, y4, z1, z2, z1, z4);
    drawTra(x2, x3, y2, y3, y4, y3, z2, z3, z4, z3);
  }
  
  private void drawTra(double xA, double xB, double yA1, double yB1, double yA2, double yB2,
      double zA1, double zB1, double zA2, double zB2) {
    double xi = ceil(xA * width) / width;
    double xStep = 1.0 / width;
    double yStep = 1.0 / height;
    
    double deltaX = xB - xA;
    double Ky1 = (yB1 - yA1) / deltaX;
    double Ky2 = (yB2 - yA2) / deltaX;
    double Kz1 = (zB1 - zA1) / deltaX;
    double Kz2 = (zB2 - zA2) / deltaX;
    
    int scansize = this.scansize;
    int[] screen = this.screen;
    int color = this.color;
    
    while (xi < xB) {
      
      double startX = xi - xA;
      
      double yFROM = yA1 + Ky1 * startX;
      double yEND = yA2 + Ky2 * startX;
      
      double z1 = zA1 + Kz1 * startX;
      double z2 = zA2 + Kz2 * startX;
      
      double yi = ceil(yFROM * height) / height;
      
      double Kzy = (z2 - z1) / (yEND - yFROM);
      
      while (yi < yEND) {
        
        int X = (int)(xi * width + 0.1);
        int Y = (int)(yi * height + 0.1);
        
        @SuppressWarnings("unused")
        double zi = z1 + (yi - yFROM) * Kzy;
        
        //point {xi, yi, zi}
        
        int pos = X + Y * scansize;
        
        screen[pos] = color;
        
        yi += yStep;
      }
      
      xi += xStep;
    }
  }
}
