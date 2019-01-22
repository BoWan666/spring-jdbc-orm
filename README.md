# 基于Jdbc封装的一款开源数据库ORM框架
    ORM(对象关系映射    Object Relation Mapping)
    说的就是讲已经持久化的数据内容转换为一个Java对象
    用Java对象来描述对象与对象之间的关系和数据内容

## 为什么要自己写ORM框架
​    1、解决实际的业务问题（根据业务需要）

​    2、自定义需求，如果要直接第三方开源框架的话，需要进行二次开发

​    3、解决团队成员之间水平参差不齐的问题

​    4、可以实现统一的管理、监控、排错等等一系列底层操作
​    

## 用法
1、jar包引入

    '<repositories>
		<repository>
			<id>sonatype</id>
			<name>Sonatype Groups</name>
			<url>https://oss.sonatype.org/content/groups/public/</url>
		</repository>
	</repositories>
	
	<!-- 依赖定义 -->
	<dependencies>
		<dependency>
			<groupId>com.github.BoWan666</groupId>
			<artifactId>spring-jdbc-orm</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
    </dependencies>'
2、用法
例如：
`@Repository
 public class MemberDao extends BaseSupportDao<Member, Integer> {`

 需要继承BaseSupportDao类，并且传入实体类Entity(Member)，以及主键PK的封装类型(Integer)

 
未完待续。。。
