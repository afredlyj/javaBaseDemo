package afred.demo.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-01-07 
 * Time: 17:59
 */
@XmlRootElement(name = "student")
public class Student implements Serializable {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @XmlElement

    public int getAge() {
        return 2222;
    }

//@XmlElement
    public void setAge(int age) {
        this.age = age;
    }
}
