package domain;

/**
 * <p>
 * Transfer Object对象,DAO通过向数据源传递此对象,完成对数据源的增删改查
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
public class User {
    private String username;
    private String password;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
