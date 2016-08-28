package afred.demo.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-01-07 
 * Time: 17:59
 */
public class Test {

    public static void main(String[] args) throws JAXBException {

        Student student = new Student();
//        student.setAge(11);
        student.setName("hellow");

        JAXBContext context = JAXBContext.newInstance(Student.class);

        Marshaller marshaller = context.createMarshaller();

        StringWriter writer = new StringWriter();

        marshaller.marshal(student, writer);

        String data = writer.toString();
        System.out.println("结果 : " + data);

//        Unmarshaller unmarshaller = context.createUnmarshaller();
//       Student student1 = (Student) unmarshaller.unmarshal(new StringReader(data));
//
//        System.out.println(student1.getAge());


    }

}
