package Base.concurrent.volatila.count;

import java.util.concurrent.atomic.AtomicInteger;

public class TestCount {
    public static void main(String[] args) {

        MyThread[] mythreadArray = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new MyThread();
        }
        MyThread1[] mythreadArray1 = new MyThread1[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray1[i] = new MyThread1();
        }

        for (int i = 0; i < 100; i++) {
            mythreadArray[i].start();
            // mythreadArray1[i].start();
        }
    }
}

class MyThread extends Thread {
    public static int count;

    public static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
            // try {
            // Thread.sleep(2);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        addCount();
    }

}

class MyThread1 extends Thread {
    static AtomicInteger count = new AtomicInteger(0);

    public static void addCount() {
        for (int i = 0; i < 100; i++) {
            count.incrementAndGet();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("auto count=" + count);
    }

    @Override
    public void run() {
        addCount();
    }

}