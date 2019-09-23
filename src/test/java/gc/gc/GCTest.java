package gc.gc;

import org.junit.Test;

public class GCTest {

    @Test
    public void gcTest1(){
        byte[] b1 = new byte[1024 * 1024 / 2];
        byte[] b2 = new byte[1024 * 1024 / 8];
        b2 = null;
        b2 = new byte[1027 * 1024 * 8];
        System.gc();
    }
}
