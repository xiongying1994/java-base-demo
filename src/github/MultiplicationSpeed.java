package github;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 判断 n * i * i     与   n * (i * i)
 * 的速度差别
 * 结论：没有太大差别，i * i 是 i 的平方，由于2的平方可以用 位移 运算，因此 i=2 时，后一种方法会稍微快一点
 * @author xiongying
 */
public class MultiplicationSpeed {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/xiongying/Desktop" + File.separator + "test.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        //2：准备输出流
        Writer out = new FileWriter(file,true);
        out.write("数字：3 \n");
        for(int j = 0; j < 10; j++){
            long n = 0;
            long m = 0;
            boolean real = false;
            String fast = "慢了啊";
            long startTime = System.nanoTime();
            for (int i = 0; i < 1000000000; i++) {
                n += 3 * i * i;
            }
            double oneTime = (double) (System.nanoTime() - startTime) / 1000000000;
            System.out.print("--1--第"+ j + "次： " + oneTime + " s，  |    ");
            out.write("--1--第"+ j + "次： " + oneTime + " s，  |    ");

            startTime = System.nanoTime();
            for (int i = 0; i < 1000000000; i++) {
                m += 3 * (i * i);
            }
            double secondTime = (double) (System.nanoTime() - startTime) / 1000000000;
            if(m == n)
                real = true;
            if(oneTime > secondTime)
                fast = "快一点";
            System.out.println("--2--第"+ j + "次： " + secondTime + " s，"+ fast + " ;  n="+ real);
            out.write("--2--第"+ j + "次： " + secondTime + " s，"+ fast + " ;  n="+ real + "\n");
        }
        out.close();
    }
}
