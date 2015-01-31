package pompei.mini3d.mat;

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
  
  public Mat4 fromThem() {
    double[] data = this.data;
    int i = this.idx0;
    
    data[i++] = 0.5; // 0
    data[i++] = 0; // 1
    data[i++] = 0; // 2
    data[i++] = 0.5; // 3
    data[i++] = 0; // 4
    data[i++] = -0.5; // 5
    data[i++] = 0; // 6
    data[i++] = 0.5; // 7
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
  
  public Mat4 identity() {
    double[] data = this.data;
    int i = this.idx0;
    
    data[i++] = 1; // 0
    data[i++] = 0; // 1
    data[i++] = 0; // 2
    data[i++] = 0; // 3
    data[i++] = 0; // 4
    data[i++] = 1; // 5
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
  
  public static String vecStr3(double v[], int idx0) {
    return "{" + v[idx0 + 0] + ", " + v[idx0 + 1] + ", " + v[idx0 + 2] + "}";
  }
  
  public static String vecStr4(double v[], int idx0) {
    return "{" + v[idx0 + 0] + ", " + v[idx0 + 1] + ", " + v[idx0 + 2] + ", " + v[idx0 + 3] + "}";
  }
}
