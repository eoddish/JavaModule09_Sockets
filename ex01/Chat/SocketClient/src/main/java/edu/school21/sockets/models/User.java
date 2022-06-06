package edu.school21.sockets.models;

public class User {
    private Long id;
    private String email;

    public User(Long id, String email) {
        this.id = id;
        this.email = email;
    }
    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
