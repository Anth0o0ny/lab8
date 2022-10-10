public class DataSingleton {
    private static final DataSingleton INSTANCE = new DataSingleton();

    private String login;
    private String password;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
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
