## Java高并发秒杀项目
### 环境
jdk1.8 + mac idea + tomcat7 Plugin / tomcat热部署

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
