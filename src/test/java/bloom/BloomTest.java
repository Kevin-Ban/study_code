package bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class BloomTest {

    @Test
    public void bloomTest() {
        int capaticy = 1000000;
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 200, 0.01);
        Runtime runtime = Runtime.getRuntime();
//        Set<String> set = Sets.newHashSet();
        for (int i = 0; i < 200; i++) {
            filter.put(UUID.randomUUID().toString());
//            set.add(UUID.randomUUID().toString());
        }
        System.out.println(runtime.totalMemory() - runtime.freeMemory());
//        System.out.println(UUID.randomUUID().toString());
//        for (int i = 0; i < capaticy; i++) {
//            filter.put(String.valueOf(i));
//        }
////        List<Integer> list = new ArrayList<>(10);
//        for (int i = 0; i < capaticy; i++) {
//            if (!filter.mightContain(String.valueOf(i))) {
//                System.out.println(i);
//            }
//        }
//        System.out.println("-1是否在过滤器中：" + filter.mightContain("-1"));

    }
}
