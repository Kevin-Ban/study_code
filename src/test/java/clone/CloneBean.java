package clone;

import bean.CommonBean;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CloneBean implements Cloneable, Serializable {

    private int id;

    private String name;

    public CommonBean commonBean;

    @Override
    public Object clone() throws CloneNotSupportedException {
        // 在这里调用父类的clone方法只能实现浅克隆
        //region 如要实现深克隆，需要同时对类里面的所有引用对象进行克隆，并且重新赋值
//        CloneBean cloneObject = (CloneBean) super.clone();
//        if (this.getCommonBean() != null) {
//            cloneObject.setCommonBean((CommonBean) this.getCommonBean().clone());
//        } else {
//            cloneObject.setCommonBean(null);
//        }
//        return cloneObject;
        //endregion
        //region 浅克隆
        return super.clone();
        //endregion
    }
}
