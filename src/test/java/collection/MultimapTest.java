package collection;

import bean.CommonBean;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Collection;

public class MultimapTest {

    @Test
    public void test1() {
        // region list
        Multimap<String, CommonBean> commonMap = ArrayListMultimap.create();
        for (int i = 10; i < 20; i++) {
            CommonBean commonBean = new CommonBean();
            commonBean.setId(String.valueOf(1001 + i));
            commonBean.setName(String.valueOf(100 - i));
            // 添加多个，不会去重
            commonMap.put("test", commonBean);
            commonMap.put("test", commonBean);
        }
        System.out.println("commonMap:" + commonMap.size());
        System.out.println("commonMap:" + commonMap.keys());
        for (String key : commonMap.keySet()) {
            Collection<CommonBean> commonBeans = commonMap.get(key);
            for (CommonBean item : commonBeans) {
                System.out.println(item.toString());
            }
        }
        // endregion

        System.out.println("=====================================================");

        // region set
        Multimap<String, CommonBean> setMultiMap = HashMultimap.create();
        for (int i = 10; i < 20; i++) {
            CommonBean commonBean = new CommonBean();
            commonBean.setId(String.valueOf(1001 + i));
            commonBean.setName(String.valueOf(100 - i));
            // 添加多个时，会向HashSet那样去重
            setMultiMap.put("test", commonBean);
            setMultiMap.put("test", commonBean);
        }
        System.out.println("commonMap:" + setMultiMap.size());
        System.out.println("commonMap:" + setMultiMap.keys());
        for (String key : setMultiMap.keySet()) {
            Collection<CommonBean> commonBeans = setMultiMap.get(key);
            for (CommonBean item : commonBeans) {
                System.out.println(item.toString());
            }
        }
        //endregion

    }
}
