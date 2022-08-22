package Design_mode.n_visitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 访问者模式
 * <p>
 * java的方法重载的分派是根据静态类型进行的（静态分派），在编译时期。
 * java的方法重写是根据实际类型来的（动态分派），编译器编译时并不知道其真实类型，而是运行时动态决定的。
 * <p>
 * 一个对象又叫做它所包含的方法的接收者，java中的动态分派，要调用哪一个方法，
 * 是由这个对象的真实类型决定的。
 * 如果能够根据参数和接收者来动态的决定调用某个方法，这就是动态的多分派语言，
 * <p>
 * ****     如果可以根据这两种方式来动态的决定方法调用，就是动态双重分派。
 * <p>
 * 但前面已经说了，java中重载是根据静态类型进行的（无关动态分派），
 * 所以java只能动态的根据接收者来进行方法调用，即java是动态单分派语言，
 * 如果要实现双重分派，就必须通过设计模式来完成。
 * <p>
 * 核心：
 * 1、访问者模式正是实现双重分派的模式。java中通过两次方法调用来实现两次分派。
 * 2、java原本就是根据参数决定方法调用的，实现功能一。
 * 3、将访问者类作为参数重载方法！重载的方法调用原方法时传入 this参数表明 接收者，实现功能二
 * 4、功能一功能二都有，就是双重分派。
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) {
        // 被访问类的各种实例化集合，便于测试
        ArrayList<User> users = new ArrayList<User>();
        users.add(new UserOrdinary("普通用户短反馈"));
        users.add(new UserOrdinary("这是一个普通用户的比较长的反馈"));
        users.add(new UserVIP("VIP用户的短反馈"));
        users.add(new UserVIP("VIP用户的比较长的反馈反馈"));

        // 循环调用方法
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            // 传入访问者类作为参数，具体实例在自己本身的类中调用这个方法时，会传入this本身作为
            // 访问者类中方法的参数，间接实现了根据接收者来动态的决定调用某个方法！
            iterator.next().accept(new APPOwner());
        }
    }
}