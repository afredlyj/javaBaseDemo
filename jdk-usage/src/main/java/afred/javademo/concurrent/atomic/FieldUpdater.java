package afred.demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Afred on 15/8/6.
 */
public class FieldUpdater {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Student> updater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");

        Student student = new Student();
        student.setAge(1);
        student.setName("afred");

        updater.incrementAndGet(student);

    }

}

class Student {
    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
