package afred.demo.jdk.arrays;

import java.util.Arrays;

/**
 * Created by Afred on 15/3/18.
 */
public class ArraysTest {


    public static void main(String[] args) {

        Student s1 = new Student("h", 2);

        Student s2 = new Student("e", 3);
        Student s3 = new Student("l", 3);
        Student s4 = new Student("o", 4);

        Student[] arr = new Student[]{
                s1, s2, s3, s4
        };

        // strategy pattern
        Arrays.sort(arr, new StudentAgeComparator());

        for (Student s : arr) {
            System.out.print(s + ", ");
        }
        System.out.println();

        Arrays.sort(arr, new StudentNameComparator());
        for (Student s : arr) {
            System.out.print(s + ", ");
        }

        System.out.println();

        Arrays.sort(arr);
        for (Student s : arr) {
            System.out.print(s + ", ");
        }
    }

}
