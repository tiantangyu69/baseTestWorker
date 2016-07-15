package guava.cache;

import com.google.common.base.Joiner;
import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2016/6/28.
 */
public class CacheTest {
    private LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<String, String>() {
                public void onRemoval(RemovalNotification<String, String> notification) {
                    System.out.println(Joiner.on(",").join(notification.getKey(), notification.getValue(), notification.getCause()));
                }
            })
            .recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return "expired";
                }
            });

    @Test
    public void test() throws InterruptedException, ExecutionException {
        cache.put("aaaa", "aaaaa");
        cache.put("bbbb", "abbwebbbaaaa");
        cache.put("cccc", "aaasbwbwedfaa");
        cache.put("ddd", "aweawebaaa");
        cache.put("eee", "aaweebgaaa");
        cache.put("fff", "aaebwwebhwbaaa");
        cache.put("ggg", "aaabbsdbaa");
        cache.put("hhh", "aaabbebsbnewaaa");
        cache.put("iiii", "aaaweebaa");
        cache.put("jjjj", "aawebwbwbaaa");
        cache.put("kkkk", "aaawebweaa");
        cache.put("llll", "aaawg34tg43ebweaa");
        cache.put("mmm", "aaawe23r23bweaa");
        cache.put("nnn", "aaawebwsdg23eaa");

        System.out.println(cache.size());
        System.out.println(cache.stats().hitCount());

        Thread.sleep(12000);
        System.out.println();
        System.out.println("getCache fff: " + cache.get("fff"));
        System.out.println(cache.size());
    }
}
