package dev.dubrovsky.model.match;

import dev.dubrovsky.model.participant.Participant;
import dev.dubrovsky.model.tournament.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Participant team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Participant team2;

    @Column(name = "team1_score", nullable = false)
    private Integer team1Score = 0;

    @Column(name = "team2_score", nullable = false)
    private Integer team2Score = 0;

}
