package Base.concurrent.rein;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static Integer i = 0;
    public static ReentrantLock reentrantLock = new ReentrantLock();

    static ReentrantLock reentrantLockOfFair = new ReentrantLock(true);

    public static class FairLock implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    reentrantLockOfFair.lock();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " gets the lock successfully");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLockOfFair.unlock();
                }
            }

        }
    }

    public static class ReenterLock implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 10000000; j++) {
                reentrantLock.lock();
                try {
                    i++;
                } finally {
                    reentrantLock.unlock();
                }

            }
        }
    }

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
        // ReenterLock reenterLock = new ReenterLock();
        // FairLock reenterLock = new FairLock();
        // Thread thread1 = new Thread(reenterLock);
        // Thread thread2 = new Thread(reenterLock);
        // thread1.start();
        // thread2.start();
        // thread1.join();
        // thread2.join();
        // System.out.println(i);

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