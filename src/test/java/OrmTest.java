import com.demo.dao.MemberDao;
import com.demo.model.Member;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
* @Description:    测试
* @Author:         wanbo
* @CreateDate:     2019/1/19 15:29
* @UpdateRemark:
* @Version:        1.0.0
*/
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrmTest {

    //ORM(对象关系映射    Object Relation Mapping)
    //说的就是讲已经持久化的数据内容转换为一个Java对象
    //用Java对象来描述对象与对象之间的关系和数据内容

    //Hibernate MyBatis JPA  SpringJDBC

    //Hibernate 全自动档  不需要写一句SQL语句、烧油（牺牲性能）
    //MyBatis  手自一体（半自动） 支持单表映射，多表关联需要配置，轻量级一些
    //SpringJDBC  手动挡 包括SQL语句，映射都是要自己实现的（最省油的）


    //为什么要自己写ORM框架
    //1、解决实际的业务问题（根据业务需要）
    //2、自定义需求，如果要直接第三方开源框架的话，需要进行二次开发
    //3、解决团队成员之间水平参差不齐的问题
    //4、可以实现统一的管理、监控、排错等等一系列底层操作

    @Autowired MemberDao memberDao;


    @Test
    @Ignore
    public void test(){
        System.out.println(memberDao);
    }

    /**
     * @Description 测试新增
     * @Author wanbo
     * @date 2019/1/19
     * @param
     * @return void
     **/
    @Test
    public void testInsert(){
        try {
            for (int age = 25; age < 35; age++) {
                Member member = new Member();
                member.setAge(age);
                member.setName("Bob");
                member.setAddr("Cheng Du");
                memberDao.insert(member);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Description 测试查询
     * @Author wanbo
     * @date 2019/1/19
     * @param
     * @return void
     **/
    @Test
    public void testSelect(){

        try {
//            List<Member> result = memberDao.selectAll();
//            System.out.println(Arrays.toString(result.toArray()));

            List<Member> result = memberDao.selectByName("Tom");
            System.out.println(Arrays.toString(result.toArray()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }







}
