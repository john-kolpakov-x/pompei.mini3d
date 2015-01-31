package pompei.mini3d;

import static java.lang.System.arraycopy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Model3D {
  
  public int verticesCount, faceCount;
  
  public double[] vertices;
  public int[] faces;
  
  public Model3D copy() {
    Model3D ret = new Model3D();
    ret.verticesCount = verticesCount;
    ret.faceCount = faceCount;
    
    ret.vertices = new double[vertices.length];
    arraycopy(vertices, 0, ret.vertices, 0, vertices.length);
    
    ret.faces = new int[faces.length];
    arraycopy(faces, 0, ret.faces, 0, faces.length);
    
    return ret;
  }
  
  public void loadFromUrl(URL resourceUrl) throws Exception {
    int vCount = 0, fCount = 0;
    {
      BufferedReader br = new BufferedReader(new InputStreamReader(resourceUrl.openStream(),
          "UTF-8"));
      try {
        while (true) {
          String line = br.readLine();
          if (line == null) break;
          if (line.startsWith("v ")) vCount++;
          if (line.startsWith("f ")) fCount++;
        }
      } finally {
        br.close();
      }
    }
    
    double v[] = new double[3 * vCount];
    int f[] = new int[3 * fCount];
    {
      BufferedReader br = new BufferedReader(new InputStreamReader(resourceUrl.openStream(),
          "UTF-8"));
      int vi = 0;
      int fi = 0;
      try {
        while (true) {
          String line = br.readLine();
          if (line == null) break;
          if (line.startsWith("v ")) {
            String[] split = line.split("\\s+");
            v[vi + 0] = Double.parseDouble(split[1]);
            v[vi + 1] = Double.parseDouble(split[2]);
            v[vi + 2] = Double.parseDouble(split[3]);
            vi += 3;
            continue;
          }
          if (line.startsWith("f ")) {
            String[] split = line.split("\\s+");
            f[fi + 0] = Integer.parseInt(split[1].split("/")[0]);
            f[fi + 1] = Integer.parseInt(split[2].split("/")[0]);
            f[fi + 2] = Integer.parseInt(split[3].split("/")[0]);
            fi += 3;
          }
        }
      } finally {
        br.close();
      }
      
      verticesCount = vCount;
      faceCount = fCount;
      vertices = v;
      faces = f;
    }
  }
}
