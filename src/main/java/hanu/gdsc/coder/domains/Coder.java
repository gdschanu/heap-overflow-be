package hanu.gdsc.coder.domains;

import hanu.gdsc.share.domains.Id;

public class Coder {
    private Id id;
    private String name;
    private int age;
    private Url avatar;
    private Phone phone;
    private String university;
    private String slogan;
    private Gender gender;
    private String address;
    private int rank;

    public Coder(Id id, String name, int age, Url avatar, Phone phone, String university, String slogan, Gender gender, String address, int rank) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.avatar = avatar;
        this.phone = phone;
        this.university = university;
        this.slogan = slogan;
        this.gender = gender;
        this.address = address;
        this.rank = rank;
    }

    public static Coder create(String name,
                               int age,
                               Url avatar,
                               Phone phone,
                               String university,
                               String slogan,
                               Gender gender,
                               String address) {
        return new Coder(
                Id.generateRandom(),
                name,
                age,
                avatar,
                phone,
                university,
                slogan,
                gender,
                address,
                0);
    }

    public Id getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Url getAvatar() {
        return avatar;
    }

    public Phone getPhone() {
        return phone;
    }

    public String getUniversity() {
        return university;
    }

    public String getSlogan() {
        return slogan;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
