package Base.concurrent.future;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: xiongying
 * @Date: 2024/4/15 14:50
 */
public class FutureXYTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Integer threadNum = 10;
        List<Map<String, Object>> requestMap = new ArrayList<>();
        List<Map<String, Object>> resultList = new ArrayList<>();

        // 增加入参
//        requestMap.add();

        // 设置 request 对象可以在子线程中共享
//        RequestContextHolder.setRequestAttributes(RequestContextHolder.getRequestAttributes(), true);
        // 创建线程池
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(threadNum,
                new BasicThreadFactory.Builder().namingPattern("audit-schedule-pool-%d").daemon(true).build());
        // 结果集阻塞队列
        BlockingQueue<Future<Map<String, Object>>> queue = new LinkedBlockingDeque<>(threadNum);
        // 优先完成优先结束
        final CompletionService<Map<String, Object>> completionService = new ExecutorCompletionService<>(
                service, queue);

        // 循环入参条数，将所有入参数据均计入线程池，并开启线程处理
        for (int i = 0; i < requestMap.size(); i++) {
            Map<String, Object> params = requestMap.get(i);
            completionService.submit(
                    () -> {
                        // 开启多线程，启动稽核诊断流程
//                        return startAudit(Long.parseLong(params.get("sceneId").toString()), params.get("tableName").toString(), params.get("primaryValue").toString());
                        System.out.println("子线程在进行计算");
                        Thread.sleep(1000);
                        int sum = 0;
                        for (int m = 0; m < 100; m++) {
                            sum += m;
                        }
                        Map<String, Object> result = new HashMap<>();
                        result.put("sum", sum);
                        return result;
                    }
            );
//            log.info("添加线程池完成！开始处理！");
        }

        for (int i = 0; i < requestMap.size(); i++) {
            // 取出结果集，先完成的会先取出
            resultList.add(completionService.take().get());
        }
        // 关闭线程池
        service.shutdown();

        Map<String, Object> result = new HashMap<>(2);
        result.put("code", 200);
        result.put("resultList", resultList);
    }
}
