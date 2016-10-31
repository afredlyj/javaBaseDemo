package afred.javademo.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by afred on 16/10/30.
 */
public class PreMain {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("add premain");
        instrumentation.addTransformer(new Transformer());

    }

}
