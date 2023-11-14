package gr.aueb.cf.schoolapppro.dto;

public class UserRegisterDTO {

    private String username;
    private String password;
    private String confPass;

    UserRegisterDTO(){}

    public UserRegisterDTO(String username, String password, String confPass) {
        this.username = username;
        this.password = password;
        this.confPass = confPass;
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

    public String getConfPass() {
        return confPass;
    }

    public void setConfPass(String confPass) {
        this.confPass = confPass;
    }
}
