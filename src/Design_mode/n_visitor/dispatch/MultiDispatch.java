package Design_mode.n_visitor.dispatch;

/**
 * 从java的静态分派和动态分派 来看一下简单的访问者模式
 * 即实现双重分派
 *
 */
public class MultiDispatch {
    public static void main(String args[]) {
        Father child1 = new Child();
        child1.print();

        Child child2 = new Child();
        child2.print();
        child2.print(new Vistor());
    }
}

class Father {
    void print() {
        System.out.println("父类");
    }
}

class Child extends Father {
    @Override
    void print() {
        System.out.println("子类");
    }

    /**
     * 这一步是核心
     * @param c
     */
    void print(Vistor c) {
        c.print(this);
    }
}

class Vistor {
    public void print(Child child) {
        child.print();
    }
}