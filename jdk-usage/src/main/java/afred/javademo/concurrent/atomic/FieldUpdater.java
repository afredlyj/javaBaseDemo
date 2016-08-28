package afred.javademo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Afred on 15/8/6.
 */
public class FieldUpdater {

    public static void main(String[] args) throws NoSuchFieldException {
        AtomicIntegerFieldUpdater<Student> ageUpdater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");

        Student student = new Student();
        student.setAge(1);
        student.setName("afred");
        student.setRank(1);

        ageUpdater.incrementAndGet(student);

        try {
            AtomicIntegerFieldUpdater<Student> rankUpdater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "rank");
            rankUpdater.incrementAndGet(student);
        } catch (IllegalArgumentException e) {

        }

        System.out.println("修改之后的值 : " + student);
    }

}

class Student {

    public volatile Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public volatile int age;

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

    @Override
    public String toString() {
        return "Student{" +
                "rank=" + rank +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
