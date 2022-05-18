package Base.concurrent.fork;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * * Description: ForkJoin接口 * Designer: jack * Date:
 * 2017/8/3 * Version: 1.0.0
 */
public class ForkJoinService extends RecursiveTask<List<String>> {

    private int threshold; // 阈值
    private List<String> list; // 待拆分 List

    ForkJoinService(List<String> list, int threshold) {
        this.list = list;
        this.threshold = threshold;
    }

    @Override
    protected List<String> compute() {
        // 当end与start之间的差小于阈值时，开始进行实际筛选
        if (list.size() < threshold) {
            return list.parallelStream().filter(s -> s.contains("a")).collect(Collectors.toList());
        } else {
            // 如果当end与start之间的差大于阈值时,将大任务分解成两个小任务。
            int middle = list.size() / 2;
            List<String> leftList = list.subList(0, middle);
            List<String> rightList = list.subList(middle, list.size());
            ForkJoinService left = new ForkJoinService(leftList, threshold);
            ForkJoinService right = new ForkJoinService(rightList, threshold);
            // 并行执行两个“小任务”
            left.fork();
            right.fork();
            // 把两个“小任务”的结果合并起来
            List<String> join = left.join();
            join.addAll(right.join());
            return join;
        }
    }

}