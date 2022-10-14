public class AuthSingleton {
    private static final AuthSingleton INSTANCE = new AuthSingleton();

    private String login;
    private String password;
    private String language;

    private AuthSingleton(){}

    public static AuthSingleton getInstance(){
        return INSTANCE;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
