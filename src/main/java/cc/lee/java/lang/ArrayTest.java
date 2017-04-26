package cc.lee.java.lang;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by bjlizhitao on 2017/4/19.
 */
public class ArrayTest {
    public static void main(String[] args) {
        new Random().nextInt(0);

        System.out.println(org.apache.commons.lang3.StringUtils.containsOnly("adsfsdf,sdfsdf", ","));


        /*int[] aa = new int[10];

        for (int a : aa) {
            System.out.println(a);
        }*/

        List<String> list = Lists.newArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");
        list.add("fff");
        list.add("ggg");
        list.add("aaa");
        list.add("ggg");
        list.add("aaa");
        list.add("aaa");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("aaa")) {
                iterator.remove();
            }
        }

        /*for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("aaa"))
                list.remove(i);
        }*/

        System.out.println(list);
    }
}
