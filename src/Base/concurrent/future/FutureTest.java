package Base.concurrent.future;

import java.util.concurrent.*;

/**
 * Future 方式下，多线程的使用，与线程结果收集
 */
public class FutureTest {
    public static void main(String[] args) {
        // 第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        // 第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        // Task task = new Task();
        // FutureTask<Integer> futureTask = new
        // FutureTask<Integer>(task);
        // Thread thread = new Thread(futureTask);
        // thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(1000);
        int sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return sum;
    }
}
