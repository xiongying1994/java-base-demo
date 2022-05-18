package Base.Exception;

/**
 * @Author: xiongying
 * @Date: 2022/5/17 下午5:33
 */
public class ServiceContraller {
    public static void main(String[] args) {
        ExceptionService exceptionService = new ExceptionService();

        try {
            exceptionService.Demo("b");
            Thread.sleep(5000);
            exceptionService.Demo("a");
        } catch (ServiceException e) {
            System.out.println("哈哈哈");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("啦啦啦");
            e.printStackTrace();
        }
    }
}
