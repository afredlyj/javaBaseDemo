package afred.javademo.threadlocal;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/10/3.
 */
public class InheritableTest {

    class Student {
        private String name;

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void test() throws InterruptedException {
        final InheritableThreadLocal<Student> inheritableThreadLocal = new InheritableThreadLocal<>();

        Student student = new Student();
        student.setName("afred");
        student.setAge(18);

        // 初始化  t.inheritableThreadLocals
        inheritableThreadLocal.set(student);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("in child thread ");
                System.out.println(inheritableThreadLocal.get());

                Student newStudent = new Student();
                newStudent.setAge(80);
                newStudent.setName("NA");
                inheritableThreadLocal.set(newStudent);
                System.out.println(inheritableThreadLocal.get());
            }
        };

        // 单线程执行两个不同的task
        //
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 创建线程时,会浅拷贝父线程的inheritableThreadLocals
        // 线程池中的线程修改inheritableThreadLocal
        executorService.submit(runnable);

        TimeUnit.SECONDS.sleep(1);

        // 由于线程池只有一个线程,所以这次执行仍然是 age == 80
        executorService.submit(runnable);

        TimeUnit.SECONDS.sleep(1);

        System.out.println("in parent thread ");

        // 父线程的t.inheritableThreadLocals并没有被修改
        System.out.println(inheritableThreadLocal.get());


    }

}
