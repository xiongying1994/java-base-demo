package Base.concurrent.reentrant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * show the use of ReentrantLock
 *
 * @author LIUTAO
 * @version 2017/10/2
 * @see
 */
public class SemaphoreTest {
    /**
     * show how to use Semaphore
     */
    static final Semaphore semp = new Semaphore(5);

    public static class SempDemo implements Runnable {

        @Override
        public void run() {
            try {
                semp.acquire();
                // simulate time consuming operation
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getId() + ":done!  " + System.currentTimeMillis());
                semp.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SempDemo sempDemo = new SempDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(sempDemo);
            System.err.println("-----------");
        }
        executorService.shutdown();
    }

}
