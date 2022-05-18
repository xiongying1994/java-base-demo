package Base.concurrent.threadd;

public class ThreadLocalExample {

    public static void main(String[] args) throws Exception {
        ThreadBean thread = new ThreadBean();
        Thread thread01 = new Thread(thread, "线程一");
        Thread thread02 = new Thread(thread, "线程二");
        thread01.start();
        thread02.start();

    }

}

class NumberBean {
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class ThreadBean implements Runnable {
    private static ThreadLocal<NumberBean> local = new ThreadLocal<NumberBean>() {
        // 覆盖初始化方法,泛型指定什么类型就返回什么类型，方法名不变自动调用。
        public NumberBean initialValue() {
            System.out.println("初始化了");
            return new NumberBean();
        }
    };

    @Override
    public void run() {

        while (local.get().getId() < 100) {
            local.get().setId(local.get().getId() + 1);
            System.out.println(Thread.currentThread().getName() + "数字： " + local.get().getId());
        }
    }

}
