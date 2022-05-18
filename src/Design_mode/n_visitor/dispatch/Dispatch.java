package Design_mode.n_visitor.dispatch;

/**
 * 根据对象的类型而对方法进行的选择，就是分派(Dispatch)。分派又分为两种：静态分派和动态分派。
 *
 * 静态分派(Static Dispatch)发生在编译时期，分派根据静态类型信息发生。静态分派对于我们来说并不陌生，方法重载就是静态分派。
 *
 * 动态分派(Dynamic Dispatch)发生在运行时期，动态分派动态地置换掉某个方法。
 *
 * 下面是一个简单的例子，可以看出分派的概念
 */
public class Dispatch {
    void print(FatherClass c) {
        System.out.print("父类");
    }

    void print(ChildClass c) {
        System.out.print("子类");
    }

    public static void main(String args[]) {
        FatherClass child = new ChildClass();
        new Dispatch().print(child);
        child.print();
    }
}

class FatherClass {
    void print() {
        System.out.println("父类");
    }
}

class ChildClass extends FatherClass {
    @Override
    void print() {
        System.out.print("子类");
    }
}