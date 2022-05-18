package Base.concurrent.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * * Description: Fork/Join执行类 * Designer: jack * Date:
 * 2017/8/3 * Version: 1.0.0
 */
public class ForkJoinCalculatorTest {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 提交可分解的ForkJoinTask任务
        ForkJoinCalculator cal = new ForkJoinCalculator(1, 100);
        ForkJoinTask<Long> future1 = pool.submit(cal);
        System.out.println(future1.get());
        // 关闭线程池
        pool.shutdown();
    }
}
