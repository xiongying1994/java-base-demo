package Base.concurrent.interrupt;

// 处理中断异常，中断sleep
class TestRunnable2 implements Runnable {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Thread is running...");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class InterruptThreadTest2 {
    public static void main(String[] args) throws Exception {
        Runnable r = new TestRunnable2();
        Thread th1 = new Thread(r);
        th1.start();
        Thread.sleep(3000);
        th1.interrupt();
    }
}