package Base.concurrent.rein;

public class Extends {

    public static void main(String[] args) {

        Service3 s = new Service3();
        MyThread4 t1 = new MyThread4(s, '1');
        MyThread4 t2 = new MyThread4(s, '2');
        t1.start();
        t2.start();

    }
}

class MyThread4 extends Thread {
    Service3 s;
    char name;

    public MyThread4(Service3 s, char name) {
        this.s = s;
        this.name = name;
    }

    public void run() {
        s.service(name);
    }

}

class Service2 {
    public synchronized void service(char name) {
        for (int i = 3; i > 0; i--) {
            System.out.println(i);
        }
    }
}

class Service3 extends Service2 {
    public void service(char name) {
        for (int i = 5; i > 0; i--) {
            System.out.println("线程" + name + ":" + i);
        }
    }
}