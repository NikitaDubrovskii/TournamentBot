package dev.dubrovsky.model.playoff;

import dev.dubrovsky.model.participant.Participant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "playoff_matches")
@Getter
@Setter
@NoArgsConstructor
public class PlayoffMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playoff_round_id", nullable = false)
    private PlayoffRound playoffRound;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Participant team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Participant team2;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    @Column(name = "team1_score", nullable = false)
    private Integer team1Score = 0;

    @Column(name = "team2_score", nullable = false)
    private Integer team2Score = 0;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Participant winner;

}
