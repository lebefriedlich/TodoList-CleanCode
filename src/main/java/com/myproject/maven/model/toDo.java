package com.myproject.maven.model;

public class toDo {
    private int id, user_id;
    private String toDo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public toDo(int id, int user_id, String toDo) {
        this.id = id;
        this.user_id = user_id;
        this.toDo = toDo;
    }
}
