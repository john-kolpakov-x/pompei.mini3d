package pompei.mini3d.generator;

public class GenTriLauncher {
  public static void main(String[] args) throws Exception {
    GenTri gen = new GenTri();
    
    gen.src = "src/main/java";
    gen.normals = false;
    gen.textureUV = true;
    gen.texture = true;
    
    gen.packageName = "pompei.mini3d.tri";
    gen.className = "ProbeTriDraw";
    
    gen.run();
    
    System.out.println("OK");
  }
}
