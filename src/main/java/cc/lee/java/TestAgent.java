package cc.lee.java;

import java.lang.instrument.Instrumentation;

/**
 * Created by bjlizhitao on 2016/7/15.
 */
public class TestAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new TestClassFileTransformer());
    }
}
