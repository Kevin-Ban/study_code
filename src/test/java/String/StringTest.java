package String;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StringTest {

    private final Joiner stringJoiner = Joiner.on(",").skipNulls();

    private final Splitter splitter = Splitter.on(",");

    @Test
    public void test1() {
        List<String> list = Lists.newArrayListWithCapacity(10);
        list.add("kevin");
        list.add("sam");
        list.add("david");
    }

    @Test
    public void test2() {
        LocalDateTime time = LocalDateTime.of(2000, 1, 23, 1, 2);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(time.format(dateTimeFormatter));
    }
}
