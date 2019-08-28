package collection;

import bean.HashMapBean;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

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
        assertTrue("map1的容量为16", (Integer) capacity.invoke(map1) == 16);

        // 创建HashMap，该方法会初始化容量的大小，注意：初始化的容量不一定是用户指定的参数，newHashMapWithExpectedSize方法会计算能装下当前数量且不用扩容的数值
        // 实际容量的计算可参考HashMap的扩容机制
        Map<String, Object> map2 = Maps.newHashMapWithExpectedSize(20);
        assertTrue("map2的容量大于等于20", (Integer) capacity.invoke(map2) >= 20);

        // 创建HashMap，该方法会初始化容量的大小，注意：初始化的容量不一定是用户指定的参数，HashMap方法会计算能装下当前数量且不用扩容的数值
        Map<String, Object> map3 = new HashMap<String, Object>(20);
        assertTrue("map3的容量大于等于20", (Integer) capacity.invoke(map3) >= 20);

    }

    /**
     * 测试HashMap碰撞
     */
    @Test
    public void testImpact() {
        Map<HashMapBean, Object> map = Maps.newHashMapWithExpectedSize(16);
        // HashMapBean作为key时，由于重写了hashCode方法，每次都返回1，所以每次put都会产生碰撞
        // 小于等与8时，是链表的Node
        for (int i = 0; i <= 8; i++) {
            HashMapBean bean = new HashMapBean(String.valueOf(i));
            map.put(bean, i);
        }
        System.out.println("暂停一下");
        // 当达到8次时会变成树节点(TreeNode)（通过断点调试查看，水平有限，通过反射的方式目前没找到方法）
        for (int i = 9; i < 20; i++) {
            HashMapBean bean = new HashMapBean(String.valueOf(i));
            map.put(bean, i);
        }
        System.out.println("暂停一下");
    }

    /**
     * 测试HashMap扩容死循环（失败）
     * @throws InterruptedException
     */
    @Test
    public void testThread() throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory());
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(16);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 2000; i < 3000; i++) {
                map.put(i, i);
            }
        });
        pool.execute(t1);
        pool.execute(t2);
        pool.shutdown();
        while (!pool.isTerminated()){
            System.out.println("等任务完成");
            // 让出cpu控制权，Thread.yield();也可以实现同样的想过
            Thread.sleep(1000);
        }
    }
}
