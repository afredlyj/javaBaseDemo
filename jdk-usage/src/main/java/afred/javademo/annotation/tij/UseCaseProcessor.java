package afred.javademo.annotation.tij;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Afred on 15/3/3.
 */
public class UseCaseProcessor {

    public static void process(List<Integer> useCases, Class<?> cl) {
        for (Method method : cl.getDeclaredMethods()) {
            UseCase uc = method.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("use case id : " + uc.id() + ", description : " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }

        for (int i : useCases) {
            System.out.println("Warning : Missing use case " + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCase = new ArrayList<Integer>();
        Collections.addAll(useCase, 47, 48, 49, 50);
        process(useCase, PasswordUtils.class);
    }

}
