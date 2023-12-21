package repositories;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public abstract class AbstractRedisRepository implements AutoCloseable {
    private static JedisPooled pool;
    private static Jsonb jsonb = JsonbBuilder.create();
    private static String host;

    static {
        try {
            host = getNode("host");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int port;

    static {
        try {
            port = Integer.parseInt((getNode("port")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initConnection() {
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
        pool = new JedisPooled(new HostAndPort(host, port), clientConfig);
    }

    public AbstractRedisRepository() {
        initConnection();
    }

    private static String getNode(String node) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = new String(Files.readAllBytes(Paths.get("src/main/java/configuration/info.json")));
        JsonNode jsonNode = objectMapper.readTree(json);
        return jsonNode.get(node).asText();
    }

    public static JedisPooled getPool() {
        return pool;
    }

    public static Jsonb getJsonb() {
        return jsonb;
    }

    public boolean checkConnection() {
        return pool.getPool().getResource().isConnected();
    }

    public void clearCache(){
        Set<String> keys = pool.keys("*");
        for (String key : keys){
            pool.del(key);
        }
    }

    @Override
    public void close() throws Exception {
//        pool.getPool().destroy();
        pool.close();
    }


}

