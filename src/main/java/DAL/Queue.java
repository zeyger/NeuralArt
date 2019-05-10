package DAL;

import redis.clients.jedis.Jedis;

import java.util.List;

public class Queue {
    private Jedis jedis;

    public Queue() {
        this.jedis = new Jedis();
    }

    public void put(String job) {
        jedis.lpush("pending", job);
    }

    public List<String> show() {
        return jedis.lrange("pending", 0, -1);
    }
}
