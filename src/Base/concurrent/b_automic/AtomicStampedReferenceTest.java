package Base.concurrent.b_automic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference：原子更新带有版本号的引用类型。
 *
 * @author xiongying
 */
public class AtomicStampedReferenceTest {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(0, 0);

    public static void main(String[] args) throws InterruptedException {
        final int stamp = atomicStampedReference.getStamp();
        final Integer reference = atomicStampedReference.getReference();
        System.out.println(reference + "============" + stamp);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(reference + "-" + stamp + "-" + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 10));
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer reference = atomicStampedReference.getReference();
                System.out.println(reference + "-" + stamp + "-" + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }
}
