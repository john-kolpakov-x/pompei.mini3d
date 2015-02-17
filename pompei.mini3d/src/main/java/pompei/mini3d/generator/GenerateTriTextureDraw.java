package pompei.mini3d.generator;

public class GenerateTriTextureDraw {
  public static void main(String[] args) throws Exception {
    GenTri gen = new GenTri();
    
    gen.src = "src/main/java";
    gen.normals = false;
    gen.textureUV = true;
    gen.texture = true;
    gen.koorTypeUV = KoorTypeUV.TYPE1;
    
    gen.packageName = "pompei.mini3d.tri";
    gen.className = "TriTextureDraw";
    
    gen.run();
    
    System.out.println("OK");
  }
}
