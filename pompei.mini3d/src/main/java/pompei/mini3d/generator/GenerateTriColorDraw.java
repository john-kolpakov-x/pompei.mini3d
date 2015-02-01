package pompei.mini3d.generator;

public class GenerateTriColorDraw {
  public static void main(String[] args) throws Exception {
    GenTri gen = new GenTri();
    
    gen.src = "src/main/java";
    gen.normals = false;
    gen.textureUV = false;
    
    gen.packageName = "pompei.mini3d.tri";
    gen.className = "TriColorDraw";
    
    gen.run();
    
    System.out.println("OK");
  }
}
