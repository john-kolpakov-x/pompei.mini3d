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
    mainClass.println("public final class " + className + " {");
    mainClass.println("");
    mainClass.println("  public double x1, y1, z1;");
    mainClass.println("  public double x2, y2, z2;");
    mainClass.println("  public double x3, y3, z3;");
    mainClass.println("  ");
    if (normals) {
      mainClass.println("  public double nx1, ny1, nz1;");
      mainClass.println("  public double nx2, ny2, nz2;");
      mainClass.println("  public double nx3, ny3, nz3;");
      mainClass.println("  ");
    }
    mainClass.println("  public int color;");
    mainClass.println("  ");
    imp.add("pompei.mini3d.PointBuffer");
    mainClass.println("  private PointBuffer pointBuffer;");
    mainClass.println("  ");
    mainClass.println("  public " + className + "(PointBuffer pointBuffer) {");
    mainClass.println("    this.pointBuffer = pointBuffer;");
    mainClass.println("  }");
    mainClass.println("  ");
    
    mainClass.println("  private final static double DEEP_SIZE"
        + " = 2 * (double)Integer.MAX_VALUE - 1;");
    mainClass.println("  ");
    
  }
  
  private void printMethodDraw() {
    methodDraw.println("  public final void draw() {");
    methodDraw.println("    {");
    methodDraw.println("      double tmp;");
    methodDraw.println("      if (x1 > x2) {");
    methodDraw.println("        tmp = x1;x1 = x2;x2 = tmp;");
    methodDraw.println("        tmp = y1;y1 = y2;y2 = tmp;");
    methodDraw.println("        tmp = z1;z1 = z2;z2 = tmp;");
    if (normals) {
      methodDraw.println("        ");
      methodDraw.println("        tmp = nx1;nx1 = nx2;nx2 = tmp;");
      methodDraw.println("        tmp = ny1;ny1 = ny2;ny2 = tmp;");
      methodDraw.println("        tmp = nz1;nz1 = nz2;nz2 = tmp;");
    }
    methodDraw.println("      }");
    methodDraw.println("      boolean swap = false;");
    methodDraw.println("      if (x2 > x3) {");
    methodDraw.println("        swap = true;");
    methodDraw.println("        ");
    methodDraw.println("        tmp = x2;x2 = x3;x3 = tmp;");
    methodDraw.println("        tmp = y2;y2 = y3;y3 = tmp;");
    methodDraw.println("        tmp = z2;z2 = z3;z3 = tmp;");
    if (normals) {
      methodDraw.println("        ");
      methodDraw.println("        tmp = nx2;nx2 = nx3;nx3 = tmp;");
      methodDraw.println("        tmp = ny2;ny2 = ny3;ny3 = tmp;");
      methodDraw.println("        tmp = nz2;nz2 = nz3;nz3 = tmp;");
    }
    methodDraw.println("      }");
    methodDraw.println("      if (swap && x1 > x2) {");
    methodDraw.println("        tmp = x1;x1 = x2;x2 = tmp;");
    methodDraw.println("        tmp = y1;y1 = y2;y2 = tmp;");
    methodDraw.println("        tmp = z1;z1 = z2;z2 = tmp;");
    if (normals) {
      methodDraw.println("        ");
      methodDraw.println("        tmp = nx1;nx1 = nx2;nx2 = tmp;");
      methodDraw.println("        tmp = ny1;ny1 = ny2;ny2 = tmp;");
      methodDraw.println("        tmp = nz1;nz1 = nz2;nz2 = tmp;");
    }
    methodDraw.println("      }");
    methodDraw.println("    }");
    methodDraw.println("    ");
    methodDraw.println("    if (x3 == x1) return;");
    methodDraw.println("    double K = (x2 - x1) / (x3 - x1);");
    methodDraw.println("    ");
    methodDraw.println("    double y4 = y1 + (y3 - y1) * K;");
    methodDraw.println("    double z4 = z1 + (z3 - z1) * K;");
    methodDraw.println("    ");
    
    methodDraw.print("    drawTra(x1, x2, y1, y2, y1, y4, z1, z2, z1, z4");
    methodDraw.println(");");
    
    methodDraw.print("    drawTra(x2, x3, y2, y3, y4, y3, z2, z3, z4, z3");
    methodDraw.println(");");
    
    methodDraw.println("    ");
    methodDraw.println("  }");
  }
  
  private void printMethodDrawTri() {
    methodDrawTri.print("  private final void drawTra(");
    {
      methodDrawTri.print("  double xA , double xB ");
      methodDrawTri.print(", double yA1, double yB1");
      methodDrawTri.print(", double yA2, double yB2");
      methodDrawTri.print(", double zA1, double zB1");
      methodDrawTri.print(", double zA2, double zB2");
      methodDrawTri.print(") {");
      methodDrawTri.println();
    }
    if (normals && localising) {
      methodDrawTri.println("    ");
      methodDrawTri.println("    double x1=this.x1, y1=this.y1, z1=this.z1;");
      methodDrawTri.println("    double x2=this.x2, y2=this.y2, z2=this.z2;");
      methodDrawTri.println("    double x3=this.x3, y3=this.y3, z3=this.z3;");
      methodDrawTri.println("    ");
      methodDrawTri.println("    double nx1=this.nx1, ny1=this.ny1, nz1=this.nz1;");
      methodDrawTri.println("    double nx2=this.nx2, ny2=this.ny2, nz2=this.nz2;");
      methodDrawTri.println("    double nx3=this.nx3, ny3=this.ny3, nz3=this.nz3;");
    }
    methodDrawTri.println("    ");
    methodDrawTri.println("    if (yB1 > yB2 || yA1 > yA2) {");
    methodDrawTri.println("      double tmp;");
    methodDrawTri.println("      ");
    methodDrawTri.println("      tmp=yB1; yB1=yB2; yB2=tmp;");
    methodDrawTri.println("      tmp=zB1; zB1=zB2; zB2=tmp;");
    methodDrawTri.println("      tmp=yA1; yA1=yA2; yA2=tmp;");
    methodDrawTri.println("      tmp=zA1; zA1=zA2; zA2=tmp;");
    methodDrawTri.println("    }");
    methodDrawTri.println("    ");
    imp.add("static java.lang.Math.ceil");
    methodDrawTri.println("    double xi = ceil(xA * pointBuffer.width) / pointBuffer.width;");
    methodDrawTri.println("    double xStep = 1.0 / pointBuffer.width;");
    methodDrawTri.println("    double yStep = 1.0 / pointBuffer.height;");
    methodDrawTri.println("    ");
    methodDrawTri.println("    double deltaX = xB - xA;");
    methodDrawTri.println("    double Ky1 = (yB1 - yA1) / deltaX;");
    methodDrawTri.println("    double Ky2 = (yB2 - yA2) / deltaX;");
    methodDrawTri.println("    double Kz1 = (zB1 - zA1) / deltaX;");
    methodDrawTri.println("    double Kz2 = (zB2 - zA2) / deltaX;");
    methodDrawTri.println("    ");
    methodDrawTri.println("    int scansize = this.pointBuffer.scansize;");
    methodDrawTri.println("    int[] screen = this.pointBuffer.screen;");
    methodDrawTri.println("    int color = this.color;");
    methodDrawTri.println("    ");
    methodDrawTri.println("    double zBack = pointBuffer.zBack;");
    methodDrawTri.println("    double zFace = pointBuffer.zFace;");
    methodDrawTri.println("    Object[] syncBuffer = pointBuffer.syncBuffer;");
    methodDrawTri.println("    int[] buffer = pointBuffer.buffer;");
    methodDrawTri.println("    ");
    methodDrawTri.println("    double DEEP_K = DEEP_SIZE / (zBack - zFace);");
    methodDrawTri.println("    ");
    methodDrawTri.println("    OUT: for (; xi < xB; xi += xStep) {");
    methodDrawTri.println("      ");
    methodDrawTri.println("      if (xi < 0) continue OUT;");
    methodDrawTri.println("      if (xi > 1) continue OUT;");
    methodDrawTri.println("      ");
    methodDrawTri.println("      double startX = xi - xA;");
    methodDrawTri.println("      ");
    methodDrawTri.println("      double yFROM = yA1 + Ky1 * startX;");
    methodDrawTri.println("      double yEND = yA2 + Ky2 * startX + 0.1 * yStep;");
    methodDrawTri.println("      ");
    methodDrawTri.println("      double zi1 = zA1 + Kz1 * startX;");
    methodDrawTri.println("      double zi2 = zA2 + Kz2 * startX;");
    methodDrawTri.println("      ");
    imp.add("static java.lang.Math.ceil");
    methodDrawTri.println("      double yi = ceil(yFROM * pointBuffer.height)"
        + " / pointBuffer.height;");
    methodDrawTri.println("      ");
    methodDrawTri.println("      double Kzy = (zi2 - zi1) / (yEND - yFROM);");
    methodDrawTri.println("      ");
    methodDrawTri.println("      IN: for (; yi < yEND; yi += yStep) {");
    methodDrawTri.println("        ");
    methodDrawTri.println("        if (yi < 0) continue IN;");
    methodDrawTri.println("        if (yi > 1) continue IN;");
    methodDrawTri.println("        ");
    methodDrawTri.println("        int X = (int)(xi * pointBuffer.width + 0.2);");
    methodDrawTri.println("        int Y = (int)(yi * pointBuffer.height + 0.2);");
    methodDrawTri.println("        ");
    methodDrawTri.println("        double zi = zi1 + (yi - yFROM) * Kzy;");
    methodDrawTri.println("        ");
    methodDrawTri.println("        if (zi < zFace) continue IN;");
    methodDrawTri.println("        if (zi > zBack) continue IN;");
    methodDrawTri.println("        ");
    methodDrawTri.println("        int deep = (int)"
        + "((double)Integer.MIN_VALUE + (zi - zFace) * DEEP_K + 0.5);");
    methodDrawTri.println("        ");
    methodDrawTri.println("        //point {xi, yi, zi}");
    if (normals) {
      methodDrawTri.println("        ");
      methodDrawTri.println("        double D1=(xi-x1)*(xi-x1)+(yi-y1)*(yi-y1)+(zi-z1)*(zi-z1);");
      methodDrawTri.println("        double D2=(xi-x2)*(xi-x2)+(yi-y2)*(yi-y2)+(zi-z2)*(zi-z2);");
      methodDrawTri.println("        double D3=(xi-x3)*(xi-x3)+(yi-y3)*(yi-y3)+(zi-z3)*(zi-z3);");
      methodDrawTri.println("        ");
      methodDrawTri.println("        double KD1=D2*D3;");
      methodDrawTri.println("        double KD2=D3*D1;");
      methodDrawTri.println("        double KD3=D1*D2;");
      methodDrawTri.println("        ");
      methodDrawTri.println("        double Nx = KD1*nx1 + KD2*nx2 + KD3*nx3;");
      methodDrawTri.println("        double Ny = KD1*ny1 + KD2*ny2 + KD3*ny3;");
      methodDrawTri.println("        double Nz = KD1*nz1 + KD2*nz2 + KD3*nz3;");
      methodDrawTri.println("        ");
      imp.add("static java.lang.Math.sqrt");
      methodDrawTri.println("        double Nlen=sqrt(Nx*Nx + Ny*Ny + Nz*Nz);");
      methodDrawTri.println("        ");
      methodDrawTri.println("        double nx = Nx/Nlen;");
      methodDrawTri.println("        double ny = Ny/Nlen;");
      methodDrawTri.println("        double nz = Nz/Nlen;");
      methodDrawTri.println("        ");
      methodDrawTri.println("        //normal {nx, ny, nz}");
    }
    methodDrawTri.println("        ");
    methodDrawTri.println("        int pos = X + Y * scansize;");
    methodDrawTri.println("        ");
    methodDrawTri.println("        synchronized (syncBuffer[pos]) {");
    methodDrawTri.println("          if (buffer[pos] < deep) continue IN;");
    methodDrawTri.println("          buffer[pos] = deep;");
    methodDrawTri.println("          ");
    methodDrawTri.println("          screen[pos] = color;");
    methodDrawTri.println("        }");
    methodDrawTri.println("        ");
    methodDrawTri.println("      }");
    methodDrawTri.println("      ");
    methodDrawTri.println("    }");
    methodDrawTri.println("  }");
  }
  
}
