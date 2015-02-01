package pompei.mini3d;

public final class PointBuffer {
  public int width, height, scansize;
  public int[] screen;
  
  public final int zFace = -1, zBack = +1;
  public int[] buffer;
  public Object[] syncBuffer;
  
  public void initDeepBuffer() {
    
    buffer = new int[screen.length];
    syncBuffer = new Object[screen.length];
    for (int i = 0, C = buffer.length; i < C; i++) {
      buffer[i] = Integer.MAX_VALUE;
      syncBuffer[i] = new Object();
    }
  }
}
