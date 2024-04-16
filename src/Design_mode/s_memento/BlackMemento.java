package Design_mode.s_memento;

/**
 * 备忘录模式
 */
public class BlackMemento {
    public static void main(String[] args) {
        //发起人
        BlankOriginator originator = new BlankOriginator();
        //负责人
        BlackCaretaker caretaker = new BlackCaretaker();
        //设置状态
        originator.setState("stateOne");
        //保存信息
        caretaker.saveMemento(originator.createMemento());
        //修改状态
        originator.setState("stateTwo");
        //恢复状态
        originator.recoverMemento(caretaker.recoverMemento());
    }
}

interface MementoIF {
}

/**
 * 发起人
 */
class BlankOriginator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MementoIF createMemento() {
        return new Memento(state);
    }

    public void recoverMemento(MementoIF memento) {
        this.setState(((Memento) memento).getState());
    }

    /**
     * 以内部类实现备忘录角色
     */
    private class Memento implements MementoIF {
        private String state;

        private Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }
    }
}

/**
 * 负责人
 */
class BlackCaretaker {
    private MementoIF memento;

    public MementoIF recoverMemento() {
        return memento;
    }

    public void saveMemento(MementoIF memento) {
        this.memento = memento;
    }
}
