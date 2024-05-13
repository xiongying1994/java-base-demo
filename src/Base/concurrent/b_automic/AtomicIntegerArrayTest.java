package Base.concurrent.b_automic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 并发包中的原子操作类(Atomic系列)，从JDK 1.5开始提供了java.util.concurrent.atomic包，
 * 在该包中提供了许多基于CAS实现的原子操作类，用法方便，性能高效，主要分以下4种类型。
 *
 * 原子更新基本类型
 * AtomicBoolean：原子更新布尔类型
 * AtomicInteger：原子更新整型
 * AtomicLong：原子更新长整型
 *
 * 原子更新数组类
 * AtomicIntegerArray：原子更新整型数组里的元素。
 * AtomicLongArray：原子更新长整型数组里的元素。
 * AtomicReferenceArray：原子更新引用类型数组里的元素。
 *
 * 原子更新引用类型
 * AtomicReference：原子更新引用类型。
 * AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
 * AtomicMarkableReference：原子更新带有标记位的引用类型。
 *
 * 原子更新字段类
 * AtomicIntegerFieldUpdater：原子更新整型的字段的更新器。
 * AtomicLongFieldUpdater：原子更新长整型字段的更新器。
 * AtomicStampedReference：原子更新带有版本号的引用类型。
 *
 * @author xiongying
 */
public class AtomicIntegerArrayTest {
    static int[] value = new int[]{1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
