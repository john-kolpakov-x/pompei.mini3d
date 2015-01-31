package pompei.mini3d.generator;

import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class GenTri {
  
  public String src;
  public String packageName;
  public String className;
  
  public boolean normals = false;
  public boolean textureUV = false;
  
  public boolean localising = true;
  
  private final Pool mainClass = new Pool();
  private final Pool methodDraw = new Pool();
  private final Pool methodDrawTri = new Pool();
  
  private final Set<String> imp = new HashSet<>();
  
  public void run() throws Exception {
    
    printMainClass();
    printMethodDraw();
    printMethodDrawTri();
    
    File outFile = new File(src + "/" + packageName.replaceAll("\\.", "/") + "/" + className
        + ".java");
    outFile.getParentFile().mkdirs();
    
    PrintStream out = new PrintStream(outFile, "UTF-8");
    try {
      out.println("package " + packageName + ";");
      out.println();
      for (String i : imp) {
        out.println("import " + i + ";");
      }
      out.println();
      out.print(mainClass.toString());
      out.println("  ");
      out.print(methodDraw.toString());
      out.println("  ");
      out.print(methodDrawTri.toString());
      out.println("}");
    } finally {
      out.close();
    }
  }
  
  private void printMainClass() {
    mainClass.prn("public final class " + className + " {");
    mainClass.prn("");
    mainClass.prn("  public double x1, y1, z1;");
    mainClass.prn("  public double x2, y2, z2;");
    mainClass.prn("  public double x3, y3, z3;");
    mainClass.prn("  ");
    if (normals) {
      mainClass.prn("  public double nx1, ny1, nz1;");
      mainClass.prn("  public double nx2, ny2, nz2;");
      mainClass.prn("  public double nx3, ny3, nz3;");
      mainClass.prn("  ");
    }
    if (textureUV) {
      mainClass.prn("  public double u1, v1;");
      mainClass.prn("  public double u2, v2;");
      mainClass.prn("  public double u3, v3;");
      mainClass.prn("  ");
    }
    mainClass.prn("  public int color;");
    mainClass.prn("  ");
    imp.add("pompei.mini3d.PointBuffer");
    mainClass.prn("  private PointBuffer pointBuffer;");
    mainClass.prn("  ");
    mainClass.prn("  public " + className + "(PointBuffer pointBuffer) {");
    mainClass.prn("    this.pointBuffer = pointBuffer;");
    mainClass.prn("  }");
    mainClass.prn("  ");
    
    mainClass.prn("  private final static double DEEP_SIZE"
        + " = 2 * (double)Integer.MAX_VALUE - 1;");
    mainClass.prn("  ");
    
  }
  
  private void printMethodDraw() {
    methodDraw.prn("  public final void draw() {");
    methodDraw.prn("    {");
    methodDraw.prn("      double tmp;");
    methodDraw.prn("      if (x1 > x2) {");
    methodDraw.prn("        tmp = x1;x1 = x2;x2 = tmp;");
    methodDraw.prn("        tmp = y1;y1 = y2;y2 = tmp;");
    methodDraw.prn("        tmp = z1;z1 = z2;z2 = tmp;");
    if (normals) {
      methodDraw.prn("        ");
      methodDraw.prn("        tmp = nx1;nx1 = nx2;nx2 = tmp;");
      methodDraw.prn("        tmp = ny1;ny1 = ny2;ny2 = tmp;");
      methodDraw.prn("        tmp = nz1;nz1 = nz2;nz2 = tmp;");
    }
    if (textureUV) {
      methodDraw.prn("        ");
      methodDraw.prn("        tmp = u1;u1 = u2;u2 = tmp;");
      methodDraw.prn("        tmp = v1;v1 = v2;v2 = tmp;");
    }
    methodDraw.prn("      }");
    methodDraw.prn("      boolean swap = false;");
    methodDraw.prn("      if (x2 > x3) {");
    methodDraw.prn("        swap = true;");
    methodDraw.prn("        ");
    methodDraw.prn("        tmp = x2;x2 = x3;x3 = tmp;");
    methodDraw.prn("        tmp = y2;y2 = y3;y3 = tmp;");
    methodDraw.prn("        tmp = z2;z2 = z3;z3 = tmp;");
    if (normals) {
      methodDraw.prn("        ");
      methodDraw.prn("        tmp = nx2;nx2 = nx3;nx3 = tmp;");
      methodDraw.prn("        tmp = ny2;ny2 = ny3;ny3 = tmp;");
      methodDraw.prn("        tmp = nz2;nz2 = nz3;nz3 = tmp;");
    }
    if (textureUV) {
      methodDraw.prn("        ");
      methodDraw.prn("        tmp = u2;u2 = u3;u3 = tmp;");
      methodDraw.prn("        tmp = v2;v2 = v3;v3 = tmp;");
    }
    methodDraw.prn("      }");
    methodDraw.prn("      if (swap && x1 > x2) {");
    methodDraw.prn("        tmp = x1;x1 = x2;x2 = tmp;");
    methodDraw.prn("        tmp = y1;y1 = y2;y2 = tmp;");
    methodDraw.prn("        tmp = z1;z1 = z2;z2 = tmp;");
    if (normals) {
      methodDraw.prn("        ");
      methodDraw.prn("        tmp = nx1;nx1 = nx2;nx2 = tmp;");
      methodDraw.prn("        tmp = ny1;ny1 = ny2;ny2 = tmp;");
      methodDraw.prn("        tmp = nz1;nz1 = nz2;nz2 = tmp;");
    }
    if (textureUV) {
      methodDraw.prn("        ");
      methodDraw.prn("        tmp = u1;u1 = u2;u2 = tmp;");
      methodDraw.prn("        tmp = v1;v1 = v2;v2 = tmp;");
    }
    methodDraw.prn("      }");
    methodDraw.prn("    }");
    methodDraw.prn("    ");
    methodDraw.prn("    if (x3 == x1) return;");
    methodDraw.prn("    double K = (x2 - x1) / (x3 - x1);");
    methodDraw.prn("    ");
    methodDraw.prn("    double y4 = y1 + (y3 - y1) * K;");
    methodDraw.prn("    double z4 = z1 + (z3 - z1) * K;");
    methodDraw.prn("    ");
    
    methodDraw.pr("    drawTra(x1, x2, y1, y2, y1, y4, z1, z2, z1, z4");
    methodDraw.prn(");");
    
    methodDraw.pr("    drawTra(x2, x3, y2, y3, y4, y3, z2, z3, z4, z3");
    methodDraw.prn(");");
    
    methodDraw.prn("    ");
    methodDraw.prn("  }");
  }
  
  private void printMethodDrawTri() {
    methodDrawTri.pr("  private final void drawTra(");
    {
      methodDrawTri.pr("  double xA , double xB ");
      methodDrawTri.pr(", double yA1, double yB1");
      methodDrawTri.pr(", double yA2, double yB2");
      methodDrawTri.pr(", double zA1, double zB1");
      methodDrawTri.pr(", double zA2, double zB2");
      methodDrawTri.pr(") {");
      methodDrawTri.prn();
    }
    if (localising) {
      methodDrawTri.prn("    ");
      methodDrawTri.prn("    double x1=this.x1, y1=this.y1, z1=this.z1;");
      methodDrawTri.prn("    double x2=this.x2, y2=this.y2, z2=this.z2;");
      methodDrawTri.prn("    double x3=this.x3, y3=this.y3, z3=this.z3;");
    }
    if (normals && localising) {
      methodDrawTri.prn("    ");
      methodDrawTri.prn("    double nx1=this.nx1, ny1=this.ny1, nz1=this.nz1;");
      methodDrawTri.prn("    double nx2=this.nx2, ny2=this.ny2, nz2=this.nz2;");
      methodDrawTri.prn("    double nx3=this.nx3, ny3=this.ny3, nz3=this.nz3;");
    }
    if (textureUV && localising) {
      methodDrawTri.prn("    ");
      methodDrawTri.prn("    double u1=this.u1, v1=this.v1;");
      methodDrawTri.prn("    double u2=this.u2, v2=this.v2;");
      methodDrawTri.prn("    double u3=this.u3, v3=this.v3;");
    }
    methodDrawTri.prn("    ");
    methodDrawTri.prn("    if (yB1 > yB2 || yA1 > yA2) {");
    methodDrawTri.prn("      double tmp;");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("      tmp=yB1; yB1=yB2; yB2=tmp;");
    methodDrawTri.prn("      tmp=zB1; zB1=zB2; zB2=tmp;");
    methodDrawTri.prn("      tmp=yA1; yA1=yA2; yA2=tmp;");
    methodDrawTri.prn("      tmp=zA1; zA1=zA2; zA2=tmp;");
    methodDrawTri.prn("    }");
    methodDrawTri.prn("    ");
    imp.add("static java.lang.Math.ceil");
    methodDrawTri.prn("    double xi = ceil(xA * pointBuffer.width) / pointBuffer.width;");
    methodDrawTri.prn("    double xStep = 1.0 / pointBuffer.width;");
    methodDrawTri.prn("    double yStep = 1.0 / pointBuffer.height;");
    methodDrawTri.prn("    ");
    methodDrawTri.prn("    double deltaX = xB - xA;");
    methodDrawTri.prn("    double Ky1 = (yB1 - yA1) / deltaX;");
    methodDrawTri.prn("    double Ky2 = (yB2 - yA2) / deltaX;");
    methodDrawTri.prn("    double Kz1 = (zB1 - zA1) / deltaX;");
    methodDrawTri.prn("    double Kz2 = (zB2 - zA2) / deltaX;");
    methodDrawTri.prn("    ");
    methodDrawTri.prn("    int scansize = this.pointBuffer.scansize;");
    methodDrawTri.prn("    int[] screen = this.pointBuffer.screen;");
    methodDrawTri.prn("    int color = this.color;");
    methodDrawTri.prn("    ");
    methodDrawTri.prn("    double zBack = pointBuffer.zBack;");
    methodDrawTri.prn("    double zFace = pointBuffer.zFace;");
    methodDrawTri.prn("    Object[] syncBuffer = pointBuffer.syncBuffer;");
    methodDrawTri.prn("    int[] buffer = pointBuffer.buffer;");
    methodDrawTri.prn("    ");
    if (normals || textureUV) {
      imp.add("static java.lang.Math.sqrt");
      methodDrawTri.prn("    double Co = " //
          + "((x2-x1)*(x3-x1) + (y2-y1)*(y3-y1) + (z2-z1)*(z3-z1))" //
          + "/" //
          + "sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1))"//
          + "/" //
          + "sqrt((x3-x1)*(x3-x1) + (y3-y1)*(y3-y1) + (z3-z1)*(z3-z1));"//
      );
      methodDrawTri.prn("    double Si2 = 1-Co*Co;");
      methodDrawTri.prn("    double V12_2 = (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1);");
      methodDrawTri.prn("    double V13_2 = (x3-x1)*(x3-x1) + (y3-y1)*(y3-y1) + (z3-z1)*(z3-z1);");
      methodDrawTri.prn("    ");
    }
    methodDrawTri.prn("    double DEEP_K = DEEP_SIZE / (zBack - zFace);");
    methodDrawTri.prn("    ");
    methodDrawTri.prn("    OUT: for (; xi < xB; xi += xStep) {");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("      if (xi < 0) continue OUT;");
    methodDrawTri.prn("      if (xi > 1) continue OUT;");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("      double startX = xi - xA;");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("      double yFROM = yA1 + Ky1 * startX;");
    methodDrawTri.prn("      double yEND = yA2 + Ky2 * startX + 0.1 * yStep;");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("      double zi1 = zA1 + Kz1 * startX;");
    methodDrawTri.prn("      double zi2 = zA2 + Kz2 * startX;");
    methodDrawTri.prn("      ");
    imp.add("static java.lang.Math.ceil");
    methodDrawTri.prn("      double yi = ceil(yFROM * pointBuffer.height)"
        + " / pointBuffer.height;");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("      double Kzy = (zi2 - zi1) / (yEND - yFROM);");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("      IN: for (; yi < yEND; yi += yStep) {");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        if (yi < 0) continue IN;");
    methodDrawTri.prn("        if (yi > 1) continue IN;");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        int X = (int)(xi * pointBuffer.width + 0.2);");
    methodDrawTri.prn("        int Y = (int)(yi * pointBuffer.height + 0.2);");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        double zi = zi1 + (yi - yFROM) * Kzy;");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        if (zi < zFace) continue IN;");
    methodDrawTri.prn("        if (zi > zBack) continue IN;");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        int deep = (int)"
        + "((double)Integer.MIN_VALUE + (zi - zFace) * DEEP_K + 0.5);");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        //point {xi, yi, zi}");
    if (normals) {
      methodDrawTri.prn("        ");
      methodDrawTri.prn("        double A12 = " //
          + "((xi-x1)*(x2-x1) + (yi-y1)*(y2-y1) + (zi-z1)*(z2-z1))/V12_2;");
      methodDrawTri.prn("        double A13 = "
          + "((xi-x1)*(x3-x1) + (yi-y1)*(y3-y1) + (zi-z1)*(z3-z1))/V13_2;");
      methodDrawTri.prn("        ");
      methodDrawTri.prn("        double a12 = (A12 - A13*Co)/Si2;");
      methodDrawTri.prn("        double a13 = (A13 - A12*Co)/Si2;");
      methodDrawTri.prn("        ");
      methodDrawTri.prn("        double Nx = nx1 + (nx2-nx1)*a12 + (nx3-nx1)*a13;");
      methodDrawTri.prn("        double Ny = ny1 + (ny2-ny1)*a12 + (ny3-ny1)*a13;");
      methodDrawTri.prn("        double Nz = nz1 + (nz2-nz1)*a12 + (nz3-nz1)*a13;");
      methodDrawTri.prn("        ");
      imp.add("static java.lang.Math.sqrt");
      methodDrawTri.prn("        double Nlen=sqrt(Nx*Nx + Ny*Ny + Nz*Nz);");
      methodDrawTri.prn("        ");
      methodDrawTri.prn("        double nx = Nx/Nlen;");
      methodDrawTri.prn("        double ny = Ny/Nlen;");
      methodDrawTri.prn("        double nz = Nz/Nlen;");
      methodDrawTri.prn("        ");
      methodDrawTri.prn("        //normal {nx, ny, nz}");
    }
    if (textureUV) {
      methodDrawTri.prn("        ");
      methodDrawTri.prn("        double u = u1 + (u2-u1)*a12 + (u3-u1)*a13;");
      methodDrawTri.prn("        double v = v1 + (v2-v1)*a12 + (v3-v1)*a13;");
      methodDrawTri.prn("        ");
      methodDrawTri.prn("        //texture {u, v}");
    }
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        int pos = X + Y * scansize;");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("        synchronized (syncBuffer[pos]) {");
    methodDrawTri.prn("          if (buffer[pos] < deep) continue IN;");
    methodDrawTri.prn("          buffer[pos] = deep;");
    methodDrawTri.prn("          ");
    methodDrawTri.prn("          screen[pos] = color;");
    methodDrawTri.prn("        }");
    methodDrawTri.prn("        ");
    methodDrawTri.prn("      }");
    methodDrawTri.prn("      ");
    methodDrawTri.prn("    }");
    methodDrawTri.prn("  }");
  }
  
}
