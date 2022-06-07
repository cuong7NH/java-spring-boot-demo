package com.example.accessingdatamysql.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

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
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Collection<UserGame> userGames;

}
