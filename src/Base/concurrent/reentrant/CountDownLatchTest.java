package Base.concurrent.reentrant;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    static final CountDownLatch countDownLatch = new CountDownLatch(5);
    public static CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(countDownLatchDemo);
        }
        countDownLatch.await();
        System.out.println("All threads have been executed completely");
        executorService.shutdown();
    }

    static class CountDownLatchDemo implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(10) * 100);
                System.out.println(Thread.currentThread().getId() + " check complete");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
