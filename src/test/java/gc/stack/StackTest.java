package gc.stack;

import org.junit.Test;

public class StackTest {

    private int count = 0;

    public void recursion(){
        count++;
        recursion();
    }

    @Test
    public void stackTest1(){
        try {
            recursion();
        } catch (Throwable e){
            System.out.println("栈的深度为：" + count);
            e.printStackTrace();
        }
    }
}
