package Tool.stream;

import Base.objectModify.entity.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.compare;

/**
 * @Author: xiongying
 * @Date: 2022/8/23 15:39
 */
public class StreamTestDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");

        /**
         * 流的操作分为两种类型
         * 1、中间操作，可以有对个，每次返回一个新的流，可以进行链式操作。
         *      例如 distinct() 方法就是一个中间操作，用来去重，会返回一个新的流程
         * 2、终端操作，只能有一个，每次执行完，这个流也就用完了，只能放在最后。
         *      count()方法是一个终端操作，返回流中的元素个数
         *
         * 流在中间操作时不会立即执行，等到终端操作时，才开始真正地遍历，用于映射、过滤等。
         *      一次遍历执行多个操作，性能就大大提高了。
         */
        long count = list.stream().distinct().count();
        System.out.println(count);

        /**
         * 创建流
         * 1、Arrays.stream() 数组创建流
         * 2、Stream.of()  自身工具方法  ---》 底层实际用的也是Arrays.stream()
         * 3、list.stream() 集合通用方法 在Collection接口中
         * 4、list.parallelStream()  创建的是并发流，默认使用的是ForkJoinPool.commonPool()线程池
         */
        String[] arr = new String[]{"aaaa", "bbbb", "cccc"};

        Stream stream = Arrays.stream(arr);

        stream = Stream.of("aaaa", "bbbb", "cccc");
        // 可以通过这个方式快速创建集合
        List<String> qk = Stream.of("1", "2", "3").collect(Collectors.toList());

        stream = list.stream();

        stream = list.parallelStream();

        List<String> s = Arrays.asList("1", "2", "3");

        s.add("4");

        /**
         * 常用方法：
         * 1、filter() 中间操作
         * 过滤通过filter()方法可以从流中筛选出我们想要的元素。
         * filter() 方法接收的是一个 Predicate（Java 8 新增的一个函数式接口，接受一个输入参数返回一个布尔值结果）类型的参数
         *
         * 因此，我们可以直接将一个 Lambda 表达式传递给该方法
         * 比如说 element -> element.contains("王") 就是筛选出带有“王”的字符串。
         *
         * 2、forEach() 终端操作
         * forEach() 方法接收的是一个 Consumer（Java 8 新增的一个函数式接口，接受一个输入参数并且无返回的操作）类型的参数
         */
        list.stream().filter(element -> element.contains("a"))
                .forEach(System.out::println);
        // 更常用的是筛选后使用collec()方法重新生成集合
        List<String> toList = list.stream().filter(element -> element.contains("a"))
                .collect(Collectors.toList());

        /**
         * 3、map() 中间操作
         * 映射  -->  把一个流中的元素转化成新的流中的元素
         * map() 方法接收的是一个 Function（Java 8 新增的一个函数式接口，接受一个输入参数 T，返回一个结果 R）类型的参数
         */
        list.stream().map(String::length)
                .forEach(System.out::println);

        /**
         * 4、Match() 终端操作 --> 返回一个boolean
         * 匹配 --> 对元素进行匹配
         * anyMatch()，只要有一个元素匹配传入的条件，就返回 true。
         * allMatch()，只有有一个元素不匹配传入的条件，就返回 false；如果全部匹配，则返回true。
         * noneMatch()，只要有一个元素匹配传入的条件，就返回 false；如果全部不匹配，则返回 true。
         */
        boolean anyMatchFlag = list.stream().anyMatch(element -> element.contains("aaaa"));
        boolean allMatchFlag = list.stream().allMatch(element -> element.length() > 2);
        boolean noneMatchFlag = list.stream().noneMatch(element -> element.endsWith("bb"));


        System.out.println(anyMatchFlag + "," + allMatchFlag + "," + noneMatchFlag);

        /**
         * 5、reduce() 中间操作
         * 组合 --> 把 Stream 中的元素组合起来
         * Optional  没有起始值，只有一个参数，就是运算规则，此时返回 Optional。
         *
         * T reduce(T identity, BinaryOperator)  有起始值，有运算规则，两个参数，此时返回的类型和起始值类型一致。
         */
        Integer[] ints = {0, 1, 2, 3};
        List<Integer> intList = Arrays.asList(ints);

        Optional<Integer> optional = intList.stream().reduce((a, b) -> a + b);
        Optional<Integer> optional1 = intList.stream().reduce(Integer::sum);
        System.out.println(optional.orElse(0));
        System.out.println(optional1.orElse(0));

        int reduce = intList.stream().reduce(6, (a, b) -> a + b);
        int reduce1 = intList.stream().reduce(6, Integer::sum);
        System.out.println(reduce + ", " + reduce1);

        // 额外测一下
        intList.stream().map(x -> compare(x, 1)).forEach(System.out::println);

        /**
         * 6、collect() 终端方法
         * 转换  -->  将流转换为集合或数组
         *
         * toArray() 方法可以将流转换成数组
         * collect() 方法将流转换成新的集合
         *      Collectors 是一个收集器的工具类，内置了一系列收集器实现：
         *      比如说 toList()方法将元素收集到一个新的java.util.List 中；
         *      比如说 toCollection()方法将元素收集到一个新的java.util.ArrayList 中；
         *      比如说 joining() 方法将元素收集到一个可以用分隔符指定的字符串中。
         *      比如说 groupingBy(T, F) 方法将元素分组，T为分组元素，F为分组创建方法
         */

        String[] strArray = list.stream().toArray(String[]::new);

        List<Integer> list1 = list.stream().map(String::length).collect(Collectors.toList());
        List<String> list2 = list.stream().collect(Collectors.toCollection(ArrayList::new));

        String str = list.stream().collect(Collectors.joining(","));
        System.out.println(strArray + "," + list1 + "," + list2 + "," + str);

        // 创建student集合，测试 groupingBy 功能
        Student stuA = new Student(15, "A", new ArrayList<>());
        Student stuB = new Student(18, "B", new ArrayList<>());

        List<Student> stuList = Stream.of(stuA, stuB).collect(Collectors.toList());

        Map<Integer, List<Student>> groupMap = stuList.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.toList()));


        /**
         * 7、toMap 终端方法
         * List转Map
         * 用Collectors的toMap方法转换List，一般会遇到两个问题。
         * （1）转换map，key重复问题；
         *  代码中使用(key1,key2)->key2表达式可以解决此类问题，如果出现重复的key就使用key2覆盖前面的key1,也可以定义成(key1,key2)->key1，保留key1,根据自己的业务场景来调整。
         * （2）空指针异常，即转为map的value是null。这个可以用filter过滤；
         *
         * Function是一个接口,identity()就是Function接口的一个静态方法。
         *      Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式。
         *      在下面案例中，Function.identity()返回的就是student对象
         */
        Map<String, Student> userMap= stuList.stream()
                .collect(Collectors.toMap(Student::getName, Function.identity(),(key1, key2)->key2));
    }


}
