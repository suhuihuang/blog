# Yarn 资源调度系统

### 一. 课前准备

---

1. 搭建好三个节点的　hadoop 集群



### 二. 课堂主题

---

1. Yarn 架构
2. Yarn 应用提交过程
3. Yarn 的调度策略

### 三.  课堂目标

1. 了解 Yarn 资源和任务调度原理
2. 了解 如何使用Yarn 的可扩展性，效率和灵活性来增强集群性能

###　四. 知识要点

> 本堂使用　CDH 版本的 hadoop
>
> hadoop-2.6.0-cdh5.14.2

1. Yarn 介绍

   ![img](../images/a19a61bc-9378-3e38-944a-899a09f37908.jpg)

   - Apache Hadoop YARN(Yet Another Resoure Negotiator) 是Hadoop 的子项目，为分离Hadoop 2.0资源管理和计算组件而引入

   - YARN 具有足够的通用性，可以支持其他的分布式计算模式

     ![img](../images/99b59921-9a97-3199-8c39-d3b77dfdceaf.jpg)

2. YARN 架构

   - 类似 HDFS ，YARN 也是经典的主从（master/slave）架构

     - YARN 服务由一个ResourceManager(RM) 和多个 NodeMarager（NM）构成

     - ResourceManager 为主节点（master）

     - NodeManager 为从节点（slave）

       ![yarn的体系结构](../images/Figure3Architecture-of-YARN.png)

   - ApplicationMaster可以在容器内运行任何类型的任务。例如，MapReduce ApplicationMaster请求容器启动map或reduce任务，而Giraph ApplicationMaster请求容器运行Giraph任务。

   | 组件名                 | 作用                                                         |
   | :--------------------- | ------------------------------------------------------------ |
   | **ApplicationManager** | 相当于这个Application的监护人和管理者，负责监控、管理这个Application的所有Attempt在cluster中各个节点上的具体运行，同时负责向Yarn ResourceManager申请资源、返还资源等； |
   | **NodeManager**        | 是Slave上一个独立运行的进程，负责上报节点的状态(磁盘，内存，cpu等使用信息)； |
   | **Container**          | 是yarn中分配资源的一个单位，包涵内存、CPU等等资源，YARN以Container为单位分配资源； |

3. 



