package afred.javademo.agent;

/**
 * Created by afred on 16/10/30.
 */
public class TestMainJar {

    /**
     * -javaagent:/Users/afred/Documents/gitrepo/javaBaseDemo/jdk-usage/jdk-usage-1.0.jar
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new TransClass().getNumber());
    }
}
