# JanusGraph 语法

> Tinkerpop 是基于内存的图数据库，JanusGraph 在集成Tinkerpop 的时候也保留了TinkerGraph 

### 基于 gremlin 写入内存中
  `graph = JanusGraphFactory.open('inmemory')`
  或
  `graph = JanusGraphFactory.build().set("storage.backend","inmemory").open()`

- 开启一个图数据库实例

  ```
  graph = JanusGraphFactory.open('conf/janusgraph-berkeleyje-es.properties')
  ```

- 获取图遍历句柄

  ```
  g = graph.traversal()
  ```

- Gremlin 查看帮助

  ```
  :help
  :h
  :?
  ```

- 查看版本

  ```groovy
  Gremlin.version
  ```

- 查看已导入的包

  ```
  :show imports
  ```

- 关闭日志输出

  ```
  g.V().count();[]
  ```

  > 如果执行结果返回”==>null”，则表示调用了一个返回void 的方法，且没有异常发生。

  > 可以执行任何Groovy 代码，也可以这样执行：`gremlin -e mycode.groovy`
  > 还可以在console 中加载groovy 脚本，比如：
  > gremlin> `:load init.groovy`

- 记录会话到日志文件

  ```groovy
  gremlin> :record start mylog.txt
  Recording session to: "mylog.txt"
  ==>mylog.txt
  gremlin> 1+3
  ==>4
  gremlin> :record stop
  Recording stopped; session saved as: "mylog.txt" (137 bytes)
  ==>mylog.txt
  gremlin>
  ```

### 图事务

> 任何的图操作都会自动开启一个事务。如果事务没有提交，那么操作就不会生效。
>
> Graph Structure API  和   Graph Process API  分别如下：

- 提交事务

  ```groovy
  graph.tx().commit()
  g.tx().commit()
  ```

- 回滚事务

  ```groovy
  graph.tx().rollback()
  g.tx().rollback()
  ```

- 查询事务

  > 查看当前正在进行的事务，如下：

  ```groovy
  graph.getOpenTransactions()
  ```

  > 想要结束当前所有的事务

  ```groovy
  for (tx in graph.getOpenTransactions()) tx.rollback()
  ```

### 创建顶点(节点)

- 新增一个顶点，标签是可选 的。例如：创建一个没有任何标识的顶点
  ```
  gremlin> :> g.addV()
  ==>v[4128]
  ```
```
  
返回的是创建成功的顶点的 `id`
  
- 创建带标签的顶点

```
  gremlin> :> g.addV('person')
  ==>v[4160]
  ```

  标签一旦创建之后不可更改

- 创建带标签和属性的顶点

  ```
  gremlin> :> g.addV('person').property('name','huihuang')
  ==>v[4256]
  ```

  > 同时添加多个属性

  ```
  gremlin> :> g.addV('person').property('name','huihuang').property('city','Chengdu')
  ==>v[4168]
  ```

### 查看顶点

- 查看所有的顶点

  ```
  gremlin> :> g.V()
  ==>v[4112]
  ==>v[4120]
  ==>v[4128]
  ....
  ```

  >  注：不推荐这样使用。当数据量很大的时候会卡死

- 根据标签查看顶点

  ```
  gremlin> :> g.V().hasLabel('person')
  ==>v[4160]
  ==>v[4168]
  ==>v[4216]
  ```

  > 注：不推荐这样使用，在数据量很大的时候。

- 根据属性查看顶点

  ```groovy
  g.V().has('code','DFW')
  变体
  g.V().hasNot('region') 等价于g.V().not(has('region'))
  ```

- 根据 `id`  查看顶点

  ```groovy
  gremlin> :> g.V(8312).valueMap()
  ==>{city=[Chengdu], name=[huihuang]}
  gremlin> 
  ```

- 查看顶点的标签

  ```
  gremlin> :> g.V().has('name','suxiehe')
  ==>v[4328]
  gremlin> 
  ```

  

- 查看顶点的属性键值对

  ```
  gremlin> :> g.V().has('name','mobboy').valueMap()
  ==>{name=[mobboy]}
  gremlin>
  ```

  > 只查看属性值

  ```
  gremlin> :> g.V().has('name','mobboy').values()
  ==>mobboy
  gremlin>
  ```

  

- 

  

  

   

- 查询刚创建的顶点

  `g.V().has('name','Dennis').valueMap()`

- 