package Base.concurrent.syncblock.diffmothed;

public class UseLock {
    public static void main(String[] args) {
        method1();

    }

    /*
     * 不同方法里面的synchronized代码块同步执行 改变锁对象，发现两个方法并不是同步执行的
     * 
     */
    private static void method1() {
        Service2 s = new Service2();
        MyThread t1 = new MyThread(s, 'A');
        MyThread2 t2 = new MyThread2(s, 'B');
        t1.start();
        t2.start();
    }

}

class Service2 {

    String s = new String();

    public void service(char name) {
        synchronized (s) {
            for (int i = 3; i > 0; i--) {
                System.out.println(name + ":" + i);
            }
        }
    }

    public void service2(char name) {
        synchronized (this) {
            for (int i = 6; i > 3; i--) {
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

class MyThread2 extends Thread {
    Service2 s = new Service2();
    char name;

    public MyThread2(Service2 s, char name) {
        this.s = s;
        this.name = name;
    }

    public void run() {
        s.service2(name);
    }
}