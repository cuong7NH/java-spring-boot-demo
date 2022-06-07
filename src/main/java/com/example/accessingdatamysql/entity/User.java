package com.example.accessingdatamysql.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

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
    // ManyToMany With Role
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<UserGame> userGames;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
