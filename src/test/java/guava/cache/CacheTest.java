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

    private LoadingCache<String, String> readCache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .maximumSize(1)
            .recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {// 缓存失效时如何加载缓存
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

    @Test
    public void testExpireAfterWrite() throws InterruptedException, ExecutionException {
        cache.put("foo", "foo1");

        Thread.sleep(6000);
        System.out.println("sleep 6 seconds, foo is: " + cache.get("foo"));

        cache.put("foo", "foo2");
        Thread.sleep(8000);
        System.out.println("sleep 8 seconds, foo is: " + cache.get("foo"));

        Thread.sleep(15000);
        System.out.println("sleep 15 seconds, foo is: " + cache.get("foo"));
    }

    @Test
    public void testExpireAfterRead() throws InterruptedException, ExecutionException {
        readCache.put("foo", "foo1");

        Thread.sleep(6000);
        System.out.println("sleep 6 seconds, foo is: " + readCache.get("foo"));

        Thread.sleep(8000);
        System.out.println("sleep 8 seconds, foo is: " + readCache.get("foo"));

        Thread.sleep(15000);
        System.out.println("sleep 15 seconds, foo is: " + readCache.get("foo"));
    }
}
