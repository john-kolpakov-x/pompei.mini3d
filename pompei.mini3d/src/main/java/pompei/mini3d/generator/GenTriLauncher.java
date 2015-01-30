package pompei.mini3d.generator;

public class GenTriLauncher {
  public static void main(String[] args) throws Exception {
    GenTri gen = new GenTri();
    
    gen.src = "src/main/java";
    gen.normals = true;
    
    gen.run();
    
    System.out.println("OK");
  }
}
