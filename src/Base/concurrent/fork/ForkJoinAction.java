package Base.concurrent.fork;

import java.util.concurrent.RecursiveAction;

/**
 *
 * @Author : Wukn
 * @Date : 2018/2/5
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

    @Override
    protected void compute() {
        // 当end-start的值小于MAX时，开始打印
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " ,i的值" + i +",次数：");
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
