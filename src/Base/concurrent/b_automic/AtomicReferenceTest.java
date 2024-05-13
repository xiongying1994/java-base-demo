package Base.concurrent.b_automic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference：原子更新引用类型。
 *
 * @author xiongying
 */
public class AtomicReferenceTest {
    public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("abc", 15);
        atomicUserRef.set(user);
        User updateUser = new User("tom", 17);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getOld());
    }

    static class User {
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
