package Base.concurrent.a_fork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author xiongying
 */
public class ForkJoinServiceTest {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        String[] strings = {"a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te", "sdr", "gdf", "df",
                "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui"};
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinService forkJoinService = new ForkJoinService(stringList, 20);
        // 提交可分解的ForkJoinTask任务
        ForkJoinTask<List<String>> future = pool.submit(forkJoinService);
        System.out.println(future.get());
        // ForkJoinCalculator cal = new
        // ForkJoinCalculator(1, 100);
        // ForkJoinTask<Long> future1 = pool.submit(cal);
        // System.out.println(future1.get());
        // 关闭线程池
        pool.shutdown();
    }
}
