package cn.jasonren.seckill.service;

import cn.jasonren.seckill.dto.Exposer;
//import cn.jasonren.seckill.dto.SeckillExecution;
import cn.jasonren.seckill.dto.SeckillExecution;
import cn.jasonren.seckill.entity.Seckill;
import cn.jasonren.seckill.exception.RepeatKillException;
import cn.jasonren.seckill.exception.SeckillCloseException;
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
@ContextConfiguration({
    "classpath:spring/applicationContext-dao.xml",
    "classpath:spring/applicationContext-service.xml"})
public class SeckillServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}", seckillList);
    }

    @Test
    public void testGetById() throws Exception {
        long seckillId = 1000;
        Seckill seckill = seckillService.getById(seckillId);
        logger.info("seckill={}", seckill);
    }

    //测试代码完整逻辑，注意可重复执行
    @Test
    public void testExportSeckillLogic() throws Exception {
        long seckillId = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 18621008382L;
            String md5 = "be70bf70f13b7d6d1c0aa8d12f245b8d";
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            }catch (RepeatKillException | SecurityException e){
                logger.error(e.getMessage());
            }
        }else {
            //秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000;
        long phone = 18621008381L;
        String md5 = "be70bf70f13b7d6d1c0aa8d12f245b8d";
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("seckillExecution={}", seckillExecution);
        }catch (RepeatKillException | SecurityException e){
            logger.error(e.getMessage());
        }
    }

    @Test
    public void executeSeckillProdure() {
        long seckillId = 1001;
        long phone = 1368011101;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }
}