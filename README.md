
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
