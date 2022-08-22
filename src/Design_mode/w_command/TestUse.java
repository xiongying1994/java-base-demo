package Design_mode.w_command;

/**
 * 命令模式
 * <p>
 * 一个对象有多种操作，但是我们不希望调用者（请求者）直接使用，
 * 我们就额外添加一个对象，然后让调用者通过这个对象来使用那些操作。
 *
 * @author a
 */
public class TestUse {
    public static void main(String args[]) throws Exception {
        //接收者
        MakeFile makeFile = new MakeFile();
        //命令
        CommandCreate create = new CommandCreate(makeFile);
        CommandDelete delete = new CommandDelete(makeFile);
        //请求者
        Client client = new Client();
        //执行命令
        client.setCommand(create).executeCommand("d://test1.txt");
        client.setCommand(create).executeCommand("d://test2.txt");
        client.setCommand(delete).executeCommand("d://test2.txt");
    }
}