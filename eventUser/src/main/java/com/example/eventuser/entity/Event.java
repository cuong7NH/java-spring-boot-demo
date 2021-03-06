package com.example.eventuser.entity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tbl_event")
@Entity
@Data
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "user_game_id")
    private UserGame userGame;
}


