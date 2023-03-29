package com.uichesoh.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
