package Design_mode.s_memento;

/**
 * 自述历史备忘录
 */
public class MementoSelf {
    public static void main(String[] args) {
        // 发起人，同时为负责人
        OriginatorCaretaker originatorCaretaker = new OriginatorCaretaker();
        // 改变状态
        originatorCaretaker.changeState("stateOne");
        // 保存状态
        IMemento memento = originatorCaretaker.createMemento();
        // 改变状态
        originatorCaretaker.changeState("stateTwo");
        // 恢复状态
        originatorCaretaker.recoverMemento(memento);
    }
}

interface IMemento {
}

/**
 * 发起人兼负责人
 */
class OriginatorCaretaker {
    public String state;

    public void changeState(String state) {
        this.state = state;
    }

    /**
     * 创造快照
     *
     * @return
     */
    public Memento createMemento() {
        return new Memento(this);
    }

    /**
     * 恢复状态
     *
     * @param memento
     */
    public void recoverMemento(IMemento memento) {
        Memento m = (Memento) memento;
        changeState(m.state);
    }

    /**
     * 内部类实现备忘录
     */
    private class Memento implements IMemento {
        private String state;

        private Memento(OriginatorCaretaker originatorCaretaker) {
            this.state = originatorCaretaker.state;
        }
    }
}
