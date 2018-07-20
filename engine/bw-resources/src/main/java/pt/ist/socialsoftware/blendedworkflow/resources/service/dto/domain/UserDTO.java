package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

public class UserDTO {
    private String id;
    private String username;
    private String password;

    public UserDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO() {

    }

    public String getId() {
        return id;
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
