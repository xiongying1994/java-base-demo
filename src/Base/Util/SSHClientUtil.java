package Base.Util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SSHClientUtil {

    // 字符编码默认是utf-8
    private static String DEFAULT_CHART = "UTF-8";


    /**
     * 登录验证
     *
     * @param conn
     * @param userName
     * @param userPwd
     * @return
     * @throws IOException
     */
    public static synchronized Boolean login(Connection conn, String userName, String userPwd) throws IOException {
        boolean flg = false;
        //加锁，防止多线程调用时线程间判断不一致，导致出现重复认证
        if (!conn.isAuthenticationComplete()) {
            //进行身份认证
            flg = conn.authenticateWithPassword(userName, userPwd);
        }
        return flg;
    }

    /**
     * 关闭连接
     *
     * @param session
     * @param conn
     */
    public static void closeConnection(Session session, Connection conn) {
        if (null != session) {
            session.close();
        }
        if (null != conn) {
            conn.close();
        }
    }

    /**
     * SSH命令执行
     *
     * @param ip
     * @param userName
     * @param userPwd
     * @param cmd
     * @return
     * @throws IOException
     */
    public static String handleSSH(String ip, String userName, String userPwd, String cmd) throws IOException {
        Connection conn = null;
        String result = null;
        Session session = null;
        try {
            // 初始化连接
            conn = new Connection(ip);
            conn.connect();
            if (login(conn, userName, userPwd)) {
                // 打开一个会话
                session = conn.openSession();
                // 执行命令
                session.execCommand(cmd);
                result = processStdout(session.getStdout(), DEFAULT_CHART);
                // 如果为得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isBlank(result)) {
                    result = processStdout(session.getStderr(), DEFAULT_CHART);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(session, conn);
        }
        return result;
    }

    /**
     * 取执行结果
     *
     * @param in
     * @param charset
     * @return
     */
    public static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    public static void main(String[] args) throws IOException {
        String name = "hadoop";
        String password = "luhuan123";
        String ip = "192.1.217.108";
        String cmd = "mkdir /home/hadoop/Desktop/kjhgf";
        String reslt = null;
        reslt = SSHClientUtil.handleSSH(ip, name, password, cmd);
        System.out.println(reslt);
    }

}







