package com.example.twitterclone.AppUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true,nullable = false)
    private String userName;
    @Column(unique = true,nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true,nullable = false)
    @Pattern(regexp = "^[0-9]+$")
    @Size(min = 6)
    private String phoneNumber;
    @Column(nullable = false)
    @Size(min = 6)
    private String password;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    @JsonProperty("DOB")
    private LocalDate DOB;

    private String profilePicture;
    private String description;

    @Column(nullable = false)
    private boolean enabled;
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

}
