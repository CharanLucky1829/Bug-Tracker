package com.example.bugTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;


    @JsonIgnore  // prevent recursion when serializing bugs
    @OneToMany(mappedBy = "reporter")
    private List<Bug> reportedBugs;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee")
    private List<Bug> assignedBugs;

    public User(){}
    public User(String email,String password){
        this.email=email;
        this.password=password;
    }
}
