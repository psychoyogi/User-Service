package com.psychoyogi.User.Service.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    private Integer age;

    private String gender;

    private String role;

    @Column(name = "birth_date")
    private String birthDate;

    @Embedded
    private Address address;

    @Column(name = "profile_image")
    private String image;

    private String ssn;

    // Default constructor
    public User() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getSsn() { return ssn; }
    public void setSsn(String ssn) { this.ssn = ssn; }
}

