package Design_mode.w_command;

import java.io.File;
import java.io.IOException;

/**
 * 接收者
 */
public class MakeFile {
    /**
     * 新建文件
     *
     * @param name
     * @throws IOException
     */
    public void createFile(String name) throws IOException {
        File file = new File(name);
        file.createNewFile();
    }

    /**
     * 删除文件
     *
     * @param name
     * @return
     */
    public boolean deleteFile(String name) {
        File file = new File(name);
        if (file.exists() && file.isFile()) {
            file.delete();
            return true;
        }
        return false;
    }
}