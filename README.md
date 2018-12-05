# RedisMemcachedTree
Redis/Redisson, memcached缓存

<pre>
缓存
	客户端缓存
	    1）页面缓存（页面元素）
	    2）浏览器缓存（图片等）
	网络中的缓存
	    1）web代理缓存（如nginx）
	    2）边缘缓存（如CDN技术）
	服务端缓存
	    1）数据库缓存
	    2）平台级缓存（如Ehcache）
	    3）应用级缓存（如redis, memcached）
</pre>

<pre>
分布式系统理论
    1）一致性（C）
    2）可用性（A）
    3）分区容忍性（P）
</pre>

<pre>
Redis
1) redis实现消息队列功能
2）redis数据结构
3）redis数据安全与性能保障
    1）数据持久化
       快照、AOF 重写压缩AOF
    2）复制
    3）处理系统故障
    5）redis故障
    6）性能
5）降低内存占用
   1）短结构
      压缩
   2）分片结构
6）redis的lua脚本编程   
</pre>

<pre>
1) memcached数据结构
</pre>

<pre>
Evcache
	Evcache是一个非常优秀的缓存服务，能够提供低延迟，高可靠性的解决方案，基于memcached的内存存储，
	Evcache典型的适合对强一致性没有必须要求的场合
	   1）大流量
	   2）健壮的key-value编程接口，每秒处理3千万请求，存储数十亿个对象，跨数千台memcached服务器
    Evcache是典型的客户端/服务器架构，服务器包括一个memcached进程，还包括一个Prana的java进程用于与发现服务通信并托管本地管理，以及监控服务健康状态和统计状态的各种应用，并将统计信息发送给Netflix平台的统计服务面向微服务的java应用提供了一个集成应用程序到微服务生态系统的HTTP接口，主要功能包括
		1）注册到发现系统
		2）其他服务的发现
		3）健康检查服务
		5）HTTP API和负载均衡要求
		6）动态属性加载
</pre>


redis

