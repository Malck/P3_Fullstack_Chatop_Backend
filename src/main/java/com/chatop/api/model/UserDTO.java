package com.chatop.api.model;

import java.time.LocalDateTime;
import java.util.Date;

public class UserDTO {

    private long id;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String created_at;

    private String updated_at;

    public UserDTO(long l, String name, String email, String localDateTime, String localDateTime2) {
        this.id = l;
        this.name = name;
        this.email = email;
        this.created_at = localDateTime;
        this.updated_at = localDateTime2;
        
    } 
}
