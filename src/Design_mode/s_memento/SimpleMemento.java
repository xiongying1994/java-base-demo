package Design_mode.s_memento;

/**
 * 简单的备忘录模式
 * <p>
 * 强调 单一职责原则
 * 备忘录(Memento)角色：将发起人（Originator）对象的内战状态存储起来。备忘录可以根据发起人对象的判断来决定存储多少发起人（Originator）对象的内部状态。备忘录可以保护其内容不被发起人（Originator）对象之外的任何对象所读取。
 * 发起人（Originator）角色：创建一个含有当前的内部状态的备忘录对象。使用备忘录对象存储其内部状态。
 * 负责人（Caretaker）角色：负责保存备忘录对象。不检查备忘录对象的内容。
 *
 * 发起人创建备忘录，创建状态，将状态存储到备忘录
 * 负责人保存备忘录
 *
 * 缺点是会耗费大量的资源用于保存状态，而且每次保存都会消耗一定的内存
 *
 */
public class SimpleMemento {
    public static void main(String[] args) throws Exception {
        //发起人，要被保存的对象,也是他创建要保存的信息的
        Originator originator = new Originator();
        //辅助保存的对象，负责人
        Caretaker caretaker = new Caretaker();
        //设置状态
        originator.setState("stateOne");
        //保存状态
        caretaker.saveMemento(originator.createMemento());
        //修改状态
        originator.setState("stateTwo");
        //恢复状态
        originator.recoverMemento(caretaker.recoverMemento());
    }
}

/**
 * 发起人
 */
class Originator {
    private String state;

    public Memento createMemento() {
        return new Memento(state);
    }

    public void recoverMemento(Memento memento) {
        this.state = memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 备忘录
 */
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 负责人
 */
class Caretaker {
    // 状态
    private Memento memento;

    public Memento recoverMemento() throws Exception {
        if (memento == null) {
            throw new Exception("没有保存的状态");
        }
        // 恢复状态
        return this.memento;
    }

    public void saveMemento(Memento memento) {
        // 保存状态
        this.memento = memento;
    }
}
