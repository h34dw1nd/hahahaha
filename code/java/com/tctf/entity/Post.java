package com.tctf.entity;

public class Post {

    private Integer pid;
    private Integer uid;
    private String title;
    private String content;
    private Integer status;


    public Post(Integer pid, Integer uid, String title, String content, Integer status) {
        this.pid = pid;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public Post(){}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
