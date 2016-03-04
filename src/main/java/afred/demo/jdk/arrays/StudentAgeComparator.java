package afred.demo.jdk.arrays;

import java.util.Comparator;

/**
 * Created by Afred on 15/3/18.
 */
public class StudentAgeComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
    }
}
