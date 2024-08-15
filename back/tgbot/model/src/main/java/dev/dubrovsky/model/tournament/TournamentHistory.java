package dev.dubrovsky.model.tournament;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tournament_history")
@Getter
@Setter
@NoArgsConstructor
public class TournamentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "finished_at", nullable = false)
    private LocalDateTime finishedAt = LocalDateTime.now();

}
