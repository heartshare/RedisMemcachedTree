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

Aerospike

![](https://i.imgur.com/NeZOsM2.png)

![](https://i.imgur.com/N9YTK0n.png)

<pre>
Aerospike
    Aerospike是一个高性能，可扩展，可靠性强的nosql解决方案，支持RAM和SSD作为存储介质，并针对SSD特殊优化，广泛应用于实时竞价等实时计算领域,官方保证99%的操作在1ms内完成，并提供集群数据自动rebalance，集群感知客户端等功能，且支持超大规模数据库
    
    作为K-V存储，Aerospike提供多种数据类型，其操作方式和redis类似，各种维护工具，比redis维护友好

    redis数据完全存储在内存虽然保证了查询性能，但是成本太高，aerospike最大的卖点就是数据可以存储在SSD上，并且保证了和redis相同的查询性能，内部在访问SSD时屏蔽了文件系统层级，直接访问地址，保证了数据的读取速度

    policies决定namespace的行为，包括
		1）数据的存储位置： 内存还是SSD盘
		2）一条记录存储的副本个数
		3）过期时间TTL, aerospike可以在库的层级进行全局设置，并且支持对于已经存在的数据进行TTL的设置
    Aerospike最主要的优势是采用混合存储结构，数据索引信息存储在RAM中，而数据本身可以存储在SSD盘，通过直接硬盘访问可以带来难以置信的性能,主要应用于数百G,数T等大规模并且并发数在数万以上，对读写性能要求较高的场景，目前主要集中在互联网广告领域。
    Aerospike的架构针对以下三个主要目标
		1）创建一个满足当今网络平台应用的可弹性扩展平台
		2）提供与传统数据库一样的鲁棒性和可靠性(ACID)
		3）以最小的人工参与，提高运维效率，降低运维成本
</pre>

![](https://i.imgur.com/edDlpNP.png)

![](https://i.imgur.com/ZDy8aaC.png)


<pre>
couchbase
    1) CouchDB和MemBase的合并，MemBase是基于Memcached的，因此couchbase结合了couchbase的简单可靠和memcached的高性能，以及membase的可扩展性
        1）灵活的数据模型，使用json格式存储对象
        2）不需要定义数据结构，数据可以存储为key-value对或者json文档，scaleout只需要增加服务器就行
    2) 数据存储
	    couchbase通过使用buckets提供数据管理服务，相当于关系数据库中的库，保存数据时，先建bucket，然后直接插入，couchbase有两种类型的buckets
		1）memcached buckets
		   只将数据存储在内存，key-value缓存，设计用于关系型数据库的缓存
		2) couchbase buckets
		   存数据再内存和硬盘
	3) 内存配额
    5) vBucket
       1) vBucket:相当于一个key的子集，保存的是客户端存储对象的key值，通过vBucket，客户端直接访问保存信息的服务器，不需要通过中间代理或者其他架构，每个key属于一个vBucket,通过key计算出vBucket,在从vBucket与服务器的对照表中找到具体的服务器，从具体服务器获取数据
       2) vBuckets 用于在集群的节点间分配数据和备份数据
    6）缓存层
	   couchbase自动管理缓存，存在一个后台进程，专门负责把一定时间没有被访问的数据移出内存，可配置具体参数，
	   couchbase在对数据进行增删时会先体现在内存中，而不是立刻体现在硬盘上，等待执行的硬盘操作会以write queue的形式排队执行，通过这种特性使得读写速度非常快
	   1）低水位
	      当移除数据过多以至于内存有效数据占用内存低于低水位的时候，couchbase会随机挑选一些文件到内存中以达到低水位，
	   2）高水位
	      当有效数据内存占用超过高水位时，couchbase就会移除数据
	   随着内存数据越来越多，当达到低水位时，系统不会做任何处理，当数据量持续增加，达到高水位时，系统会启动一个job移除数据，当达到低水位时任务停止
    7）硬盘存储
       couchbase主要使用缓存为客户端保存和返回信息，同时会逐渐将数据保存到硬盘以维持高可靠性，
       为了提高读写硬盘的速度和提高缓存的命中率，现在couchbase提供了多线程读写
       多线程问题解决：
       couchbase对每个线程访问的资源进行静态的分配，同时使用资源锁，当创建多个读写线程时，服务器为每个线程单独分配了不同的vbuckets,保证同一个vbuckets只有一个读线程，一个写线程访问
</pre>

couchbase内存管理

![](https://i.imgur.com/ElBnxGu.png)

redis内存管理

![](https://i.imgur.com/8VLZ9bF.png)

<pre>
内存管理
  couchbase,redis都是基于内存的数据库系统
  <pre>
  counchbase内存管理机制
	  默认使用Slab Allocation机制管理内存，其主要思想是按照预先规定的大小，将分配的内存分割成特定长度的块以存储相应长度的key-value数据记录，以完全解决内存碎片问题,所有的key-value数据都存储在slab allocation系统里，而couchbase的其它内存请求则通过普通的malloc/free来申请，
	  具体机制：
	     首先从操作系统申请一大块内存，并将其分割成各种尺寸的chunk，并把尺寸相同的块分成组slab class， 其中chunk就是用来存储数据的最小单位，可以设置chunk的增长因子
      缺点：空间浪费
  </pre>
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