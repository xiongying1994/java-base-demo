package Tool.lambda;

import Tool.compare.User;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/**
 * Lambada表达式的应用
 */
public class TestLambada {
    public static void main(String[] args) {
        // 方法引用 :: 是Lambda表达式的另一种简写方式
        //类名::静态方法
        //对象::实例方法
        //类名::实例方法

        // 这里其实就是实例化 Supplier，并且覆写了接口中的方法
        Supplier<Double> supplier = new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        };

        Supplier<Double> supplier2 = () -> Math.random();//方法引用

        Supplier<Double> supplier3 = Math::random;

        // 通过实例化的对象，调用覆写的方法
        Double a = supplier3.get();

        System.out.println("---------------  2  ----------------------");

        // 这里展示的是带参数的 覆写的方法，其实是差不多的
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Consumer<String> consumer2 = (s) -> System.out.println(s);
        Consumer<String> consumer3 = System.out::println;// 方法引用也没有参数

        consumer3.accept("bbbb");

        System.out.println("-------------  3  -----------------");
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


        System.out.println("---------------------- 4 --------------------------");
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };

        Comparator<Integer> comparator2 = (x, y) -> x.compareTo(y);
        Comparator<Integer> comparator3 = Integer::compareTo;
        comparator3.compare(1, 2);

        System.out.println("---------------------  5   ---------------------------");
        User u1 = new User(19, "张三");

        Optional.ofNullable(u1).ifPresent(System.out::println);
        Optional.ofNullable(u1).ifPresent(x -> System.out.println(x));
        Optional.ofNullable(u1).ifPresent(x -> {
            System.out.println(x);
        });
        Optional.ofNullable(u1).ifPresent(new Consumer<User>() {
            @Override
            public void accept(User x) {
                System.out.println(x);
            }
        });

        System.out.println("---------------------  6   ---------------------------");

        Map ab = new HashMap<Object, Object>();
        ab.put("A: ","a");
        ab.put("B: ","b");
        ab.put("C: ","c");

        ab.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " +value));

        ab.forEach(new BiConsumer() {
            @Override
            public void accept(Object x, Object y) {
                System.out.println("Key: " + x + ", Value: " + y);
            }
        });
    }

    public static void UserTest(String[] args) {

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

        Arrays.sort(users, (a, b) -> a.getAge() - b.getAge());

        Arrays.sort(users, (a, b) -> {
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

    public static <T> Comparator < T > comparingInt(ToIntFunction < ? super T > keyExtractor) {
        Objects.requireNonNull(keyExtractor);

        return (Comparator<T> & Serializable)
                (T c1, T c2) -> {
                    return Integer.compare(keyExtractor.applyAsInt(c1), keyExtractor.applyAsInt(c2));
                };
    }

}