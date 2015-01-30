package pompei.mini3d.tri;

import static java.lang.Math.ceil;
import pompei.mini3d.PointBuffer;

public final class TriNormalDraw {
  
  public double x1, y1, z1;
  public double x2, y2, z2;
  public double x3, y3, z3;
  
  public double nx1, ny1, nz1;
  public double nx2, ny2, nz2;
  public double nx3, ny3, nz3;
  
  public int color;
  
  private PointBuffer pointBuffer;
  
  public TriNormalDraw(PointBuffer pointBuffer) {
    this.pointBuffer = pointBuffer;
  }
  
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
        
        tmp = nx1;
        nx1 = nx2;
        nx2 = tmp;
        tmp = ny1;
        ny1 = ny2;
        ny2 = tmp;
        tmp = nz1;
        nz1 = nz2;
        nz2 = tmp;
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
        
        tmp = nx2;
        nx2 = nx3;
        nx3 = tmp;
        tmp = ny2;
        ny2 = ny3;
        ny3 = tmp;
        tmp = nz2;
        nz2 = nz3;
        nz3 = tmp;
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
        
        tmp = nx1;
        nx1 = nx2;
        nx2 = tmp;
        tmp = ny1;
        ny1 = ny2;
        ny2 = tmp;
        tmp = nz1;
        nz1 = nz2;
        nz2 = tmp;
      }
    }
    
    if (x3 == x1) return;
    double K = (x2 - x1) / (x3 - x1);
    
    double y4 = y1 + (y3 - y1) * K;
    double z4 = z1 + (z3 - z1) * K;
    
    drawTra(x1, x2, y1, y2, y1, y4, z1, z2, z1, z4);
    drawTra(x2, x3, y2, y3, y4, y3, z2, z3, z4, z3);
    
  }
  
  private final static double DEEP_SIZE = 2 * (double)Integer.MAX_VALUE - 1;
  
  @SuppressWarnings("unused")
  private final void drawTra(double xA, double xB, double yA1, double yB1, double yA2, double yB2,
      double zA1, double zB1, double zA2, double zB2) {
    
    double nx1 = this.nx1, ny1 = this.ny1, nz1 = this.nz1;
    double nx2 = this.nx2, ny2 = this.ny2, nz2 = this.nz2;
    double nx3 = this.nx3, ny3 = this.ny3, nz3 = this.nz3;
    
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
    
    double xi = ceil(xA * pointBuffer.width) / pointBuffer.width;
    double xStep = 1.0 / pointBuffer.width;
    double yStep = 1.0 / pointBuffer.height;
    
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
      
      if (xi < 0) continue OUT;
      if (xi > 1) continue OUT;
      
      double startX = xi - xA;
      
      double yFROM = yA1 + Ky1 * startX;
      double yEND = yA2 + Ky2 * startX + 0.1 * yStep;
      
      double z1 = zA1 + Kz1 * startX;
      double z2 = zA2 + Kz2 * startX;
      
      double yi = ceil(yFROM * pointBuffer.height) / pointBuffer.height;
      
      double Kzy = (z2 - z1) / (yEND - yFROM);
      
      IN: for (; yi < yEND; yi += yStep) {
        
        if (yi < 0) continue IN;
        if (yi > 1) continue IN;
        
        int X = (int)(xi * pointBuffer.width + 0.2);
        int Y = (int)(yi * pointBuffer.height + 0.2);
        
        double zi = z1 + (yi - yFROM) * Kzy;
        
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
