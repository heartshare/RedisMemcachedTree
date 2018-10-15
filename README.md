# RedisMemcachedTree
Redis/Redisson, memcached缓存

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

  <pre>
  redis内存管理机制
  </pre>
</pre>
