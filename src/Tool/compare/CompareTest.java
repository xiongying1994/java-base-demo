package Tool.compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * @Author: xiongying
 * @Date: 2020/5/27 下午3:32
 *
 * 比较方法的实验，解决 Comparable，Comparator 等的混淆问题
 *
 * Comparable是排序接口，若一个类实现了Comparable接口，就意味着“该类支持排序”。而Comparator是比较器，我们若需要控制某个
 * 类的次序，可以建立一个“该类的比较器”来进行排序。
 *
 * Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”。
 *
 * 两种方法各有优劣， 用Comparable 简单， 只要实现Comparable 接口的对象直接就成为一个可以比较的对象，但是需要修改源代码。
 * 用Comparator 的好处是不需要修改源代码， 而是另外实现一个比较器， 当某个自定义的对象需要作比较的时候，把比较器和对象一起
 * 传递过去就可以比大小了， 并且在Comparator 里面用户可以自己实现复杂的可以通用的逻辑，使其可以匹配一些比较简单的对象，那样就
 * 可以节省很多重复劳动了。
 *
 *
 */
public class CompareTest {

    public static void main(String[] args) {
        CompareTest compareTest = new CompareTest();
        compareTest.test();
    }

    /**
     * Integer,String 均已经继承了 Comparable 接口
     */
    public void test(){
        // 数组排序
        int[] ints = {50,1,4,8,3};
        String[] strings = {"q","a","c"};

        Arrays.sort(ints);

        for (String val : strings) {
            System.out.print(val + " ");
        }
        System.out.println();

        for (int i =0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();

        // 集合排序
        List<Integer> list = Arrays.asList(50,1,4,8,3);
        Collections.sort(list);

        System.out.println(list);

        // 自定义排序
        Integer[] intss = {50,1,4,8,3}; //必须使用包装类，包装类才是实现 Comparable 的，上面都是自动装箱
        Arrays.sort(intss, new Comparator<Integer>() {

            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        });

    }

    /**
     * 自定义对象继承 Comparable 接口，实现 compareTo 方法进行对象的排序
     */
    public void test2(){
        Student s1 = new Student(19, "张三");
        Student s2 = new Student(18, "李四");
        Student s3 = new Student(21, "王五");

        Student[] students = {s1,s2,s3};

        int i = s1.compareTo(s2);

        if (i >= 0) {
            System.out.println(s1.getName() + "年龄大");
        } else {
            System.out.println(s2.getName() + "年龄大");
        }

        for (Student val : students) {
            System.out.println(val + " ");
        }

        Arrays.sort(students);
        System.out.println();

        for (Student val : students) {
            System.out.println(val + " ");
        }
    }

    /**
     * 自定义排序器
     * 当一个类没有实现 Comparable 接口的时候，可以自定义一个排序类给它，这就使用到了 Comparator 接口
     */
    public void test3(){
        // 对象 已经实现 Comparable 接口
        Student s1 = new Student(19, "张三");
        Student s2 = new Student(18, "李四");
        Student s3 = new Student(21, "王五");

        Student[] students = {s1,s2,s3};

        Arrays.sort(students, new Comparator<Student>() {

            @Override
            public int compare(Student a, Student b) {
                return a.compareTo(b);
            }
        });

        // 对象 没有实现 Comparable 接口，自定义排序方法
        User u1 = new User(19, "张三");
        User u2 = new User(18, "李四");
        User u3 = new User(21, "王五");

        User[] users = {u1,u2,u3};

        Arrays.sort(users, new Comparator<User>() {

            @Override
            public int compare(User a, User b) {
                return a.getAge() - b.getAge();
            }
        });
        Arrays.sort(users,(a,b) -> {
            if (a.getAge() > b.getAge()) {
                return 1;
            } else {
                return 0;
            }
        });

        Arrays.sort(users, Comparator.comparingInt(User::getAge));

        Arrays.sort(users, Comparator.comparingInt(user -> user.getAge()));

        Arrays.sort(users, Comparator.comparingInt((User user) -> user.getAge()));

        Arrays.sort(users, Comparator.comparingInt((User user) -> {
            return user.getAge();
        }));

        Arrays.sort(users, Comparator.comparingInt(new ToIntFunction<User>() {
            @Override
            public int applyAsInt(User user) {
                return user.getAge();
            }
        }));

        Arrays.sort(users, Comparator.comparingInt((User user) -> user.getAge()));

        Arrays.sort(users, Comparator.comparingInt(User::getAge));
    }

}
