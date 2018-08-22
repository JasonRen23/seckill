package cn.jasonren.seckill.dao.cache;

import cn.jasonren.seckill.entity.Seckill;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 操作Redis的dao类
 *
 * @author : JasonRen
 * @date : 2018-08-01 下午10:00
 * @email : zhicheng_ren@163.com
 */
public class RedisDao {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDao.class);

    private final JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        //redis操作业务逻辑
        //try-with-resources statement
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "seckill:" + seckillId;
            //并没有内部实现序列化操作
            //get->byte[]字节数组->反序列化->Object(Seckill)
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                LOGGER.info("Redis取!");
                // new一个空对象
                Seckill seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                //seckill被反序列化
                return seckill;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        //set Object(Seckill) -> 序列化 -> byte[]
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "seckill:" + seckill.getSeckillId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //超时缓存
            int timeout = 60 * 60; //1小时
            LOGGER.info("放进去Redis了！");
            return jedis.setex(key.getBytes(), timeout, bytes);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
