package com.example.accessingdatamysql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbl_game")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    // mappedBy trỏ tới tên biến games ở trong user.
    @ManyToMany(mappedBy = "games")
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users;


    //  OneToMany with EVENT
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến game ở trong Event.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Event> events;

}
