package Base.concurrent.rein;

public class SynchronizedTest implements Runnable {
    public synchronized void method1() {
        System.out.println("method1获得锁，正常运行!");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2获得锁，也正常运行!");
    }

    @Override
    public void run() {
        method1();
    }

    public static void main(String[] args) {
        SynchronizedTest st = new SynchronizedTest();
        new Thread(st).start();
        new Thread(st).start();
    }
}
