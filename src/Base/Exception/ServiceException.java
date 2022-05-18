package Base.Exception;

/**
 * @Author: xiongying
 * @Date: 2022/5/17 下午5:31
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1107631170534840303L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
