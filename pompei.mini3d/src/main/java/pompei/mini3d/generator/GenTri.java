package pompei.mini3d.generator;

import java.io.File;
import java.io.PrintStream;

public class GenTri {
  
  public String src;
  public boolean normals = false;
  
  private PrintStream out;
  
  private String className;
  
  public void run() throws Exception {
    className = "Tri";
    if (normals) className += "Normal";
    className += "Draw";
    
    File outFile = new File(src + "/pompei/mini3d/tri/" + className + ".java");
    outFile.getParentFile().mkdirs();
    
    out = new PrintStream(outFile, "UTF-8");
    try {
      printAll();
    } finally {
      out.close();
    }
  }
  
  private void printAll() {
    out.println("package pompei.mini3d.tri;");
    out.println();
    out.println("import static java.lang.Math.ceil;");
    out.println("import pompei.mini3d.PointBuffer;");
    out.println();
    out.println("public final class " + className + " {");
    out.println("");
    out.println("  public double x1, y1, z1;");
    out.println("  public double x2, y2, z2;");
    out.println("  public double x3, y3, z3;");
    out.println("  ");
    if (normals) {
      out.println("  public double nx1, ny1, nz1;");
      out.println("  public double nx2, ny2, nz2;");
      out.println("  public double nx3, ny3, nz3;");
      out.println("  ");
    }
    out.println("  public int color;");
    out.println("  ");
    out.println("  private PointBuffer pointBuffer;");
    out.println("  ");
    out.println("  public " + className + "(PointBuffer pointBuffer) {");
    out.println("    this.pointBuffer = pointBuffer;");
    out.println("  }");
    out.println("  ");
    
    printMethodDraw();
    
    out.println("  ");
    out.println("  private final static double DEEP_SIZE = 2 * (double)Integer.MAX_VALUE - 1;");
    out.println("  ");
    
    printMethodDrawTri();
    
    out.println("}");
    out.println("");
  }
  
  private void printMethodDraw() {
    out.println("  public final void draw() {");
    out.println("    {");
    out.println("      double tmp;");
    out.println("      if (x1 > x2) {");
    out.println("        tmp = x1;x1 = x2;x2 = tmp;");
    out.println("        tmp = y1;y1 = y2;y2 = tmp;");
    out.println("        tmp = z1;z1 = z2;z2 = tmp;");
    if (normals) {
      out.println("        ");
      out.println("        tmp = nx1;nx1 = nx2;nx2 = tmp;");
      out.println("        tmp = ny1;ny1 = ny2;ny2 = tmp;");
      out.println("        tmp = nz1;nz1 = nz2;nz2 = tmp;");
    }
    out.println("      }");
    out.println("      boolean swap = false;");
    out.println("      if (x2 > x3) {");
    out.println("        swap = true;");
    out.println("        ");
    out.println("        tmp = x2;x2 = x3;x3 = tmp;");
    out.println("        tmp = y2;y2 = y3;y3 = tmp;");
    out.println("        tmp = z2;z2 = z3;z3 = tmp;");
    if (normals) {
      out.println("        ");
      out.println("        tmp = nx2;nx2 = nx3;nx3 = tmp;");
      out.println("        tmp = ny2;ny2 = ny3;ny3 = tmp;");
      out.println("        tmp = nz2;nz2 = nz3;nz3 = tmp;");
    }
    out.println("      }");
    out.println("      if (swap && x1 > x2) {");
    out.println("        tmp = x1;x1 = x2;x2 = tmp;");
    out.println("        tmp = y1;y1 = y2;y2 = tmp;");
    out.println("        tmp = z1;z1 = z2;z2 = tmp;");
    if (normals) {
      out.println("        ");
      out.println("        tmp = nx1;nx1 = nx2;nx2 = tmp;");
      out.println("        tmp = ny1;ny1 = ny2;ny2 = tmp;");
      out.println("        tmp = nz1;nz1 = nz2;nz2 = tmp;");
    }
    out.println("      }");
    out.println("    }");
    out.println("    ");
    out.println("    if (x3 == x1) return;");
    out.println("    double K = (x2 - x1) / (x3 - x1);");
    out.println("    ");
    out.println("    double y4 = y1 + (y3 - y1) * K;");
    out.println("    double z4 = z1 + (z3 - z1) * K;");
    out.println("    ");
    
    out.print("    drawTra(x1, x2, y1, y2, y1, y4, z1, z2, z1, z4");
    out.println(");");
    
    out.print("    drawTra(x2, x3, y2, y3, y4, y3, z2, z3, z4, z3");
    out.println(");");
    
    out.println("    ");
    out.println("  }");
  }
  
