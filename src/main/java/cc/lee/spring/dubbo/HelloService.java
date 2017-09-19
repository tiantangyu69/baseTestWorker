package cc.lee.spring.dubbo;

import java.util.List;
import java.util.Map;

/**
 * Created by bjlizhitao on 2016/7/30.
 */
public interface HelloService {
    String say(String msg);

    List<String> getNames();

    Map<String, List<String>> getMaps();
}
