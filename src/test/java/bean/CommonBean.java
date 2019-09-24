package bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CommonBean implements Cloneable, Serializable {

    private String name;

    private String id;

    public static void main(String[] args) throws CloneNotSupportedException {
        CommonBean bean = new CommonBean();
        bean.setName("kevin");
        bean.setId("1234");
        CommonBean bean1 = (CommonBean) bean.clone();
        System.out.println(bean);
        System.out.println(bean1);
        System.out.println(bean == bean1);
    }

}
