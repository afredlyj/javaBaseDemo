package afred.javademo.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by afred on 17/5/21.
 */
public class JavassistTest {

    @Test
    public void writeFile() throws IOException, CannotCompileException, NotFoundException {
        ClassPool classPool = ClassPool.getDefault();

        CtClass point = classPool.makeClass("Point");

        point.writeFile();

    }

}
