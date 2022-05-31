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
    //  OneToMany with EVENT
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến user ở trong Event.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Event> events;

    // ManyToMany With Game
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng Game
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()

    @JoinTable(name = "tbl_user_game", //Tạo ra một join Table tên là "tbl_user_game"
            joinColumns = @JoinColumn(name = "user_id"),  // TRong đó, khóa ngoại chính là user_id trỏ tới class User
            inverseJoinColumns = @JoinColumn(name = "game_id") //Khóa ngoại thứ 2 trỏ tới Game
    )
    private List<Game> games;

    // ManyToMany With Role
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng Role
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()

    @JoinTable(name = "tbl_user_role", //Tạo ra một join Table tên là "tbl_user_role"
            joinColumns = @JoinColumn(name = "user_id"),  // TRong đó, khóa ngoại chính là user_id trỏ tới class User
            inverseJoinColumns = @JoinColumn(name = "role_id") //Khóa ngoại thứ 2 trỏ tới role
    )
    private List<Role> roles;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
