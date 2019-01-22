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
public class Test {
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
            
            List<Member> result = memberDao.selectByName("Bob");
            System.out.println(Arrays.toString(result.toArray()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }







}
