package pompei.mini3d.generator;

public class GenerateTriDraw {
  public static void main(String[] args) throws Exception {
    GenTri gen = new GenTri();
    
    gen.src = "src/main/java";
    gen.normals = false;
    gen.textureUV = false;
    
    gen.packageName = "pompei.mini3d.tri";
    gen.className = "TriDraw";
    
    gen.run();
    
    System.out.println("OK");
  }
}
