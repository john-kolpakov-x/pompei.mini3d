package pompei.mini3d.mat;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Матрица 4x4. Значения лежат в массиве <code>data</code> в позициях: <br>
 * <code>data[idx0 + 0], data[idx0 + 1], ..., data[idx0 + 15]</code>.
 * 
 * @author pompei
 *
 */
public class Mat4 {
  public double data[];
  public int idx0;
  
  public Mat4() {
    data = new double[16];
    idx0 = 0;
  }
  
  public Mat4(double data[], int idx0) {
    this.data = data;
    this.idx0 = idx0;
  }
  
  /**
   * Умножает матрицу на трёхмерный вектор и получает трёхмерный вектор <br>
   * Результат может совпадать с аргументом
   * 
   * @param res
   *          тут хранится результат
   * @param resIdx0
   *          смещение результата
   * @param a
   *          тут хранится операнд
   * @param aIdx0
   *          смещение операнда
   */
  public void mul3(double[] res, int resIdx0, double[] a, int aIdx0) {
    double[] data = this.data;
    int idx0 = this.idx0;
    
    double a0 = a[aIdx0 + 0];
    double a1 = a[aIdx0 + 1];
    double a2 = a[aIdx0 + 2];
    
    double r0 = data[idx0 + 0] * a0 + data[idx0 + 1] * a1 + data[idx0 + 2] * a2 + data[idx0 + 3];
    double r1 = data[idx0 + 4] * a0 + data[idx0 + 5] * a1 + data[idx0 + 6] * a2 + data[idx0 + 7];
    double r2 = data[idx0 + 8] * a0 + data[idx0 + 9] * a1 + data[idx0 + 10] * a2 + data[idx0 + 11];
    
    res[resIdx0 + 0] = r0;
    res[resIdx0 + 1] = r1;
    res[resIdx0 + 2] = r2;
  }
  
  /**
   * Умножает матрицу на четырёхмерный вектор и получает четырёхмерный вектор <br>
   * Результат может совпадать с аргументом
   * 
   * @param res
   *          тут хранится результат
   * @param resIdx0
   *          смещение результата
   * @param a
   *          тут хранится операнд
   * @param aIdx0
   *          смещение операнда
   */
  public void mul4(double[] res, int resIdx0, double[] a, int aIdx0) {
    double[] data = this.data;
    int idx0 = this.idx0;
    
    double a0 = a[aIdx0 + 0];
    double a1 = a[aIdx0 + 1];
    double a2 = a[aIdx0 + 2];
    double a3 = a[aIdx0 + 3];
    
    double r0 //
    = data[idx0 + 0] * a0 + data[idx0 + 1] * a1 + data[idx0 + 2] * a2 + data[idx0 + 3] * a3;
    double r1 //
    = data[idx0 + 4] * a0 + data[idx0 + 5] * a1 + data[idx0 + 6] * a2 + data[idx0 + 7] * a3;
    double r2 //
    = data[idx0 + 8] * a0 + data[idx0 + 9] * a1 + data[idx0 + 10] * a2 + data[idx0 + 11] * a3;
    double r3 //
    = data[idx0 + 12] * a0 + data[idx0 + 13] * a1 + data[idx0 + 14] * a2 + data[idx0 + 15] * a3;
    
    res[resIdx0 + 0] = r0;
    res[resIdx0 + 1] = r1;
    res[resIdx0 + 2] = r2;
    res[resIdx0 + 3] = r3;
  }
  
  public Mat4 setIdentity() {
    double[] data = this.data;
    int i = this.idx0;
    
    data[i++] = 1; //  0
    data[i++] = 0; //  1
    data[i++] = 0; //  2
    data[i++] = 0; //  3
    data[i++] = 0; //  4
    data[i++] = 1; //  5
    data[i++] = 0; //  6
    data[i++] = 0; //  7
    data[i++] = 0; //  8
    data[i++] = 0; //  9
    data[i++] = 1; // 10
    data[i++] = 0; // 11
    data[i++] = 0; // 12
    data[i++] = 0; // 13
    data[i++] = 0; // 14
    data[i++] = 1; // 15
    
    return this;
  }
  
