package Base.concurrent.b_automic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
 *
 * @author xiongying
 */
public class AtomicReferenceFieldUpdaterTest {
    public static void main(String[] args) throws Exception {
        AtomicReferenceFieldUpdater<Dog, String> updater = AtomicReferenceFieldUpdater.newUpdater(Dog.class,
                String.class, "name");
        Dog dog1 = new Dog();
        updater.compareAndSet(dog1, dog1.name, "test");
        System.out.println(dog1.name);

    }

}

class Dog {
    volatile String name = "dog1";

}
