package hanu.gdsc.coder.Domains;

import java.sql.Blob;

import hanu.gdsc.share.domains.Id;

public class Coder {
    private Id id;
    private String username;
    private Blob avatar;
    private String email;
    private String phone;
    private String university;
    private String slogan;
    private Gender gender;
    private String address;
    public Coder(Id id, String username, Blob avatar, String email, String phone, String university, String slogan,
            Gender gender, String address) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.university = university;
        this.slogan = slogan;
        this.gender = gender;
        this.address = address;
    }
    public Id getId() {
        return id;
    }
    public void setId(Id id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Blob getAvatar() {
        return avatar;
    }
    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    public String getSlogan() {
        return slogan;
    }
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    
}
