package Base.concurrent.syncblock;

public class CallTheSame {
    public static void main(String[] args) {
        method1();
    }

    /*
     * 当多个线程访问同一个对象的synchronized（this）代码块时，一段时间内只有一个线程能执行
     */
    private static void method1() {
        Service2 s = new Service2();
        MyThread t1 = new MyThread(s, 'A');
        MyThread t2 = new MyThread(s, 'B');
        t1.start();
        t2.start();
    }
}

class Service2 {
    public void service(char name) {
        /*
         * 不在同步代码块中的代码可以异步执行，在同步代码块中的代码同步执行
         */
        for (int i = 6; i > 3; i--) {
            System.out.println(name + ":" + i);
        }
        synchronized (this) {
            for (int i = 3; i > 0; i--) {
                System.out.println(name + ":" + i);
            }
        }
    }
}

class MyThread extends Thread {
    Service2 s = new Service2();
    char name;

    public MyThread(Service2 s, char name) {
        this.s = s;
        this.name = name;
    }

    public void run() {
        s.service(name);
    }
}