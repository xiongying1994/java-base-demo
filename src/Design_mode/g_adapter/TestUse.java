package Design_mode.g_adapter;

/**
 * 你有一个播放器，只能播放MP3格式的音乐，但是现在需要它能播放flac格式的
 * 这个例子很差，因为我们需要它只能播放flac，附带的那个List的例子很好，表达了适配器模式的用处
 * <p>
 * 类适配器：继承被适配类，再继承 适配接口
 * <p>
 * 对象适配器：继承适配接口，在适配器类的 构造方法中，实例化 被适配类，那么在适配器类初始化的时候，就会
 * 创建一个 被适配类 的对象，再使用其中的方法
 */
public class TestUse {
    public static void main(String args[]) {
        // 你有的mp3
        Adaptee adaptee = new Adaptee();
        adaptee.playMp3("mp3");

        // 类适配器
        ClassAdapter classAdapter = new ClassAdapter();
        classAdapter.playFlac("flac");
        classAdapter.playMp3("mp3");

        // 对象适配器   你用适配器实现类还是接口作为结果的类，看自己需要
        // ClassAdapter作为返回，可以两边方法都用，接口作为返回只能用目标方法，被适配的方法就没了
        Target target = new ObjectAdapter();
        target.playFlac("flac");
    }
}