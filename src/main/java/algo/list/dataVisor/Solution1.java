package algo.company.dataVisor;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.StreamEntryID;

import java.util.HashMap;
import java.util.Map;

/// redis 来实现消息队列
public class Solution1 {

    /// XADD key ID field value [field value ...]
    public void producer(int[] nums1, int m) {
        Jedis jedis = new Jedis("localhost", 6347);
        int msgid = 0;
        Map map = new HashMap();
        map.put( "field1", "value1");
        jedis.xadd("topicA" , (StreamEntryID) null,map);
    }

    //XREAD [COUNT count] [BLOCK milliseconds] STREAMS key [key ...] id [id ...]
    public void consumer(int[] nums1, int m) {
        Jedis jedis = new Jedis();
        int msgid = 0;
        Map map = new HashMap();
        jedis.xread(null,map);
    }
}
