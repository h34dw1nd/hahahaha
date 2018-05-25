package com.tctf.entity;


public class User{
    private Integer id;
    private String username;
    private String password;
    private String headurl;
    private Boolean isadmin;

    public User(Integer id, String username, String password, String headurl, Boolean isadmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.headurl = headurl;
        this.isadmin = isadmin;
    }

    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public User(){ }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "uid=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", headurl='" + headurl + '\'' +
                ", isadmin=" + isadmin +
                '}';
    }

}