  private void printMethodDrawTri() {
    out.print("  private final void drawTra(");
    {
      out.print("  double xA , double xB ");
      out.print(", double yA1, double yB1");
      out.print(", double yA2, double yB2");
      out.print(", double zA1, double zB1");
      out.print(", double zA2, double zB2");
      out.print(") {");
      out.println();
    }
    if (normals) {
      out.println("    ");
      out.println("    double nx1=this.nx1, ny1=this.ny1, nz1=this.nz1;");
      out.println("    double nx2=this.nx2, ny2=this.ny2, nz2=this.nz2;");
      out.println("    double nx3=this.nx3, ny3=this.ny3, nz3=this.nz3;");
    }
    out.println("    ");
    out.println("    if (yB1 > yB2 || yA1 > yA2) {");
    out.println("      double tmp;");
    out.println("      ");
    out.println("      tmp=yB1; yB1=yB2; yB2=tmp;");
    out.println("      tmp=zB1; zB1=zB2; zB2=tmp;");
    out.println("      tmp=yA1; yA1=yA2; yA2=tmp;");
    out.println("      tmp=zA1; zA1=zA2; zA2=tmp;");
    out.println("    }");
    out.println("    ");
    out.println("    double xi = ceil(xA * pointBuffer.width) / pointBuffer.width;");
    out.println("    double xStep = 1.0 / pointBuffer.width;");
    out.println("    double yStep = 1.0 / pointBuffer.height;");
    out.println("    ");
    out.println("    double deltaX = xB - xA;");
    out.println("    double Ky1 = (yB1 - yA1) / deltaX;");
    out.println("    double Ky2 = (yB2 - yA2) / deltaX;");
    out.println("    double Kz1 = (zB1 - zA1) / deltaX;");
    out.println("    double Kz2 = (zB2 - zA2) / deltaX;");
    out.println("    ");
    out.println("    int scansize = this.pointBuffer.scansize;");
    out.println("    int[] screen = this.pointBuffer.screen;");
    out.println("    int color = this.color;");
    out.println("    ");
    out.println("    double zBack = pointBuffer.zBack;");
    out.println("    double zFace = pointBuffer.zFace;");
    out.println("    Object[] syncBuffer = pointBuffer.syncBuffer;");
    out.println("    int[] buffer = pointBuffer.buffer;");
    out.println("    ");
    out.println("    double DEEP_K = DEEP_SIZE / (zBack - zFace);");
    out.println("    ");
    out.println("    OUT: for (; xi < xB; xi += xStep) {");
    out.println("      ");
    out.println("      if (xi < 0) continue OUT;");
    out.println("      if (xi > 1) continue OUT;");
    out.println("      ");
    out.println("      double startX = xi - xA;");
    out.println("      ");
    out.println("      double yFROM = yA1 + Ky1 * startX;");
    out.println("      double yEND = yA2 + Ky2 * startX + 0.1 * yStep;");
    out.println("      ");
    out.println("      double z1 = zA1 + Kz1 * startX;");
    out.println("      double z2 = zA2 + Kz2 * startX;");
    out.println("      ");
    out.println("      double yi = ceil(yFROM * pointBuffer.height) / pointBuffer.height;");
    out.println("      ");
    out.println("      double Kzy = (z2 - z1) / (yEND - yFROM);");
    out.println("      ");
    out.println("      IN: for (; yi < yEND; yi += yStep) {");
    out.println("        ");
    out.println("        if (yi < 0) continue IN;");
    out.println("        if (yi > 1) continue IN;");
    out.println("        ");
    out.println("        int X = (int)(xi * pointBuffer.width + 0.2);");
    out.println("        int Y = (int)(yi * pointBuffer.height + 0.2);");
    out.println("        ");
    out.println("        double zi = z1 + (yi - yFROM) * Kzy;");
    out.println("        ");
    out.println("        if (zi < zFace) continue IN;");
    out.println("        if (zi > zBack) continue IN;");
    out.println("        ");
    out.println("        int deep = (int)((double)Integer.MIN_VALUE + (zi - zFace) * DEEP_K + 0.5);");
    out.println("        ");
    out.println("        //point {xi, yi, zi}");
    out.println("        ");
    out.println("        int pos = X + Y * scansize;");
    out.println("        ");
    out.println("        synchronized (syncBuffer[pos]) {");
    out.println("          if (buffer[pos] < deep) continue IN;");
    out.println("          buffer[pos] = deep;");
    out.println("          ");
    out.println("          screen[pos] = color;");
    out.println("        }");
    out.println("        ");
    out.println("      }");
    out.println("      ");
    out.println("    }");
    out.println("  }");
  }
  
}
