package gc.gc;

import org.junit.Test;

import java.util.Vector;

public class MaxHeapTest {

    @Test
    public void maxHeapTest(){
        Vector v = new Vector<>();
        for(int i = 0; i < 10; i++){
            byte[] b = new byte[2 << 20];
            v.add(b);
            if(v.size() == 3){
                v.clear();
            }
        }
    }
}
