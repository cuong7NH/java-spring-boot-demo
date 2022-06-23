package com.example.eventuser.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Table(name = "tbl_user_game")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "play_time")
    private Long playTime;
    @Column(name = "last_event_id")
    private Long lastEventId;
    @Column(nullable = false, name = "play_point")
    private Long playPoint;
    @OneToMany(mappedBy = "userGame", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Event> events;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
