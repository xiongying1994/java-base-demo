package Design_mode.t_mediator;

/**
 * 中介者模式
 * 凸显了 单一职责原则，迪米特法则（最小知道原则）
 *
 * 有两个类，他们都是做持久化的，一个负责将数据写入文件，一个负责将数据写入数据库。
 * 他们谁先接收到数据是不确定的，但是要确保其中一个接收到数据后，另外一个也必须完成这些数据的持久化。
 * 如果我们直接将两个类关联在一起，互相调用是可以实现的，但不利于后期扩展或维护（比如再添加一个持久化组建，则原有的组建可能都需要修改）
 * 此时，若添加一个中介者，来协调他们，事儿就好办多了
 *
 * 中介者包含所有功能类，功能类之间不希望有关联，是松耦合的
 * 关联关系放在中介者中，这大大简化了扩展的复杂度，但是由于关系都在中介者类中，也提升了中介者类的复杂度
 *
 * 如果还有许多的持久化组件（具体同事），可以在中介者中使用一个List来存放他们的引用，set的时候就添加。
 * 在通知其他同事时，遍历这个List，除了参数本身这个同事，其他的依次通知，即可实现！
 * 我们这个例子就简单化处理了，没有用List。
 *
 * 其实这种概念，在数据库中比较多，就是管理表，或者说总表，不同的功能表之间没有关联，而在总表中，关联各个功能表
 * 那么在需要修改数据时，根据总表去各个功能表进行修改
 *
 */
public class TestUse {
    public static void main(String args[]) {
        Object data = "数据";
        PersistentDB persistentDB = new PersistentDB();
        PersistentFile persistentFile = new PersistentFile();
        Midiator midiator = new Midiator();
        midiator.setPersistentDB(persistentDB).setPersistentFile(persistentFile);
        persistentDB.getData(data, midiator);
        persistentFile.getData(data, midiator);
    }
}
