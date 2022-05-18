package Design_mode.h_bridge;

/**
 * 桥接模式
 * 经典的依赖倒换原则，能使用抽象的地方，均使用抽象
 * 抽象类 -- 抽象接口 之前就像桥接了一样
 * 在抽象类中存在抽象接口的对象，并在构造方法中使用了抽象接口类型的实际参数参与构造，传入抽象接口实际对象！
 * 那么就可以在抽象类的实现类中使用super来引用这个抽象接口实际对象了
 * 两边都是抽象的，那么在具体运用过程中就可以有各种各样的组合方式了
 *
 * @author a
 */
public class SimpleBridge {
    public static void main(String args[]) {
        AbstractComputer abstractComputer = new LenevoComputer(new Amd());
        abstractComputer.discribe();

        abstractComputer = new HaseeComputer(new Intel());
        abstractComputer.discribe();
    }
}

/**
 * 实现者
 */
interface Cpu {
    String discribe();
}

/**
 * 具体实现者*2
 */
class Amd implements Cpu {
    @Override
    public String discribe() {
        return "just so so...";
    }
}

class Intel implements Cpu {
    @Override
    public String discribe() {
        return "great !";
    }
}

/**
 * 抽象
 */
abstract class AbstractComputer {
    Cpu cpu;

    public AbstractComputer(Cpu cpu) {
        this.cpu = cpu;
    }

    public abstract void discribe();

}

/**
 * 细化抽象*2
 */
class LenevoComputer extends AbstractComputer {
    public LenevoComputer(Cpu cpu) {
        super(cpu);
    }

    @Override
    public void discribe() {
        System.out.println("联想笔记本cpu:" + super.cpu.discribe());
    }
}

class HaseeComputer extends AbstractComputer {
    public HaseeComputer(Cpu cpu) {
        super(cpu);
    }

    @Override
    public void discribe() {
        System.out.println("神舟笔记本cpu:" + super.cpu.discribe());
    }
}