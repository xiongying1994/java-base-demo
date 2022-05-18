package Base.concurrent.rein;

public class T1 {
    public static void main(String[] args) {

        MyThread3 thread = new MyThread3();
        thread.start();
    }
}

class MyThread3 extends Thread {
    Service s = new Service();

    public void run() {
        s.service1();
    }
}

class Service {
    public synchronized void service1() {
        System.out.println("服务1并没有被锁住");
        service2();
    }

    public synchronized void service2() {
        System.out.println("服务2并没有被锁住");
        service3();
    }

    public synchronized void service3() {
        System.out.println("服务3并没有被锁住");
    }
}