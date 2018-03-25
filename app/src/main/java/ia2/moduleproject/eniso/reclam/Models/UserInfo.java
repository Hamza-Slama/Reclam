package ia2.moduleproject.eniso.reclam.Models;

/**
 * Created by hamza on 02/03/18.
 */

public class UserInfo {
    private String login;
    private String password;

    public UserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
