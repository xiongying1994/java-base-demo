package Base.concurrent.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 无法中断正在运行的线程代码
 */
class TestRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            // while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
                // 程序循环1秒钟，不同于sleep(1000)会阻塞进程。
            }
        }
    }
}

/**
 * @author xiongying
 */
public class InterruptThreadTest1 {
    public static void main(String[] args) throws Exception {
        Runnable r = new TestRunnable();
        Thread th1 = new Thread(r);
        th1.start();
        TimeUnit.SECONDS.sleep(4);
        th1.interrupt();
    }
}
