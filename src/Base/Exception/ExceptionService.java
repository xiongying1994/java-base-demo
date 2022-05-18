package Base.Exception;

/**
 * @Author: xiongying
 * @Date: 2022/5/17 下午5:33
 */
public class ExceptionService {
    public String Demo(String code) throws Exception {
        if (code.equals("a")) {
            throw new Exception("报错了，Exception");
        }
        if (code.equals("b")) {
            throw new ServiceException("新错误，ServiceException");
        }

        return "";
    }
}
