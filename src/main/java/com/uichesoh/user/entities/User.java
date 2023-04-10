package com.uichesoh.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name" ,length = 20)
    private String name;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "info", length = 100)
    private String info;
    @Transient
    private List<Review> reviews = new ArrayList<Review>();
}
