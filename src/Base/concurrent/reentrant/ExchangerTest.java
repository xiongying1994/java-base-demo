package Base.concurrent.reentrant;

import java.util.concurrent.Exchanger;

class ExchangerThread extends Thread {
    private Exchanger<String> exchanger;
    private String string;
    private String threadName;

    public ExchangerThread(Exchanger<String> exchanger, String string, String threadName) {
        this.exchanger = exchanger;
        this.string = string;
        this.threadName = threadName;
    }

    public void run() {
        try {
            System.out.println(threadName + ": " + exchanger.exchange(string));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerThread test1 = new ExchangerThread(exchanger, "string1", "thread-1");
        ExchangerThread test2 = new ExchangerThread(exchanger, "string2", "thread-2");

        test1.start();
        test2.start();
    }
}
