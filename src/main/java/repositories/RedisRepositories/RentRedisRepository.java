package repositories.RedisRepositories;

import jakarta.json.bind.Jsonb;
import mapper.RentMapper;
import redis.RentJson;
import redis.clients.jedis.JedisPooled;
import repositories.AbstractRedisRepository;
import repositories.IRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RentRedisRepository extends AbstractRedisRepository implements IRepository<RentJson> {
    private JedisPooled pool = getPool();
    private Jsonb jsonb = getJsonb();
    private String prefix = "rent:";

    @Override
    public boolean add(RentJson entity) {
        try {
            entity.getItem().setRented(1);
            String entityStr = jsonb.toJson(entity);
            pool.set(prefix + entity.getId(), entityStr);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            pool.del(prefix + id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(RentJson entity) {
        try {
            String entityStr = jsonb.toJson(entity);
            pool.set(prefix + entity.getId(), entityStr);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RentJson findById(int id) {
        String found = pool.get(prefix + id);
        return jsonb.fromJson(found, RentJson.class);
    }

    @Override
    public List<RentJson> findAll() {
        List<RentJson> all = new ArrayList<>();
        Set<String> keys = pool.keys(prefix + "*");
        for (String key : keys) {
            all.add(jsonb.fromJson(pool.get(key), RentJson.class));
        }

        return all;
    }



}
