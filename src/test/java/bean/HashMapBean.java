package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashMapBean {

    private String name;

    @Override
    public int hashCode(){
        // 让hashCode永远等1测试hash碰撞
        return 1;
    }
}
