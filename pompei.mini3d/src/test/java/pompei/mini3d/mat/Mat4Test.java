package pompei.mini3d.mat;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.Test;

public class Mat4Test {
  
  private void pr(String s) {
    //System.out.println(s);
  }
  
  @Test
  public void fromThem_001() throws Exception {
    Mat4 m = new Mat4().setPngMatrix();
    
    double v[] = new double[] { 0, 0, 0, 0 };
    m.mul3(v, 0, v, 0);
    pr(Mat4.vecStr3(v, 0));
    assertThat(Mat4.vecStr3(v, 0)).isEqualTo("{0.5, 0.5, 0.0}");
  }
  
  @Test
  public void fromThem_002() throws Exception {
    Mat4 m = new Mat4().setPngMatrix();
    
    double v[] = new double[] { 1, 0, 0, 0 };
    m.mul3(v, 0, v, 0);
    pr(Mat4.vecStr3(v, 0));
    assertThat(Mat4.vecStr3(v, 0)).isEqualTo("{1.0, 0.5, 0.0}");
  }
  
  @Test
  public void fromThem_003() throws Exception {
    Mat4 m = new Mat4().setPngMatrix();
    
    double v[] = new double[] { -1, 0, 0, 0 };
    m.mul3(v, 0, v, 0);
    pr(Mat4.vecStr3(v, 0));
    assertThat(Mat4.vecStr3(v, 0)).isEqualTo("{0.0, 0.5, 0.0}");
  }
  
  @Test
  public void fromThem_004() throws Exception {
    Mat4 m = new Mat4().setPngMatrix();
    
    double v[] = new double[] { 0, 1, 0, 0 };
    m.mul3(v, 0, v, 0);
    pr(Mat4.vecStr3(v, 0));
    assertThat(Mat4.vecStr3(v, 0)).isEqualTo("{0.5, 0.0, 0.0}");
  }
  
  @Test
  public void fromThem_005() throws Exception {
    Mat4 m = new Mat4().setPngMatrix();
    
    double v[] = new double[] { 0, -1, 0, 0 };
    m.mul3(v, 0, v, 0);
    pr(Mat4.vecStr3(v, 0));
    assertThat(Mat4.vecStr3(v, 0)).isEqualTo("{0.5, 1.0, 0.0}");
  }
}
