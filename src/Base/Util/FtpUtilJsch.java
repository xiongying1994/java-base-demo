package Base.Util;

/**
 * @author xieguobao-pc
 * @Date 2019/3/28
 */

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

//@Configuration
@Slf4j
public class FtpUtilJsch {

    public static final String NO_FILE = "No such file";

    private ChannelSftp sftp = null;
    private Session sshSession = null;


    private static String hostName;
    private static String password;
    private static String host;
    private static int port;

//    @Value("${ftp.hostName}")
//    public void setHostName(String hostName) {
//        FtpUtilJsch.hostName = hostName;
//    }
//    @Value("${ftp.password}")
//    public void setPassword(String password) {
//        FtpUtilJsch.password = password;
//    }
//    @Value("${ftp.host}")
//    public void setHost(String host) {
//        FtpUtilJsch.host = host;
//    }
//    @Value("${ftp.port}")
//    public void setPort(int port) {
//        FtpUtilJsch.port = port;
//    }

    /**
     * 连接sftp服务器
     *
     * @return ChannelSftp sftp类型
     * @throws Exception
     */
    public ChannelSftp connect() throws Exception {
        log.info("FtpUtil-->connect--ftp连接开始>>>>>>host=" + host + ">>>port" + port + ">>>username=" + hostName);
        JSch jsch = new JSch();
        try {
            jsch.getSession(hostName, host, port);
            sshSession = jsch.getSession(hostName, host, port);
            log.info("ftp---Session created.");
            sshSession.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            log.info("ftp---Session connected.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            log.info("Opening Channel.");
            sftp = (ChannelSftp) channel;
            log.info("ftp---Connected to " + host);
        } catch (JSchException e) {
            throw new Exception("FtpUtil-->connect异常" + e.getMessage());
        }
        return sftp;
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                this.sftp = null;
                log.info("sftp is closed already");
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                this.sshSession = null;
                log.info("sshSession is closed already");
            }
        }
    }

    /**
     * 删除文件
     * @param filename
     * @throws Exception
     */
    public void rmFile(String filename) throws Exception {
        connect();
        if (sftp != null) {
            try {
                sftp.rm(filename);
            } catch (SftpException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String fileUrl = "/home/app/uploadfiles/2019/03/8cdb1453797d41c9b98c426525392253_基础运营表@20190117-谢国宝.xlsx";
            new FtpUtilJsch().rmFile(fileUrl);
            System.out.println("=====");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

