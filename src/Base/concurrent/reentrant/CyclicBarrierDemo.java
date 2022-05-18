package Base.concurrent.reentrant;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * show how to use CyclicBarrier
 *
 * @author LIUTAO
 * @version 2017/10/10
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final int N = 5;
        Thread[] allSoldier = new Thread[5];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍！");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道！");
            allSoldier[i] = new Thread(new Soldier("士兵：" + i, cyclicBarrier));
            allSoldier[i].start();
        }
    }
}

/**
 * 士兵线程类
 */
class Soldier implements Runnable {
    private String soldier;
    private final CyclicBarrier cyclicBarrier;

    public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
        this.soldier = soldier;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 等待所有士兵到齐
            cyclicBarrier.await();
            doWork();
            cyclicBarrier.await();
            // 等待所有士兵完成工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException b) {
            b.printStackTrace();
        }
    }

    void doWork() {
        try {
            Thread.sleep(Math.abs(new Random().nextInt() % 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(soldier + ":完成任务");
    }
}

class BarrierRun implements Runnable {
    boolean flag;
    int N;

    public BarrierRun(boolean flag, int N) {
        this.flag = flag;
        this.N = N;
    }

    @Override
    public void run() {
        if (flag) {
            System.out.println("司令：[士兵" + N + "个，任务完成！]");
        } else {
            System.out.println("司令：[士兵" + N + "个，集合完成！]");
            flag = true;
        }
    }
}