  /**
   * Производит перемножение матриц: R = A * B <br>
   * Матрицы могут совпадать
   * 
   * @param R
   *          результирующая матрица
   * @param A
   *          первый аргумент
   * @param B
   *          второй аргумент
   */
  public static void mul(Mat4 R, Mat4 A, Mat4 B) {
    double[] a = A.data;
    int i = A.idx0;
    
    double a00 = a[i++];
    double a01 = a[i++];
    double a02 = a[i++];
    double a03 = a[i++];
    double a10 = a[i++];
    double a11 = a[i++];
    double a12 = a[i++];
    double a13 = a[i++];
    double a20 = a[i++];
    double a21 = a[i++];
    double a22 = a[i++];
    double a23 = a[i++];
    double a30 = a[i++];
    double a31 = a[i++];
    double a32 = a[i++];
    double a33 = a[i];
    
    double[] b = B.data;
    i = B.idx0;
    
    double b00 = b[i++];
    double b01 = b[i++];
    double b02 = b[i++];
    double b03 = b[i++];
    double b10 = b[i++];
    double b11 = b[i++];
    double b12 = b[i++];
    double b13 = b[i++];
    double b20 = b[i++];
    double b21 = b[i++];
    double b22 = b[i++];
    double b23 = b[i++];
    double b30 = b[i++];
    double b31 = b[i++];
    double b32 = b[i++];
    double b33 = b[i];
    
    double r00 = a00 * b00 + a01 * b10 + a02 * b20 + a03 * b30;
    double r01 = a00 * b01 + a01 * b11 + a02 * b21 + a03 * b31;
    double r02 = a00 * b02 + a01 * b12 + a02 * b22 + a03 * b32;
    double r03 = a00 * b03 + a01 * b13 + a02 * b23 + a03 * b33;
    
    double r10 = a10 * b00 + a11 * b10 + a12 * b20 + a13 * b30;
    double r11 = a10 * b01 + a11 * b11 + a12 * b21 + a13 * b31;
    double r12 = a10 * b02 + a11 * b12 + a12 * b22 + a13 * b32;
    double r13 = a10 * b03 + a11 * b13 + a12 * b23 + a13 * b33;
    
    double r20 = a20 * b00 + a21 * b10 + a22 * b20 + a23 * b30;
    double r21 = a20 * b01 + a21 * b11 + a22 * b21 + a23 * b31;
    double r22 = a20 * b02 + a21 * b12 + a22 * b22 + a23 * b32;
    double r23 = a20 * b03 + a21 * b13 + a22 * b23 + a23 * b33;
    
    double r30 = a30 * b00 + a31 * b10 + a32 * b20 + a33 * b30;
    double r31 = a30 * b01 + a31 * b11 + a32 * b21 + a33 * b31;
    double r32 = a30 * b02 + a31 * b12 + a32 * b22 + a33 * b32;
    double r33 = a30 * b03 + a31 * b13 + a32 * b23 + a33 * b33;
    
    double[] r = R.data;
    i = R.idx0;
    
    r[i++] = r00;
    r[i++] = r01;
    r[i++] = r02;
    r[i++] = r03;
    r[i++] = r10;
    r[i++] = r11;
    r[i++] = r12;
    r[i++] = r13;
    r[i++] = r20;
    r[i++] = r21;
    r[i++] = r22;
    r[i++] = r23;
    r[i++] = r30;
    r[i++] = r31;
    r[i++] = r32;
    r[i] = r33;
  }
  
  public static String vecStr3(double v[], int idx0) {
    return "{" + v[idx0 + 0] + ", " + v[idx0 + 1] + ", " + v[idx0 + 2] + "}";
  }
  
  public static String vecStr4(double v[], int idx0) {
    return "{" + v[idx0 + 0] + ", " + v[idx0 + 1] + ", " + v[idx0 + 2] + ", " + v[idx0 + 3] + "}";
  }
  
  public Mat4 setRotateX(double alfa) {
    double[] data = this.data;
    int i = this.idx0;
    
    data[i++] = 1; // 0
    data[i++] = 0; // 1
    data[i++] = 0; // 2
    data[i++] = 0; // 3
    data[i++] = 0; // 4
    data[i++] = cos(alfa); // 5
    data[i++] = -sin(alfa); // 6
    data[i++] = 0; // 7
    data[i++] = 0; // 8
    data[i++] = sin(alfa); // 9
    data[i++] = cos(alfa); //10
    data[i++] = 0; //11
    data[i++] = 0; //12
    data[i++] = 0; //13
    data[i++] = 0; //14
    data[i] = 1; //15
    
    return this;
  }
  
  public Mat4 setRotateY(double alfa) {
    double[] data = this.data;
    int i = this.idx0;
    
    data[i++] = cos(alfa); // 0
    data[i++] = 0; // 1
    data[i++] = sin(alfa); // 2
    data[i++] = 0; // 3
    
    data[i++] = 0; // 4
    data[i++] = 1; // 5
    data[i++] = 0; // 6
    data[i++] = 0; // 7
    
    data[i++] = -sin(alfa); // 8
    data[i++] = 0; // 9
    data[i++] = cos(alfa); //10
    data[i++] = 0; //11
    
    data[i++] = 0; //12
    data[i++] = 0; //13
    data[i++] = 0; //14
    data[i++] = 1; //15
    
    return this;
  }
  
  public Mat4 setRotateZ(double alfa) {
    
    double[] data = this.data;
    int i = this.idx0;
    
    data[i++] = cos(alfa); // 0
    data[i++] = -sin(alfa); // 1
    data[i++] = 0; // 2
    data[i++] = 0; // 3
    
    data[i++] = sin(alfa); // 4
    data[i++] = cos(alfa); // 5
    data[i++] = 0; // 6
    data[i++] = 0; // 7
    
    data[i++] = 0; // 8
    data[i++] = 0; // 9
    data[i++] = 1; //10
    data[i++] = 0; //11
    
    data[i++] = 0; //12
    data[i++] = 0; //13
    data[i++] = 0; //14
    data[i++] = 1; //15
    
    return this;
  }
  
  @Override
  public String toString() {
    
    StringBuilder sb = new StringBuilder();
    
    sb.append('|').append(pa(0)).append('|').append(pa(1));
    sb.append('|').append(pa(2)).append('|').append(pa(3));
    sb.append("|\n");
    sb.append('|').append(pa(4)).append('|').append(pa(5));
    sb.append('|').append(pa(6)).append('|').append(pa(7));
    sb.append("|\n");
    sb.append('|').append(pa(8)).append('|').append(pa(9));
    sb.append('|').append(pa(10)).append('|').append(pa(11));
    sb.append("|\n");
    sb.append('|').append(pa(12)).append('|').append(pa(13));
    sb.append('|').append(pa(14)).append('|').append(pa(15));
    sb.append("|\n");
    
    return sb.toString();
  }
  
  private String pa(int i) {
    double v = data[idx0 + i];
    return String.format("% 10.4f ", v);
  }
  
}
