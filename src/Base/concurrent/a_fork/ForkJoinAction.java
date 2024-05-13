package Base.concurrent.a_fork;

import java.util.concurrent.RecursiveAction;

/**
 * Fork/Join的执行过程主要有两步：
 *     第一步分割任务。通过ForkJoinTask对象，调用fork()方法把大任务分割成子任务。
 *     第二步执行任务并合并结果。分割的子任务分别放在双端队列里，然后几个启动线程分别从双端队列里获取任务执行。
 *          子任务执行完的结果都统一放在一个队列里，启动一个线程从队列里拿数据，然后合并这些数据，
 *          而子任务执行的结果是通过ForkJoinTask的join()方法获取的。
 *
 * ForkJoinTask有点像Thread的Runable,都是用来定义要执行的任务的。
 *
 * ForkJoinTask有两个子类：
 *      RecursiveAction：用于没有返回结果的任务。
 *      RecursiveTask ：用于有返回结果的任务。
 *
 * 本案例为无返回结果时的演示
 * @author xiongying
 */
public class ForkJoinAction extends RecursiveAction {
    /**
     * 每个"小任务"最多只打印20个数
     */
    private static final int MAX = 13;

    private int start;
    private int end;

    public ForkJoinAction(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 子任务拆分方法，必须重写这个方法来进行线程逻辑编写 与 任务拆分
     */
    @Override
    protected void compute() {
        // 当end-start的值小于MAX时，开始打印
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " ,i的值" + i + ",次数：");
            }
        } else {
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            ForkJoinAction left = new ForkJoinAction(start, middle);
            ForkJoinAction right = new ForkJoinAction(middle, end);
            left.fork();
            right.fork();
        }
    }
}
