package Tool.lambda;

import Tool.compare.User;

import java.util.*;

/**
 * @Author: xiongying
 * @Date: 2021/12/2 上午10:20
 */
public class LambdaDemo {
    /**
     * 循环简单的集合
     */
    public void foreachTest() {
        Map ab = new HashMap<Object, Object>();
        ab.put("A: ","a");
        ab.put("B: ","b");
        ab.put("C: ","c");
        ab.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " +value));
    }

    /**
     * optional的工具类调用
     */
    public void optionalTest() {
        User u1 = new User(19, "张三");

        Optional.ofNullable(u1).ifPresent(System.out::println);
    }

    /**
     * 比较compare
     */
    public void compareTest() {
        Comparator<String> stringComparator = new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                // 你对一个函数式接口中的抽象方法重写时，如果说你传的这两个参数
                // 一个参数作为了调用者，一个参数作为了 传入者
                // 那么你也可以使用方法引用来简写Lambda表达式
                return s1.compareTo(s2);
            }
        };
        Comparator<String> stringComparator2 = (s1, s2) -> s1.compareTo(s2);
        stringComparator2.compare("a", "b");
        Comparator<String> stringComparator3 = String::compareTo;

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };
    }

    /**
     * 数组排序
     */
    public void arraysTest() {
        User u1 = new User(19, "张三");
        User u2 = new User(18, "李四");
        User u3 = new User(21, "王五");

        User[] users = {u1, u2, u3};

        Arrays.sort(users, new Comparator<User>() {
            @Override
            public int compare(User a, User b) {
                return a.getAge() - b.getAge();
            }
        });
    }
}
