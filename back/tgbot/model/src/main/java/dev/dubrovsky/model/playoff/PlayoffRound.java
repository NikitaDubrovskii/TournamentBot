package dev.dubrovsky.model.playoff;

import dev.dubrovsky.model.tournament.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "playoff_rounds")
@Getter
@Setter
@NoArgsConstructor
public class PlayoffRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @Column(nullable = false)
    private String roundName;

    @OneToMany(mappedBy = "playoffRound", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlayoffMatch> matches;

}
