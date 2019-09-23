package collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class ArrayListTest {

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        Field capacity = List.class.getDeclaredField("size");
        capacity.setAccessible(true);

        List<String> list = Lists.newArrayListWithCapacity(10);
        System.out.println(capacity.get(list));
    }
}
