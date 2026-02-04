package com.example.models;

/**
 * UserRequest is a simple POJO used to serialize request payloads for user creation/updates.
 */
public class UserRequest {
    private String name;
    private String gender;
    private String email;
    private String status;

    /** Default constructor for deserialization. */
    public UserRequest() {
    }

    /**
     * Create a UserRequest.
     * @param name user name
     * @param gender user gender (male/female)
     * @param email user email
     * @param status account status (active/inactive)
     */
    public UserRequest(String name, String gender, String email, String status) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.status = status;
    }

    /** @return user name */
    public String getName() {
        return name;
    }

    /** @param name set user name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return user gender */
    public String getGender() {
        return gender;
    }

    /** @param gender set user gender */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /** @return user email */
    public String getEmail() {
        return email;
    }

    /** @param email set user email */
    public void setEmail(String email) {
        this.email = email;
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