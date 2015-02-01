package pompei.mini3d.tri;

import static java.lang.Math.ceil;
import pompei.mini3d.PointBuffer;

/**
 * Draws tri
 * <p>
 * Autogenerated by class pompei.mini3d.generator.GenTri
 * </p>
 * <p>
 * At 2015-02-01T12:49:33.703+0600
 * </p>
 */
public final class TriColorDraw {
  
  public double x1, y1, z1;
  public double x2, y2, z2;
  public double x3, y3, z3;
  
  public int color;
  
  private PointBuffer pointBuffer;
  
  public TriColorDraw(PointBuffer pointBuffer) {
    this.pointBuffer = pointBuffer;
  }
  
  private final static double DEEP_SIZE = 2 * (double)Integer.MAX_VALUE - 1;
  
  public final void draw() {
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
  
  private final void drawTra(double xA, double xB, double yA1, double yB1, double yA2, double yB2,
      double zA1, double zB1, double zA2, double zB2) {
    
    int width = pointBuffer.width;
    int height = pointBuffer.height;
    
    if (yB1 > yB2 || yA1 > yA2) {
      double tmp;
      
      tmp = yB1;
      yB1 = yB2;
      yB2 = tmp;
      tmp = zB1;
      zB1 = zB2;
      zB2 = tmp;
      tmp = yA1;
      yA1 = yA2;
      yA2 = tmp;
      tmp = zA1;
      zA1 = zA2;
      zA2 = tmp;
    }
    
    double xi = ceil(xA * width) / width;
    double xStep = 1.0 / width;
    double yStep = 1.0 / height;
    
    double deltaX = xB - xA;
    double Ky1 = (yB1 - yA1) / deltaX;
    double Ky2 = (yB2 - yA2) / deltaX;
    double Kz1 = (zB1 - zA1) / deltaX;
    double Kz2 = (zB2 - zA2) / deltaX;
    
    int scansize = this.pointBuffer.scansize;
    int[] screen = this.pointBuffer.screen;
    int color = this.color;
    
    double zBack = pointBuffer.zBack;
    double zFace = pointBuffer.zFace;
    Object[] syncBuffer = pointBuffer.syncBuffer;
    int[] buffer = pointBuffer.buffer;
    
    double DEEP_K = DEEP_SIZE / (zBack - zFace);
    
    OUT: for (; xi < xB; xi += xStep) {
      
      if (xi < -1) continue OUT;
      if (xi > +1) continue OUT;
      
      double startX = xi - xA;
      
      double yFROM = yA1 + Ky1 * startX;
      double yEND = yA2 + Ky2 * startX + 0.1 * yStep;
      
      double zi1 = zA1 + Kz1 * startX;
      double zi2 = zA2 + Kz2 * startX;
      
      double yi = ceil(yFROM * height) / height;
      
      double Kzy = (zi2 - zi1) / (yEND - yFROM);
      
      IN: for (; yi < yEND; yi += yStep) {
        
        if (yi < -1) continue IN;
        if (yi > +1) continue IN;
        
        int X = (int)((xi + 1) / 2 * width + 0.1);
        int Y = (int)((1 - yi) / 2 * height + 0.1);
        
        if (X >= width) continue IN;
        if (Y >= height) continue IN;
        
        double zi = zi1 + (yi - yFROM) * Kzy;
        
        if (zi < zFace) continue IN;
        if (zi > zBack) continue IN;
        
        int deep = (int)((double)Integer.MIN_VALUE + (zi - zFace) * DEEP_K + 0.5);
        
        //point {xi, yi, zi}
        
        int pos = X + Y * scansize;
        
        synchronized (syncBuffer[pos]) {
          if (buffer[pos] < deep) continue IN;
          buffer[pos] = deep;
          
          screen[pos] = color;
        }
        
      }
      
    }
  }
}