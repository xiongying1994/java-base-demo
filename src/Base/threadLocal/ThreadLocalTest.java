package Base.threadLocal;

import Base.objectModify.ObjectTransmit;

import java.io.PrintStream;

/**
 * 测试线程相关信息
 * 1、ThreadLocal 存储线程中的比较对象，保证线程安全，一般用static修饰，一个线程只有一个ThreadLocal对象
 * 2、普通对象会互相修改
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static Integer integer = new Integer(0);

    public static void main(String[] args) {
        ObjectTransmit testDemo = new ObjectTransmit();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testDemo.studentCode();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    threadLocal.set(i);
                    integer = 111;
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    System.out.println(Thread.currentThread().getName() + "       *********    " + integer);

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                threadLocal.remove();
            }
        }, "threadLocal1").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    threadLocal.set(100 + i);
                    integer = 222;
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    System.out.println(Thread.currentThread().getName() + "       *********    " + integer);

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                threadLocal.remove();
            }
        }, "threadLocal2").start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < 50; i++) {
                                System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                                System.out.println(Thread.currentThread().getName() + "       *********    " + integer);

                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } finally {
                            threadLocal.remove();
                        }
                    }
                }, "threadLocal3"
        ).start();

        Object object = new Object();
        Thread a = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            System.out.println(Thread.currentThread().getName() + "====" + "aaaaaaaaa--" + i);

                            synchronized (object) {
                                if (i == 5) {
                                    try {
                                        object.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }, "xy_thread_1"
        );

        a.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("aaaaaaaaa");
                    }
                    object.notify();
                }
            }
        }.start();


        PrintStream out = System.out;
        Runnable b = () -> out.println(" ");
    }
}
