package Objects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ObjectsTest {

    @Test
    public void test1() {
        List<String> list = Lists.newArrayList();
        list.add("第一个元素");
        List<String> list1 = Lists.newArrayList();
        list1.add("第一个元素");
        // 因为ArrayList的父类有重写equals方法，所以这里可以通过测试
        assertTrue("Objects的equals方法", Objects.equal(list, list1));
    }

    @Test
    public void test2() {
        List<String> list = Lists.newArrayList();
        list.add("第一个元素");
        list.add("第一个元素");

        Student student = new Student();
        student.setGender("男");
        student.setName("Kevin");

        // ArrayList已重写toString方法
        System.out.println("ArrayList原生：" + list);
        System.out.println("使用toStringHelper：" + MoreObjects.toStringHelper(list).toString());
        System.out.println("在类方法中使用toStringHelper编写toString方法：" + student);
    }

    @Data
    class Student{
        private String name;
        private String gender;

        @Override
        public String toString(){
            return MoreObjects.toStringHelper(this).add("name", this.name).add("gender", this.gender).toString();
        }
    }
}
