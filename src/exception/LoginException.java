package exception;

/**
 * <p>
 * 登陆异常信息
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
public class LoginException extends Exception {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }
}
