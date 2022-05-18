package Base.concurrent.volatila;

public class TestVolatile {
    public static void main(String[] args) {

        try {
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(1000);
            thread.setRunning(false);
            System.out.println("已经赋值为false");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RunThread extends Thread {
    /*
     * isRunning变量存放在公共堆栈和线程的私有堆栈中，我们对他赋值为false时，只对公共堆栈进行更新，
     * 而但我们设置为-server后， 读取的是线程私有栈的内容，所以也就造成了死循环。
     * 我们可以在isRunning变量前加上volatite关键字，这个时候访问的是公共堆栈，
     * 就不会造成死循环了
     */
    private volatile boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入run了");
        while (isRunning == true) {
        }
        System.out.println("线程被停止了！");
    }

}