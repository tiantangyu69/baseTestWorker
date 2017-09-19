package guava.collect;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bjlizhitao on 2017/4/11.
 * 排序
 */
public class OrdingTest {
    List<Student> studentList = new ArrayList();

    {
        studentList.add(new Student("张三", 22, 78f));
        studentList.add(new Student("李四", 22, 68f));
        studentList.add(new Student("王五", 22, 88f));
        studentList.add(new Student("赵六", 22, 98f));
        studentList.add(new Student("孙七", 22, 58f));
    }

    /**
     * comparator 排序
     */
    @Test
    public void test() {
        List<Student> orderList = Ordering.from(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return Float.compare(o1.getScore(), o2.getScore());
            }
        }).greatestOf(FluentIterable.from(studentList), 3);

        for (Student student : orderList) {
            System.out.println(student);
        }
    }

    /**
     * comparable 排序
     */
    @Test
    public void test2() {
        List<Student> orderList = Ordering.natural().greatestOf(FluentIterable.from(studentList), 2);

        for (Student student : orderList) {
            System.out.println(student);
        }
    }
}

class Student implements Comparable<Student> {
    private String name;
    private Integer age;
    private Float score;

    public Student(String name, Integer age, Float score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

/*    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("age", age).add("score", score).toString();
    }*/

    public int compareTo(Student o) {
        return ComparisonChain.start()
                .compare(this.score, o.score)
                .compare(this.age, o.age)
                .compare(this.name, o.name)
                .result();
    }
}

