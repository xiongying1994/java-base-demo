package Base.objectModify;

import Base.objectModify.entity.Student;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.mutable.MutableLong;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试对象的传递
 * 直接传递的方式是没有办法从方法中将对象传出到方法调用的地方的
 * 需要使用
 * commons-beanutils jar包下的
 * import org.apache.commons.beanutils.BeanUtils;     性能极差，尽量不要使用-----添加了大量的日志，监控等内容拖慢了速度
 * import org.apache.commons.beanutils.PropertyUtils;
 * 两个工具类
 * <p>
 * 注意：
 * 1、BeanUtils提供类型转换，可在八大基础类型之间互转，注意，java.util.Date是不被支持的，而它的子类java.sql.Date是被支持的
 * 2、PropertyUtils在遇到同名但类型不同时，会抛出 argument mistype 异常
 * 3、commons-beanutils-1.9.3.jar 加载后，也需要加载
 * commons-logging-1.2.jar 提供支持，否则无法使用工具类
 *
 * @author xiongying
 */
public class ObjectTransmit {
    public static void main(String[] args) {
        Student students = new Student();
        ObjectTransmit objectTransmit = new ObjectTransmit();
        //调用方法，传入对象获取属性
        objectTransmit.method(students);

        if (null == students) {
            System.out.println("lalalala");
        }
        //list也可作为属性传出，限基本类型，对象类型未测试
        List<Integer> grage = students.getGrade();
        grage.add(12);
        grage.add(15);
        System.out.println(students);

        //测试Mutable系列的一些方法
        objectTransmit.mutableTest();
    }

    /**
     * 方法
     *
     * @param student
     */
    public void method(Student student) {
        try {
            //使用工具类 复制对象
//            student = studentCode();
            PropertyUtils.copyProperties(student, studentCode());
            /**
             * 在这个方法中，直接getter，setter 是可以对传入的对象进行修改的
             * 但是，在面对这个方法调用其他方法获得对象（等同于在方法中new Student() 然后传递），然后获取属性时
             * 1、直接 = 是无法取得属性的; 原因其实很简单，= 是用来赋新地址的，若使用
             *    student = studentCode();
             *    就相当于 student = new Student(); 是给形参一个新的引用，指向了新的地址，与原来的 实参就毫无关系了
             *
             * 2、依次取出属性值，再getter，setter是可以的
             * 3、使用工具类！
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象产生的方法
     *
     * @return
     */
    public Student studentCode() {
        List<Integer> grage = new ArrayList<>();
        grage.add(34);
        return new Student(1, "important/junzong", grage);
//        return null;
    }

    /**
     * 测试MutableLong等的一些方法
     */
    public void mutableTest() {
        MutableLong code = new MutableLong();
        Long a = code.longValue();
        System.out.println("MutableLong初始化后的默认值是：" + a);

        code.setValue(1L);
        mutable(code);
        System.out.println(code);
    }

    public void mutable(MutableLong code) {
        for (Long i = 0L; i < 10L; i++) {
            code.getAndAdd(i);
        }
    }
}
