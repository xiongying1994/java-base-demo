package Base.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Thread方法演示
 * @Author: xiongying
 * @Date: 2023/10/16 15:27
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.test();
    }
    public class ThreadA extends Thread {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int priority = Thread.currentThread().getPriority();
            System.out.println("threadName:" + threadName + "，priority：" +  priority);
        }
    }

    public class ThreadB extends Thread {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int priority = Thread.currentThread().getPriority();
            System.out.println("threadName:" + threadName + "，priority：" +  priority);
        }
    }

    /**
     * setPriority()方法的作用是设置线程的优先级，取值范围：1～ 10，与此对应的还有getPriority()方法，用于获取线程的优先级。优先级越高，拥有优先获取 CPU 执行的优势。
     * 换句话说，当有两个线程在等待 CPU 执行时，优先级高的线程越容易被 CPU 选择执行。
     *
     * 线程默认优先级为 5，如果不手动指定，那么线程优先级具有继承性，比如线程 A 启动线程 B，那么线程 B 的优先级和线程 A 的优先级相同。
     *
     * 在实测过程中，可能有的同学感觉效果并不明显，如果你的电脑 CPU 是多核的，线程数量较少的情况，可能会被多个 CPU 并行执行，具体执行环境取决于 CPU 。
     * 需要特别注意的是：设置优先级只是很大程度上让某个线程尽可能获得比较多的执行机会，操作系统不能保证设置了优先级高的线程就一定会先运行或得到更多的 CPU 时间，具体执行哪一个线程，最终还是由 CPU 来决定。
     * 另外有些 linux 操作系统是不区分优先级的，它把所有优先级都视为 5。
     * setPriority()方法在实际的开发中，使用的并不多见。
     *
     * @throws InterruptedException
     */
    public void testsetPriority() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            ThreadA threadA = new ThreadA();
            ThreadB threadB = new ThreadB();

            threadA.setPriority(10);
            threadA.start();

            threadB.setPriority(1);
            threadB.start();
        }

        Thread.sleep(3000);
        System.out.println("主线程方法执行完毕！");
    }

    /**
     * 守护线程
     * 在 Java 中线程分为两种，一种是用户线程，一种是守护线程。
     * 守护线程是一种特殊的线程，它的作用是为其他线程的运行提供便利的服务，比如垃圾回收线程，就是最典型的守护线程。
     * 当 JVM 检测到应用程序中的所有线程都只有守护线程时，它将退出应用程序，因为没有存在的必要，服务的对象都没了，当然就需要销毁了。
     * 开发者可以通过使用setDaemon()方法，传递true作为参数，使线程成为一个守护线程，同时可以使用isDaemon()方法来检查线程是否是守护线程。
     */
    public void testsetDaemon() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                while (true){
                    String threadName = Thread.currentThread().getName();
                    boolean isDaemon = Thread.currentThread().isDaemon();
                    System.out.println("threadName:" + threadName + ",isDaemon:" + isDaemon);
                    Thread.sleep(500);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        threadA.setDaemon(true);
        threadA.start();

        Thread.sleep(3000);
        System.out.println("主线程方法执行完毕！");
    }

    /**
     * join()方法的作用是让调用此方法的主线程被阻塞，仅当该方法执行完成以后，才能继续运行。
     *
     * 从运行结果可以得出一个结论，主线程main调用threadA.join()方法时，会进入阻塞状态，直到线程实例threadA的run()方法执行完毕，主线程main从阻塞状态变成可运行状态。
     * 此例中主线程main会无限期阻塞直到threadA.run()方法执行完毕。
     * 比如某个业务场景下，主线程main的执行时间是 1s，子线程的执行时间是 10s，同时主线程依赖子线程执行完的结果，此时让主线程执行join()方法进行适度阻塞，可以实现此目标。
     *
     * @throws InterruptedException
     */
    public void testjoin() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                System.out.println(time + " 当前线程：" + Thread.currentThread().getName() + "，正在运行");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();

        // 让执行这个方法的线程阻塞（指的是主线程，不是threadA线程）
        threadA.join();

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(time + " 主线程方法执行完毕！");
    }

    /**
     * interrupt()方法的作用是当线程受到阻塞时，调用此方法会抛出一个中断信号，让线程退出阻塞状态，如果当前线程没有阻塞，是无法中断线程的。
     * 与此对应的还有isInterrupted()方法，用于检查线程是否已经中断，但不清除状态标识。
     *
     * 很明显，当线程处于阻塞状态时，调用interrupt()方法，可以让线程退出阻塞，起到终止线程的效果。终止线程会抛出 sleep interrupted
     *
     * 需要注意的是，若子线程已运行完毕（例如主线程sleep较长的时间），就是线程正常关闭，interrupt()也没有作用
     * @throws InterruptedException
     */
    public void testinterrupt() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
                    System.out.println(time + " 当前线程：" + Thread.currentThread().getName() + "，count:" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();

        Thread.sleep(2000);
        // 检查线程是否中断，没有尝试终止线程
        if(!threadA.isInterrupted()){
            System.out.println("尝试中断子线程！");
            threadA.interrupt();
        }

        System.out.println("主线程方法执行完毕！");
    }

    /**
     * isAlive()方法的作用是检查线程是否处于活动状态，只要线程启动且没有终止，方法返回的就是true。
     *
     * 从运行结果上可以看出，线程启动前isAlive=false，线程运行中isAlive=true，线程运行完成isAlive=false。
     * @throws InterruptedException
     */
    public void testisAlive() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "，isAlive：" + Thread.currentThread().isAlive());
        });
        System.out.println("begin == " + threadA.isAlive());

        threadA.start();

        Thread.sleep(1000);
        System.out.println("end == " + threadA.isAlive());
    }

    /**
     * currentThread()方法的作用是返回当前正在执行线程对象的引用。
     *
     * 线程类的构造方法、静态块是被主线程main调用的，而线程类的run()方法才是用户线程自己调用的。
     *
     * currentThread() 方法返回的是正在执行线程对象的引用，与this.getName()不同，this.getName()返回的是线程类的引用
     */
    public void testcurrentThread() {
        ThreadC threadC = new ThreadC();
        System.out.println("===============");
        threadC.start();

        System.out.println("主线程方法执行完毕！");
    }

    public static class ThreadC extends Thread {

        static {
            System.out.println("静态块打印的线程名称：" + Thread.currentThread().getName());
        }

        public ThreadC() {
            System.out.println("构造方法打印 Begin...");
            System.out.println("Thread.currentThread打印的线程名称：" + Thread.currentThread().getName());
            System.out.println("this.getName打印的线程名称：" + this.getName());
            System.out.println("构造方法打印 end...");
        }

        @Override
        public void run() {
            System.out.println("run()方法打印 Begin...");
            System.out.println("Thread.currentThread打印的线程名称：" + Thread.currentThread().getName());
            System.out.println("this.getName打印的线程名称：" + this.getName());
            System.out.println("run()方法打印 end...");
        }
    }

    /**
     * yield()方法的作用是暂停当前执行的线程对象，并执行其他线程。
     * 这个暂停会放弃 CPU 资源，并且放弃 CPU 的时间不确定，有可能刚放弃，就获得 CPU 资源了，也有可能放弃好一会儿，才会被 CPU 执行。
     *
     * 从运行结果上可以看出，调用yield()方法可以让线程放弃 CPU 资源，由于现在多核的出现，因此会没那么明显，循环次数越多，越明显。
     */
    public void testyield() {
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()  + ":" + i);
                if ("t1".equals(Thread.currentThread().getName())) {
                    System.out.println(Thread.currentThread().getName()  + ":" + i +"......yield.............");
                    Thread.yield();
                }
            }
        });

        Thread threadA1 = new Thread(threadA);
        Thread threadA2 = new Thread(threadA);
        threadA1.setName("t1");
        threadA2.setName("t2");

        threadA1.start();
        threadA2.start();

        System.out.println("主线程方法执行完毕！");
    }

    /**
     * sleep()方法的作用是在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）
     * 此操作受到系统计时器和调度程序精度和准确性的影响。这个正在执行的线程指的是Thread.currentThread()返回的线程。
     *
     * 根据 JDK API 的说法，该线程不丢失任何监视器的所属权，换句话说就是不会释放锁，如果sleep()代码上下文被加锁了，锁依然在，只是 CPU 资源会让出给其他线程。
     * @throws InterruptedException
     */
    public void testsleep() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                String begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
                System.out.println(begin + " 当前线程：" + Thread.currentThread().getName());

                Thread.sleep(3000);

                String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
                System.out.println(end + " 当前线程：" + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
    }

    /**
     * holdsLock()方法表示当且仅当当前线程在指定的对象上保持监视器锁时，才返回 true，简单的说就是检测一个线程是否拥有锁。
     * @throws InterruptedException
     */
    private String lock = "lock";

    public void testholdsLock() {
        Thread threadA = new Thread(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "，Holds Lock = " + Thread.holdsLock(lock));

            synchronized (lock){
                System.out.println("当前线程：" + Thread.currentThread().getName() + "，Holds Lock = " + Thread.holdsLock(lock));
            }
        });
        threadA.start();
    }

    /**
     * dumpStack()方法的作用是将当前线程的堆栈跟踪打印至标准错误流。此方法仅用于调试。
     * @throws InterruptedException
     */
    public void test() {
        Thread threadA = new Thread(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            Thread.dumpStack();
        });
        threadA.start();
    }
}
