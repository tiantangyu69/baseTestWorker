package cc.lee.spring.dubbo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by bjlizhitao on 2016/7/30.
 */
public class HelloServiceImpl implements HelloService {
    public String say(String msg) {
        return "dubbo say: " + msg;
    }

    @Override
    public List<String> getNames() {
        List<String> lists = Lists.newArrayList();
        lists.add("aaaa");
        lists.add("bbb");
        lists.add("cc");
        lists.add("ddd");
        lists.add("ee");
        lists.add("ff");
        lists.add("gg");
        return lists;
    }

    @Override
    public Map<String, List<String>> getMaps() {
        Map<String, List<String>> maps = Maps.newHashMap();

        List<String> lists = Lists.newArrayList();
        lists.add("aaaa");
        lists.add("bbb");
        lists.add("cc");
        lists.add("ddd");
        lists.add("ee");
        lists.add("ff");
        lists.add("gg");

        maps.put("aa", lists);

        return maps;
    }
}
