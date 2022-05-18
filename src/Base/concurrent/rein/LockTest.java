package Base.concurrent.rein;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable {
    Lock lock = new ReentrantLock();

    public void method1() {
        lock.lock();
        System.out.println("method1获得锁，正常运行!");
        System.out.println(Thread.currentThread());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
        lock.unlock();
    }

    public void method2() {
        lock.lock();
        System.out.println("method2获得锁，也正常运行!");
        System.out.println(Thread.currentThread());
        lock.unlock();
    }

    @Override
    public void run() {
        method1();
    }

    public static void main(String[] args) {
        LockTest lt = new LockTest();
        new Thread(lt).start();
        new Thread(lt).start();
    }
}