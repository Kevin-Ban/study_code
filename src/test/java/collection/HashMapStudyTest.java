package collection;

import com.google.common.collect.Maps;
import com.google.common.reflect.Reflection;
import com.sun.deploy.util.ReflectionUtil;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class HashMapStudyTest {

    @Test
    public void test1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 通过反射调用HashMap的私有方法
        Class clazz = HashMap.class;
        Method capacity = clazz.getDeclaredMethod("capacity");
        capacity.setAccessible(true);

        // 创建HashMap，该方法未初始化容器大小，默认为16
        Map<String, Object> map1 = Maps.newHashMap();
        // 测试map1的容量
        assertTrue("map1的容量为16", (Integer)capacity.invoke(map1) == 16);

        // 创建HashMap，该方法会初始化容量的大小，注意：初始化的容量不一定是用户指定的参数，newHashMapWithExpectedSize方法会计算能装下当前数量且不用扩容的数值
        // 实际容量的计算可参考HashMap的扩容机制
        Map<String, Object> map2 = Maps.newHashMapWithExpectedSize(20);
        assertTrue("map2的容量大于等于20", (Integer)capacity.invoke(map2) >= 20);

        // 创建HashMap，该方法会初始化容量的大小，注意：初始化的容量不一定是用户指定的参数，HashMap方法会计算能装下当前数量且不用扩容的数值
        Map<String, Object> map3 = new HashMap<String, Object>(20);
        assertTrue("map3的容量大于等于20", (Integer)capacity.invoke(map3) >= 20);

    }
}
