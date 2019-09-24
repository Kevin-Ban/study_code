package clone;

import bean.CommonBean;
import org.junit.Test;

import java.io.*;

public class CloneTest {

    @Test
    public void lightCloneTest() throws CloneNotSupportedException {
        CloneBean cloneBean = new CloneBean();
        CommonBean commonBean = new CommonBean();
        commonBean.setId("2");
        commonBean.setName("commonBean name");
        cloneBean.setId(1);
        cloneBean.setName("name");
        cloneBean.setCommonBean(commonBean);

        CloneBean cloneBean1 = (CloneBean) cloneBean.clone();

        // 原对象与克隆出来的对象地址值是不等的
        System.out.println("原对象与克隆出来的对象是否相等：" + (cloneBean == cloneBean1));
        // 原对象与克隆出来的对象里面的引用对象commonBean地址值是相等的, 修改了其中一个，另一个也会被修改
        System.out.println("原对象与克隆出来的对象里面的引用对象是否相等：" + (cloneBean.getCommonBean() == cloneBean1.getCommonBean()));
    }

    @Test
    public void deepCloneTest() throws IOException, ClassNotFoundException {
        CloneBean cloneBean = new CloneBean();
        CommonBean commonBean = new CommonBean();
        commonBean.setId("2");
        commonBean.setName("commonBean name");
        cloneBean.setId(1);
        cloneBean.setName("name");
        cloneBean.setCommonBean(commonBean);

        //region 将需要克隆的对象进行序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(cloneBean);
        //endregion

        //region 反序列化，之后就可以得到该对象的深克隆，就算对象中包含数组，引用对象等也可以得到相同的数据
        // 使用序列化进行克隆一般效率较低(涉及到IO操作)，故没有特殊情况时，一般建议自己实现clone方法
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        CloneBean cloneBean1 = (CloneBean) objectInputStream.readObject();
        //endregion

        // 原对象与克隆出来的对象地址值是不等的
        System.out.println("原对象与克隆出来的对象是否相等：" + (cloneBean == cloneBean1));
        // 原对象与克隆出来的对象里面的引用对象commonBean地址值是不相等的, 两个对象的commonBean互不干扰
        System.out.println("原对象与克隆出来的对象里面的引用对象是否相等：" + (cloneBean.getCommonBean() == cloneBean1.getCommonBean()));

    }
}
