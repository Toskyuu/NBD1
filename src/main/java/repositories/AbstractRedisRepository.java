package repositories;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

import java.util.Set;

public abstract class AbstractRedisRepository implements AutoCloseable {
    private static JedisPooled pool;
    private static Jsonb jsonb = JsonbBuilder.create();
    public void initConnection() {
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
        pool = new JedisPooled(new HostAndPort("localhost", 6379), clientConfig);
    }

    public AbstractRedisRepository() {
        initConnection();
    }

    public static JedisPooled getPool() {
        return pool;
    }

    public static Jsonb getJsonb() {
        return jsonb;
    }

    public void clearCache(){
        Set<String> keys = pool.keys("*");
        for (String key : keys){
            pool.del(key);
        }
    }

    @Override
    public void close() throws Exception {
        pool.getPool().destroy();
        pool.close();
    }
}

