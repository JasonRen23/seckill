package cn.jasonren.seckill.dao.cache;

import cn.jasonren.seckill.dao.SeckillMapper;
import cn.jasonren.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class RedisDaoTest {
    private long id = 1000;
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillMapper seckillDao;


    @Test
    public void testSeckill() throws Exception {
        //get and put
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }

        }

    }


}