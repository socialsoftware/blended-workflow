package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

public class UserDto {
    private String id;
    private String username;
    private String password;

    public UserDto(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDto() {

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

    public String print() {
        return "UserDTO: ID = " + this.id + " Username =  " + this.username;
    }
}
