package com.example.twitterclone.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(unique = true,nullable = false)
    String UserName;
    @Column(unique = true,nullable = false)
    @Email
    String email;
    @Column(nullable = false)
    String fullName;
    @Column(unique = true,nullable = false)
    @Pattern(regexp = "^[0-9]+$")
    @Size(min = 6)
    String phoneNumber;
    @Column(nullable = false)
    @Size(min = 6)
    String password;
    @Column(nullable = false)
    String country;
    @Column(nullable = false)
    LocalDate DOB;

    String profilePicture;
    String Description;

    @Column(nullable = false)
    boolean enabled;
    @Column(nullable = false,updatable = false)
    LocalDateTime createdAt;

}
