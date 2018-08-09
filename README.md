## Java高并发秒杀项目
### 环境
jdk1.8 + mac idea + tomcat7 Plugin / tomcat热部署
### 优化思路
1. 查询秒杀url的时候先去redis中查询，如果redis中不存在这个key，再去MySQL中查找，减轻
MySQL压力，同时采用protostuff做序列化框架序列化Seckill这个POJO。
2. 因为对数据库的更新操作需要获取行锁，而插入不需要，如果我们将插入和更新的顺序调换，也可以
减少竞争行级锁的阻塞时间。
3. 将执行秒杀操作的insert和update直接放在MySQL服务端的存储过程，Java客户端直接调用，省去了
事务管理。
4. 将原有数据库连接池C3P0换为Druid，其支持SQL拦截，并发性较好，且有监控功能，访问http://localhost:8080/druid/index.html可登入监控界面。

监控效果如下：
![](https://ws1.sinaimg.cn/large/73d640f7ly1fu40v01qcvj226u11ggwb.jpg)

![](https://ws1.sinaimg.cn/large/73d640f7ly1fu40uzv68xj226m0fowjn.jpg)

### 重点难点
1. Spring的声明式事务，此次项目里采用的是@Transcational注解的方式。并且spring的事务
并非只要有错就会回滚，而是只有在运行期出现异常才回滚。
2. [熟悉tomcat的热部署](https://www.jianshu.com/p/0458f2f5eecd)
3. 用redis做服务端缓存，protostuff做高效序列化和反序列化。
### 项目展示
#### 商品详情页
![](https://ws1.sinaimg.cn/large/73d640f7ly1ftubnr1mllj21t00ligpx.jpg)
#### 重复秒杀
![](https://ws1.sinaimg.cn/large/73d640f7ly1ftubnr1mllj21t00ligpx.jpg)
#### 秒杀倒计时
![](https://ws1.sinaimg.cn/large/73d640f7ly1ftubnwblbej21sw0auq4j.jpg)
#### 秒杀结束
![](https://ws1.sinaimg.cn/large/73d640f7ly1ftubnufz1jj21sc0akq4a.jpg)
