package Base.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/***
 * Created by hujian06 on 2017/10/31.** the demo of
 * FutureTask
 */
public class FutureTaskDemo {
    public static void main(String... args) {
        ACallAble callAble = new ACallAble();
        FutureTask<String> futureTask = new FutureTask<>(callAble);
        Thread thread = new Thread(futureTask);
        thread.start();
        do {
        } while (!futureTask.isDone());
        try {
            String result = futureTask.get();
            System.out.println("Result:" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class ACallAble implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Thread-Name:" + Thread.currentThread().getName();
    }
}