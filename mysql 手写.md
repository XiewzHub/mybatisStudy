mysql 手写



## 分使用端与架构端

- 使用端

  创建配置文件，数据库连接配置、sqlmapper配置文件

  sql语句信息mapper

  

  sqlMapConfig.xml : 存放数据源信息，引入mapper.xml （存放）

  Mapper.xml : sql语句的配置文件信息 

  

- 架构端

  1.读取配置文件 

  读取完成以后以流的形式存在，我们不能将读取到的配置信息以流的形式存放在内存中，不好操作，可 以创建javaBean来存储 

  (1)Configuration : 存放数据库基本信息、Map<唯一标识，Mapper> 唯一标识:namespace + "." + id 

  (2)MappedStatement:sql语句、statement类型、输入参数java类型、输出 参数java类型 

   

  2.解析配置文件

  创建sqlSessionFactoryBuilder类:
   方法:sqlSessionFactory build(): 第一:使用dom4j解析配置文件，将解析出来的内容封装到Configuration和MappedStatement中 第二:创建SqlSessionFactory的实现类DefaultSqlSession 

  

  3.创建SqlSessionFactory:
   方法:openSession() : 获取sqlSession接口的实现类实例对象 

  

  4.创建sqlSession接口及实现类:主要封装crud方法 方法:selectList(String statementId,Object param):查询所有 selectOne(String statementId,Object param):查询单个 具体实现:封装JDBC完成对数据库表的查询操作 



## 代码步骤

框架层

1. 加载配置文件：根据配置文件路径，加载字节流到内存

2. 创建两个javaBean（容器对象） 存放的是对配置文件解析出来的内容

   Configuration：核心配置类，放sqlMapConfig.xml的内容

   MappedStatement 映射配置类 放mapper.xml内容

3. 解析配置文件：dom4j

   创建SqlSessionFactoryBuilder。方法：build(InputStream in)

   一 使用dom4j解析配置文件，装载到容器对象

   二 创建SqlSessionFactory对象；生成sqlSession （**工厂模式**）

4. DefaultSqlSessionFactory实现SqlSessionFactory

   生产sqlSession

5. 创建SqlSession接口及实现类 DefaultSession

   定义对数据库的curd操作：selectList() selectOne() update() delete()

6. 创建Executor接口及实现类SimpleExecutor实现类

   通用的query(Configuration ，MappedStatement，Object… param) 执行的就是JDBC操作代码



代码执行流程

1. 先加载配置文件

2. SqlSessionFactoryBuilder通过配置文件流构建SqlSessionFactory对象  得到工厂对象

3. 由工厂对象创建SqlSession

4. 最后由SqlSession提供增删查改方法语句

   还提供getMapper方法(**代理的dao层实现**)

5. sqlSession底层使用SimpleExecutor提供的query方法，其底层使用jdbc操作数据库





代码图









## sql准备

```sql
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lucy', '123', '2019-12-12');
INSERT INTO `user` VALUES ('2', 'tom', '123', '2019-12-12');

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordertime` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '2019-12-12', '3000', '1');
INSERT INTO `orders` VALUES ('2', '2019-12-12', '4000', '1');
INSERT INTO `orders` VALUES ('3', '2019-12-12', '5000', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL,
  `roleDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'CTO', 'CTO');
INSERT INTO `sys_role` VALUES ('2', 'CEO', 'CEO');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`roleid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('2', '2');
```









