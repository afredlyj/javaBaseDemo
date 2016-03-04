package afred.demo.kaptcha;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-01-15 
 * Time: 11:30
 */
public class Test {

    @org.junit.Test public void oneProducer() throws IOException  {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-kaptcha.xml");
        Producer producer = (Producer) context.getBean("captchaProducer");

        String text = producer.createText();
        System.out.println("text : " + text);

        FileOutputStream stream = new FileOutputStream(System.currentTimeMillis() + "kaptcha.jpg");

        BufferedImage bi = producer.createImage(text);

        ImageIO.write(bi, "jpg", stream);

        stream.close();
    }

    @org.junit.Test public void multilProducer() throws IOException {

        /**
         *
         *
         <prop key="kaptcha.border">no</prop>
         <prop key="kaptcha.textproducer.font.color">0,146,95</prop>
         <prop key="kaptcha.image.width">64</prop>
         <prop key="kaptcha.image.height">32</prop>
         <prop key="kaptcha.textproducer.font.size">20</prop>
         <prop key="kaptcha.textproducer.char.space">1</prop>
         <prop key="kaptcha.textproducer.char.length">4</prop>
         <prop key="kaptcha.textproducer.font.names">Courier,Georgia,Times New Roman,Verdana,Comic Sans MS</prop>
         <prop key="kaptcha.background.clear.from">white</prop>
         <prop key="kaptcha.background.clear.to">white</prop>
         <prop key="kaptcha.obscurificator.impl">afred.demo.kaptcha.WaterRipple</prop>
         <prop key="kaptcha.noise.color">0,146,95</prop>
         *
         */
        Producer producer = new DefaultKaptcha();
//        Config config = new Config();

    }

}
