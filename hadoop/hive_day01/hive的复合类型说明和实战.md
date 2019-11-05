## hive的复合类型使用说明和实战

#### 1、参数说明

~~~
创建表的时候可以指定每行数据的格式,如果使用的是复合数据类型，还需要指定复合数据类型中的元素分割符
ROW FORMAT DELIMITED 
	[FIELDS TERMINATED BY char [ESCAPED BY char]] 
	[COLLECTION ITEMS TERMINATED BY char]
	[MAP KEYS TERMINATED BY char] 
	[LINES TERMINATED BY char]
		
其中这里 
FIELDS TERMINATED BY char 	         指定每一行记录中字段的分割符
COLLECTION ITEMS TERMINATED BY char  指定复合类型中多元素的分割符
MAP KEYS TERMINATED BY char         指定map集合类型中每一个key/value之间的分隔符
LINES TERMINATED BY char            指定每行记录的换行符，一般有默认 就是\n 
~~~

#### 2、Array类型

* array中的数据为相同类型，例如，假如array A中元素['a','b','c']，则A[1]的值为'b'

* 准备数据文件

  *  t_array.txt  (字段空格分割)

  ~~~
  1 zhangsan beijing,shanghai
  2 lisi shanghai,tianjin
  ~~~

* 建表语法

  ~~~sql
  create table t_array(
  id string,
  name string,
  locations array<string>
  ) row format delimited fields terminated by ' ' collection items terminated by ',';
  ~~~

* 加载数据

  ~~~sql
  load data local inpath '/home/hadoop/t_array.txt' into table t_array;
  ~~~

* 查询数据

  ~~~sql
  select id,name,locations[0],locations[1] from t_array;
  ~~~

#### 3、Map类型

* map类型中存储key/value类型的数据，后期可以通过["指定key名称"]访问

* 准备数据文件

  -  t_map.txt  (字段空格分割)

    ~~~
    1 name:zhangsan#age:30
    2 name:lisi#age:40
    ~~~

* 建表语法

  ~~~sql
  create table t_map(
  id string,
  info map<string,string>
  ) row format delimited fields terminated by ' ' collection items terminated by '#' map keys terminated by ':';
  
  ~~~

* 加载数据

  ~~~sql
  load data local inpath '/home/hadoop/t_map.txt' into table t_map;
  ~~~

* 查询数据

  ~~~sql
  select id,info['name'],info['age'] from t_map;
  ~~~



#### 4、Struct类型

* 可以存储不同类型的数据

  * 例如c的类型为struct{a INT; b INT}，我们可以通过c.a来访问域a

* 准备数据文件

  - t_struct.txt  (字段空格分割)

    ~~~
    1 zhangsan:30:beijing
    2 lisi:40:shanghai
    ~~~

* 建表语法

  ```sql
  create table t_struct(
  id string,
  info struct<name:string, age:int,address:String>
  ) row format delimited fields terminated by ' ' collection items terminated by ':' ;
  ```

- 加载数据

  ```sql
  load data local inpath '/home/hadoop/t_struct.txt' into table t_struct;
  ```

- 查询数据

  ```sql
  select id,info.name,info.age,info.address from t_struct;
  ```





