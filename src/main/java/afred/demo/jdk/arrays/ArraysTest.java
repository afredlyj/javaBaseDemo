package afred.demo.jdk.arrays;

import java.util.Arrays;

/**
 * Created by Afred on 15/3/18.
 */
public class ArraysTest {


    public static void main(String[] args) {

        Student s1 = new Student("h", 2);

        Student s2 = new Student("e", 3);

        Student[] arr = new Student[]{
                s1, s2
        };

        // strategy pattern
        Arrays.sort(arr, new StudengComparator());

        for (Student s : arr) {
            System.out.println("student info : " + s);
        }

    }

}
