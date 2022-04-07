package com.hamid.userservice.dto;

public class RoleToUserDTO {

  private String username;
  private String roleName;

  public RoleToUserDTO() {
  }

  public RoleToUserDTO(String username, String roleName) {
    this.username = username;
    this.roleName = roleName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Override
  public String toString() {
    return "RoleToUserDTO{" +
        "username='" + username + '\'' +
        ", roleName='" + roleName + '\'' +
        '}';
  }
}
