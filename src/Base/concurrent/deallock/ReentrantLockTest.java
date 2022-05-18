package Base.concurrent.deallock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * show the use of ReentrantLock
 *
 * @author LIUTAO
 * @version 2017/10/2
 * @see
 */
public class ReentrantLockTest {
    /**
     * show the use of ReentrantLock interrupt
     */
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public static class IntLock implements Runnable {
        int lock; // 用于控制加锁的顺序，便于构造死锁
        String name;

        public IntLock(int lock, String threadName) {
            this.name = threadName;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                if (lock == 1) {
                    lock1.lockInterruptibly();
                    try {
                        Thread.sleep(500);
                        System.out.println("name:" + name);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    try {
                        Thread.sleep(500);
                        System.out.println("name:" + name);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }

                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getId() + ",name:" + name + "线程退出!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock intLock1 = new IntLock(1, "thread1");
        IntLock intLock2 = new IntLock(2, "thread2");
        Thread thread1 = new Thread(intLock1);
        Thread thread2 = new Thread(intLock2);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();

    }

}