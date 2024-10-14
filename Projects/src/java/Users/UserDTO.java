package Users;

import Users.*;

public class UserDTO {

    private String userId;
    private String fullName;
    private String password;
    private String roleID;
    private String gmail;
    private String address;
    private Integer status;

    public UserDTO(String userId, String fullName, String password, String roleID, String gmail, String address, Integer status) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.gmail = gmail;
        this.address = address;
        this.status = status;
    }

    public UserDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
