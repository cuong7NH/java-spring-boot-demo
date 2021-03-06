package com.example.eventuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Table(name = "tbl_user")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(nullable = false)
    @NotEmpty(message = "Thiếu username")
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<UserGame> userGames;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
