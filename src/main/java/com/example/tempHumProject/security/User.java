package com.example.tempHumProject.security;

import lombok.Data;

import javax.persistence.*;

@Table(name = "organisation")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    private String login;
    private String password;
    private java.sql.Timestamp created_at;
    private String email;
    private String nickname;
    private boolean is_admin;






}
