package afred.javademo.agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by afred on 16/10/30.
 */
public class Transformer implements ClassFileTransformer {

    public static final String classNumberReturns2 = "/Users/afred/Documents/gitrepo/javaBaseDemo/jdk-usage/TransClass.class";

    public static byte[] getBytesFromFile(String fileName)  {

        try {
            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(file);
            long length = file.length();

            System.out.println("length : " + length);
            byte[] bytes = new byte[(int) length];

            int offset = 0;
            int numRead = 0;

            while (offset < bytes.length && ((numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0)) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }

            inputStream.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!className.equals("afred/javademo/agent/TransClass")) {
            System.out.println("class name : " + className);
            return null;
        }

        System.out.println("get bytes from file : " + classNumberReturns2);

        return getBytesFromFile(classNumberReturns2);
    }
}
