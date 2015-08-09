package afred.demo.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.Date;

/**
 * Created by winnie on 2015-03-26 .
 */
public class Test {


    public static void main(String[] args) {

        Mapper mapper = new DozerBeanMapper();

        StudentVO studentVO = new StudentVO();

        studentVO.setAge(1);
        studentVO.setName("hello");

        System.out.println("student vo : " + studentVO);

        StudentDTO studentDTO = new StudentDTO();
        mapper.map(studentVO, studentDTO);

        System.out.println("student dto : " + studentDTO);


        Info info = new Info();
        info.setCreateDate(new Date());
//        info.setGender(GenderType.female);
        info.setId(1);

        User user = new User();
        user.setInfo(info);
        user.setId(1);
        user.setName("helloworld");
        user.setPassword("654321");


        UserVO vo = new UserVO();
        mapper.map(user, vo);

        System.out.println("user : " + user);
        System.out.println("vo : " + vo);
    }



}

