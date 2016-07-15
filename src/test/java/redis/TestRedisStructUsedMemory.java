package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjlizhitao on 2016/7/5.
 * 测试redis各数据结构占用内存情况
 */
public class TestRedisStructUsedMemory {
    public static final int CONNECTION_TIMEOUT = 3000;// redis 连接超时3秒
    public static final int SO_TIMEOUT = 300000;// so_timeout 5分钟
    public static final String HOST = "localhost";
    public static final int PORT = 6379;

    public static final String KEY_PREFIX = "test_struct_";

    /**
     * 测试 string json 与 map 数据结构占用内存大小
     * 测试数据为 {"againstcount":182,"audioLock":"1","code":"1","prcount":258,"ptcount":252,"threadAgainst":0,"threadVote":1,"votecount":1130}
     */
    @Test
    public void testStringAndMap() {
        Jedis jedis = new Jedis(HOST, PORT, CONNECTION_TIMEOUT, SO_TIMEOUT);

        Pipeline pipeline = jedis.pipelined();

        int rows = 1000000;// 插入的数据量
//        saveInString(pipeline, KEY_PREFIX, rows);  // 1W 2.25MB   10W 22.34MB   20W 44.71MB    50W 110.8MB    100W 221.16MB
//        saveInMap(pipeline, KEY_PREFIX, rows);     // 1W 2.25MB   10W 22.35MB   20W 44.71MB    50W 110.8MB    100W 221.16MB
        pipeline.sync();
    }

    /**
     * 先执行插入操作，再执行更新操作，更新操作增加字符串长度，map则增加key，比对两种数据结构在修改数据后占用的内存大小
     * 测试数据为{"againstcount":182,"audioLock":"1","code":"1","prcount":258,"ptcount":252,"threadAgainst":0,"threadVote":1,"votecount":1130,"appendTest":"test"}
     * 追加字符串"appendTest":"test"
     */
    @Test
    public void testSetAndUpdateStringAndMap() {
        Jedis jedis = new Jedis(HOST, PORT, CONNECTION_TIMEOUT, SO_TIMEOUT);

        Pipeline pipeline = jedis.pipelined();

        int rows = 100000;// 插入的数据量
        saveInString(pipeline, KEY_PREFIX, rows);  // 1W 2.25MB   10W 22.34MB   20W 44.71MB    50W 110.8MB    100W 221.16MB
//        saveInMap(pipeline, KEY_PREFIX, rows);     // 1W 2.25MB   10W 22.35MB   20W 44.71MB    50W 110.8MB    100W 221.16MB


        // 更新数据
        updateInString(pipeline, KEY_PREFIX, rows);  // 1W 2.57MB    10W 25.43MB    20W 50.82MB    50W 127.09MB    100W 252.21MB
//        updateInMap(pipeline, KEY_PREFIX, rows);     // 1W 2.41MB    10W 23.88MB    20W 47.77MB    50W 118.58MB    100W 236.97MB

        pipeline.sync();

    }

    /**
     * 测试 list 和 set 数据结构占用内存大小
     */
    @Test
    public void testListAndSet() {
        Jedis jedis = new Jedis(HOST, PORT, CONNECTION_TIMEOUT, SO_TIMEOUT);

        Pipeline pipeline = jedis.pipelined();

        int rows = 1000000;// 插入的数据量
//        saveInList(pipeline, KEY_PREFIX, rows); // 1W 0.69MB    10W 6.87MB    20W 15.26MB    50W 40.65MB    100W 82.40MB
//        saveInSet(pipeline, KEY_PREFIX, rows);  // 1W 0.88MB    10W 8.37MB    20W 18.26MB    50W 44.44MB    100W 90.77MB

        pipeline.sync();
    }

    private void saveInString(Pipeline pipeline, String keyPrefix, int count) {
        for (int i = 0; i < count; i++) {
            pipeline.set(keyPrefix + i, "{\"againstcount\":182,\"audioLock\":\"1\",\"code\":\"1\",\"prcount\":258,\"ptcount\":252,\"threadAgainst\":0,\"threadVote\":1,\"votecount\":1130}");
        }
    }

    private void updateInString(Pipeline pipeline, String keyPrefix, int count) {
        for (int i = 0; i < count; i++) {
            pipeline.set(keyPrefix + i, "{\"againstcount\":182,\"audioLock\":\"1\",\"code\":\"1\",\"prcount\":258,\"ptcount\":252,\"threadAgainst\":0,\"threadVote\":1,\"votecount\":1130,\"appendTest\":\"test\"}");
        }
    }

    private void saveInMap(Pipeline pipeline, String keyPrefix, int count) {
        for (int i = 0; i < count; i++) {
            Map<String, String> value = new HashMap<String, String>();
            value.put("againstcount", "182");
            value.put("audioLock", "1");
            value.put("code", "1");
            value.put("prcount", "258");
            value.put("ptcount", "252");
            value.put("threadAgainst", "0");
            value.put("threadVote", "1");
            value.put("votecount", "1130");

            pipeline.hmset(keyPrefix + i, value);
        }
    }

    private void updateInMap(Pipeline pipeline, String keyPrefix, int count) {
        for (int i = 0; i < count; i++) {
            Map<String, String> value = new HashMap<String, String>();
            value.put("appendTest", "test");

            pipeline.hmset(keyPrefix + i, value);
        }
    }

    private void saveInList(Pipeline pipeline, String keyPrefix, int count) {
        for (int i = 0; i < count; i++) {
            pipeline.lpush(keyPrefix, "memoryTest" + i);
        }
    }

    private void saveInSet(Pipeline pipeline, String keyPrefix, int count) {
        for (int i = 0; i < count; i++) {
            pipeline.sadd(keyPrefix, "memoryTest" + i);
        }
    }
}
