package afred.javademo.mybatis;

import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by afred on 16/8/7.
 */
public class Test {

    @org.junit.Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mybatis/mybatisContext.xml");

        StudentMapper mapper = context.getBean(StudentMapper.class);

//        Student student = new Student();
//        student.setAge(11);
//        student.setName("a1");
//        int insert = mapper.insert(student);
//        System.out.println("插入 : " + insert);
//
//        Assert.assertTrue(insert > 0);

        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andNameEqualTo("a1");
        List<Student> students = mapper.selectByExample(studentExample);

        System.out.println("查询结果 : " + students);
//        Assert.assertTrue(students.size() == 1);

        System.out.println(students.get(0).getName());

    }

}
