package afred.javademo.arrays;

import java.util.Comparator;

/**
 * Created with demo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-08-12 
 * Time: 16:37
 */
public class StudentNameComparator implements Comparator<Student> {
    @Override public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
