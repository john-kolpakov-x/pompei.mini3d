package pompei.mini3d.tri;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import pompei.mini3d.PointBuffer;

/**
 * Draws tri
 * <p>
 * Autogenerated by class pompei.mini3d.generator.GenTri
 * </p>
 * <p>
 * At 2015-02-17T21:53:45.710+0600
 * </p>
 */
public final class TriTextureDraw {
  
  public double x1, y1, z1;
  public double x2, y2, z2;
  public double x3, y3, z3;
  
  public double u1, v1;
  public double u2, v2;
  public double u3, v3;
  
  public int texture[];
  public int textureWidth, textureHeight;
  
  public int color;
  
  private PointBuffer pointBuffer;
  
  public TriTextureDraw(PointBuffer pointBuffer) {
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
        
        tmp = u1;
        u1 = u2;
        u2 = tmp;
        tmp = v1;
        v1 = v2;
        v2 = tmp;
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
        
        tmp = u2;
        u2 = u3;
        u3 = tmp;
        tmp = v2;
        v2 = v3;
        v3 = tmp;
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
        
        tmp = u1;
        u1 = u2;
        u2 = tmp;
        tmp = v1;
        v1 = v2;
        v2 = tmp;
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
    
    double x1 = this.x1, y1 = this.y1, z1 = this.z1;
    double x2 = this.x2, y2 = this.y2, z2 = this.z2;
    double x3 = this.x3, y3 = this.y3, z3 = this.z3;
    
    double u1 = this.u1, v1 = this.v1;
    double u2 = this.u2, v2 = this.v2;
    double u3 = this.u3, v3 = this.v3;
    
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
    
    double zBack = pointBuffer.zBack;
    double zFace = pointBuffer.zFace;
    Object[] syncBuffer = pointBuffer.syncBuffer;
    int[] buffer = pointBuffer.buffer;
    
    double Co = ((x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1) + (z2 - z1) * (z3 - z1))
        / sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1))
        / sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1) + (z3 - z1) * (z3 - z1));
    double Si2 = 1 - Co * Co;
    double V12_2 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1);
    double V13_2 = (x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1) + (z3 - z1) * (z3 - z1);
    
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
        
        double A12 = ((xi - x1) * (x2 - x1) + (yi - y1) * (y2 - y1) + (zi - z1) * (z2 - z1))
            / V12_2;
        double A13 = ((xi - x1) * (x3 - x1) + (yi - y1) * (y3 - y1) + (zi - z1) * (z3 - z1))
            / V13_2;
        
        double a12 = (A12 - A13 * Co) / Si2;
        double a13 = (A13 - A12 * Co) / Si2;
        
        double u = u1 + (u2 - u1) * a12 + (u3 - u1) * a13;
        double v = v1 + (v2 - v1) * a12 + (v3 - v1) * a13;
        
        //texture {u, v}
        int textureColor = textureColor(u, v);
        
        int pos = X + Y * scansize;
        
        synchronized (syncBuffer[pos]) {
          if (buffer[pos] < deep) continue IN;
          buffer[pos] = deep;
          
          screen[pos] = textureColor;
        }
        
      }
      
    }
  }
  
  private int textureColor(double u, double v) {
    
    if (u < -1 || u > +1 || v < -1 || v > +1) {
      return color;
    }
    
    double uu = (u + 1) / 2;
    double vv = (1 - v) / 2;
    
    double U = uu * textureWidth;
    double V = vv * textureHeight;
    
    int U11 = (int)U;
    int V11 = (int)V;
    
    if (U11 >= textureWidth) return color;
    if (V11 >= textureHeight) return color;
    
    int U12 = U11 + 1;
    int V12 = V11;
    
    int U21 = U11;
    int V21 = V11 + 1;
    
    int U22 = U11 + 1;
    int V22 = V11 + 1;
    
    if (U22 >= textureWidth) {
      U22 = U21 = U12 = U11;
    }
    
    if (V22 >= textureHeight) {
      V22 = V21 = V12 = V11;
    }
    
    int c11 = texture[U11 + V11 * textureWidth];
    int c12 = texture[U12 + V12 * textureWidth];
    int c21 = texture[U21 + V21 * textureWidth];
    int c22 = texture[U22 + V22 * textureWidth];
    
    int r11 = (c11 & 0xFF0000) >> 16;
    int g11 = (c11 & 0xFF00) >> 8;
    int b11 = (c11 & 0xFF);
    
    int r12 = (c12 & 0xFF0000) >> 16;
    int g12 = (c12 & 0xFF00) >> 8;
    int b12 = (c12 & 0xFF);
    
    int r21 = (c21 & 0xFF0000) >> 16;
    int g21 = (c21 & 0xFF00) >> 8;
    int b21 = (c21 & 0xFF);
    
    int r22 = (c22 & 0xFF0000) >> 16;
    int g22 = (c22 & 0xFF00) >> 8;
    int b22 = (c22 & 0xFF);
    
    int r = (int)((r11 + (r12 - r11) * (U - U11))
        + ((r21 + (r22 - r21) * (U - U11)) - (r11 + (r12 - r11) * (U - U11))) * (V - V11) + 0.5);
    int g = (int)((g11 + (g12 - g11) * (U - U11))
        + ((g21 + (g22 - g21) * (U - U11)) - (g11 + (g12 - g11) * (U - U11))) * (V - V11) + 0.5);
    int b = (int)((b11 + (b12 - b11) * (U - U11))
        + ((b21 + (b22 - b21) * (U - U11)) - (b11 + (b12 - b11) * (U - U11))) * (V - V11) + 0.5);
    
    return ((r & 0xFF) << (2 * 8)) | ((g & 0xFF) << (1 * 8)) | ((b & 0xFF) << (0 * 8))
        | ((0xFF) << (3 * 8));
    
  }
}