![](https://i.imgur.com/sbIs3eL.jpg)

![](https://i.imgur.com/kbZHig4.jpg)

<pre>
使用Redis的理由
    memcached采用的办法是通过黑名单来隐藏列表里面的元素，从而避免对元素执行读取，更新，写入等操作，
	redis则允许用户直接添加删除元素

    使用Redis而不是memcached来解决问题，不仅可以让代码变得简单，更易懂，更易维护，而且还可以是代码的运行速度更快（因为用户不需要通过读取数据库来更新数据），除此之外，在其他许多情况下，Redis的效率和易用性也比关系型数据库要好得多。

    使用Redis构建Web应用
       1）登录cookie缓存
	   2）使用redis实现购物车
	   3）使用redis实现网页缓存
	   5）数据行缓存
	   6）网页分析，网站可以从用户的访问，交互和购买行为中收集到有价值的信息。

    数据安全与性能保障
    
    使用Redis构建支持程序
       1）使用redis记录日志
	   2）计数器和统计数据
	   3）服务的发现与配置
	
    使用Redis构建应用程序组件
       1）自动补全
       2）分布式锁
       3）计数信号量
       5）任务队列
       6）消息 拉取（PUBLISH SUBSCRIBE）
       7）使用Redis进行文件分发

    基于搜索的应用程序
       1）使用Redis进行搜索
       2）有序索引
       3）广告定向
       5）职位搜索
   
    构建简单的社交网络
       1）用户和状态
       2）主页时间线
       3）关注者列表
       5）状态消息的发布和删除
       6）流API

    降低内存占用
       1）短结构
       2）分片结构
       3）打包存储二进制位和字节
    
    扩展Redis
       1）扩展读性能
       2）扩展写性能和内存容量	
	   3）扩展复杂的查询

    Redis的Lua脚本编程
       1）使用lua重写锁和信号量
       2）移除watch/multi/exec事务
       3）使用lua对列表进行分片

</pre>

<pre>
Redis Rehash
</pre>

<pre>
Redis内存使用分析

      info memory
      命令可以查看内存使用情况。
      返回参数：
          1）used_memory：
             Redis分配器分配的内存总量，包括使用的虚拟内存（swap），
          2）used_memory_rss:
             Redis进程占据操作系统的内存，除了分配器分配的内存外，used_memory_rss还包括
             进程运行本身需要的内存，内存碎片等，但是不包括虚拟内存。

      因此,used_memory和used_memory_rss，前者是从Redis角度得到的量，后者是从操作系统角
      度得到的量。二者之所以不同，一方面是因为内存碎片和Redis进程运行需要占用内存，使得前者
      可能比后者小，另一方面虚拟内存的存在，使得前者可能比后者大。

      由于在实际应用中，Redis的数量会比较大，此时进程运行占用的内存与Redis数据量和内存碎片
      相比，都会笑的多；因此used_memory_rss和used_memory的比例，便成了衡量Redis内存碎片
      率的参数。
 
         （3）mem_fragmentation_ratio：内存碎片比率，该值是used_memory_rss / used_memory的比值。

      mem_fragmentation_ratio一般大于1，且该值越大，内存碎片比例越大。mem_fragmentation_ratio<1，说明Redis使用了虚拟内存，由于虚拟内存的媒介是磁盘，
      比内存速度要慢很多，当这种情况出现时，应该及时排查，如果内存不足应该及时处理，如增
      加Redis节点、增加Redis服务器的内存、优化应用等

      缓冲内存：
             缓冲内存包括客户端缓冲区，复制积压缓冲区，AOF缓冲区等；其中，客户端缓冲存储
      客户端连接的输入输出缓冲；复制积压缓冲用于部分复制功能；AOF缓冲用于进行AOF重写等，
      保存最近的写入命令，

      内存碎片
             内存碎片是Redis在分配，回收物理内存过程中产生的。例如，如果对数据的更改频繁，
      而且数据之间的大小相差很大，可能导致Redis释放的空间在物理内存中并没有释放，但Redis
      又无法有效利用，这就形成了内存碎片。

             内存碎片的产生于对数据进行的操作，数据的特点等有关；此外，与使用的内存分配器
      也有关，如果内存分配器设计合理，可以尽可能的减少内存碎片的产生。

             如果Redis服务器中的内存碎片已经很大，可以通过安全重启的方式减小内存碎片，因为
      重启之后，Redis重新从备份文件中读取数据，在内存中进行重排，为每个数据重新选择合适的内
      存单元，减小内存碎片。
</pre>

![](https://i.imgur.com/aPIfn2a.png)

<pre>
Redis集群方案
      在生产环境下搭建Redis高性能集群，如果其中只使用一个Twemproxy节点，那肯定是不合理
      的。因为那样做会存在Twemproxy单节点故障问题，所以至少应该使用两个Twemproxy节点。又
      因为Twemproxy服务的工作相对独立，为了增加访问性能可以使用两个甚至多个Twemproxy节
      点同时提供服务，其上统一使用LVS服务进行负载分发。根据这样的描述，我们可以构建一种在
      生产环境下使用的Redis高性能集群方案

      Twemproxy提供了一个配合使用的扩展组件：Redis_Twemproxy_Agent，它的作用是监
      控Sentinel中Master节点的情况，并且将最新的Master节点情况通知Twemproxy。这样一来
      当下层某组Redis高可用集群发生Master—Slave状态切换时，Twemproxy就会适时对其下层代
      理配置情况作出调整。

      另外，上图中给出的第二种生产环境下的Redis集群方案，一共有5组独立运行的Redis高可用
      集群组，每组Redis高可用集群都有一个Master节点和至少一个Slave节点，它们之间使
      用Redis原生提供的数据复制功能保持数据同步。最后这些Redis高可用集群组通过一
      组Sentinel进行状态监控，而这组Sentinel也是同时拥有一个Master节点和两个Slave节点的高可用集群
</pre>

<pre>
Redis的事务（transaction）是一组命令的集合。事务同命令一样都是Redis的最小执行单位，一个事
务中的命令要么执行，要么不执行。

redis＞MULTI
OK
redis＞SADD "user:1:following" 2
QUEUED
redis＞SADD "user:2:followers" 1
QUEUED
redis＞EXEC
1) (integer) 1
2) (integer) 1


由于WATCH命令的作用只是当被监控的键值被修改后 阻止 之后一个事务的执行，而不能保证其他客户
端不修改这一键值，所以我们需要在EXEC执行失败后重新执行整个函数。

执行EXEC命令后会取消对所有键的监控 ，如果不想执行事务中的命令也可以使用 UNWATCH 命令来取消监控
</pre>

<pre>
分区：
      分区是分隔数据到多个Redis实例的处理过程，因此每个实例只保存key的一个子集。
   
      分区的不足：
            1）设计多个key的操作是不被支持的，当两个set映射到不同的redis实例上时，就不能
               对这两个set执行交集操作。
            2）设计多个key的redis事务不被支持

      分区方法：
            1）范围分区
            2）hash分区
</pre>


<pre>
Redis安全：
      1）运行在可信环境
      2）为Redis设置密码
</pre>

<pre>
Redis脚本：Lua脚本
      使用脚本的好处：
          1）减少网络开销。可以将多个请求通过脚本的形式一次发送，减少网络延时问题。
          2）原子操作，Redis会将整个脚本作为一个整体执行，中间不会被其他命令插入。因此在
             编写脚本的过程中无需担心会出现竞态条件，无需使用事务。
          3）复用，客户端发送的脚本会永久保留在Redis中，这样，其他客户端就可以复用这一脚本
             使用代码完成相同的逻辑。
</pre>

<pre>
Redis管道
      Redis的底层通信协议对管道（pipelining）提供了支持。通过管道可以一次性发送多条命令
      并在执行完后一次性将结果返回，当一组命令中每条命令都不依赖于之前命令的执行结果时就
      可以将这组命令一起通过管道发出。管道通过减少客户端与Redis的通信次数来实现降低往返时
      延累计值的目的
</pre>

<pre>
使用Redis实现最近搜索存储，以及搜索自动提示功能
</pre>

![](https://i.imgur.com/TO2HNPl.png)

![](https://i.imgur.com/fUlJWqF.png)

![](https://i.imgur.com/OixNeU5.png)

<pre>
Redis通常的使用场景：
      1)排行榜
      2）Session存储
      3）验证码存储
      5）pub/sub 
         你可以创建一个实时聊天系统，在社交网络上触发好友请求的通知等等
</pre>

<pre>
redis中允许模糊查询的有3个通配符，分别是：*，?，[]

模糊查询：
        StringRedisTemplate.keys("*" + pattern + "*");
</pre>