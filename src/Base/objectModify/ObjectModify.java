package Base.objectModify;

import Base.objectModify.entity.Home;
import Base.objectModify.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试对象作为参数，被取出后修改，是能够影响原对象的
 * 1、对象中有一个List，取出后对List对象进行增删，原对象的List一样是增删的
 * 2、对象中有另一个对象，取出后get，set方法进行修改，影响是有效的
 * 3、对象中有一个List，List是另一个对象的集合，取出这个集合中的那个对象进行修改，影响依然有效
 * <p>
 * 4、这些东西其实都是原本默认的东西，内存什么的看多了，容易产生混乱，这里测试一下；
 * <p>
 * 究其根源，其实就是用一个新建了一个引用，去接受了一个地址，再通过getset方法修改地址中的参数，当然是影响的。
 * 所以一定要是原对象的getset方法，直接 = 赋个新地址，是木有用的
 * <p>
 * 无参构造创建对象，对象中的参数都是Null（基本类型除外);
 * 如果担心对象使用默认的无参构造创建后，直接使用对象内参数调用方法，抛出 空指针异常，可在无参构造中进行参数对象的初始化
 * <p>
 * 外界  Home home = new Home(); 堆内存中开辟一块内存区域，创建一个引用，存储的就是这个地址
 * set方法就是      this.home = home;
 * 引用传递（我们不看基本类型，基本类型都是直接修改了值，没什么好说的）
 * 将外界传过来的引用付给对象中的参数，这个参数依然是指向同一个地址的引用
 * get方法 就是直接返回这个参数-----即给出相同的引用，那么外界新建的对象就是这个引用，指向的依然是同一个地址，
 * @author xiongying
 */
public class ObjectModify {
    static int[] all;

    public static void main(String[] args) {
        mathMethod();
        String result = new String("billingCycleid");
        System.out.println(result);

        /**
         * 测试List循环时修改参数，是可以直接使用set方法进行参数修改的
         */
        List<Student> aList = new ArrayList<>();
        Student aStudent = new Student();
        aStudent.setAge(1);
        Student bStudent = new Student();
        bStudent.setAge(2);
        aList.add(aStudent);
        aList.add(bStudent);

        for (Student student : aList) {
            student.setName("学生" + student.getAge());
        }
        System.out.println("修改前" + aList);

        aStudent.setAge(3);
        bStudent.setAge(4);
        aStudent.setHeight(79L);

        // 可以看到上面对于集合中对象的修改，在集合中是完全生效的，因为集合是对象引用的集合，修改引用地址指向的内容
        // 是完全影响List集合中内容的
        System.out.println("修改后" + aList);

    }

    public static int mathMethod() {

        Student student1 = new Student();

//        无参构造默认的是没有初始化参数的，因此参数均为Null（基础类型的参数均有默认值），直接调用方法会抛出空指针异常
//        student1.getGrade().add(1);

//        可以使用多种方式给对象的参数对象进行 初始化
        student1.setGrade(new ArrayList<>());
        //这里再测试一个，直接去对象中的集合参数对象，使用add 赋值，是有效的
        List<Integer> grade = student1.getGrade();
        grade.add(1);
        System.out.println(student1);

        //此处再添加一个测试，测试直接取对象，修改对象的参数，也是有效的
        student1.setHome(new Home());
        Home home = student1.getHome();
        home.setFather("xiong");
        home.setMother("xu");

        //再添加一个测试，修改对象的list中对象的参数，也是有效的
        student1.setHomeList(new ArrayList<>());
        List<Home> homeList = student1.getHomeList();
        homeList.add(home);
        Home home1 = student1.getHomeList().get(0);
        home1.setSister("ding");
        home1.setMother("huang");

        System.out.println(student1.getHome());
        return 1;
    }
}
