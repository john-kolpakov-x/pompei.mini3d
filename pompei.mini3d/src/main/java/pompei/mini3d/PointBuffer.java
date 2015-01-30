package pompei.mini3d;

public final class PointBuffer {
  public int width, height, scansize;
  public int[] screen;
  
  public int zFace, zBack;
  public int[] buffer;
  public Object[] syncBuffer;
  
  public void initDeepBuffer(int zFace, int zBack) {
    this.zFace = zFace;
    this.zBack = zBack;
    
    buffer = new int[screen.length];
    syncBuffer = new Object[screen.length];
    for (int i = 0, C = buffer.length; i < C; i++) {
      buffer[i] = Integer.MAX_VALUE;
      syncBuffer[i] = new Object();
    }
  }
}
