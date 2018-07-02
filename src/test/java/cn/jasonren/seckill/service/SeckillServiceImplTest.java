package cn.jasonren.seckill.service;

import cn.jasonren.seckill.dto.Exposer;
import cn.jasonren.seckill.entity.Seckill;
import cn.jasonren.seckill.service.interfaces.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * @author : JasonRen
 * @date : 2018/07/02
 * @email : zhicheng_ren@163.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml"})
public class SeckillServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception{
        List<Seckill> seckillList = seckillService.getSeckillList();
        System.out.println(seckillList.toString());
    }

    @Test
    public void getById() throws Exception{
        long seckillId = 1000;
        Seckill byId = seckillService.getById(seckillId);
        System.out.println(byId.toString());
    }

    @Test
    public void exportSeckillUrl() throws Exception{
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        System.out.println(exposer.toString());
    }
}