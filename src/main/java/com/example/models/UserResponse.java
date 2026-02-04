package com.example.models;

/**
 * UserResponse is a simple POJO that represents the API response for a user.
 */
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;

    /** @return user id */
    public Integer getId() {
        return id;
    }

    /** @param id set user id */
    public void setId(Integer id) {
        this.id = id;
    }

    /** @return user name */
    public String getName() {
        return name;
    }

    /** @param name set user name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return user email */
    public String getEmail() {
        return email;
    }

    /** @param email set user email */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return user gender */
    public String getGender() {
        return gender;
    }

    /** @param gender set user gender */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /** @return user status */
    public String getStatus() {
        return status;
    }

    /** @param status set account status */
    public void setStatus(String status) {
        this.status = status;
    }
}