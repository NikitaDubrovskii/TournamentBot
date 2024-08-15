package dev.dubrovsky.model.tournament;

import dev.dubrovsky.model.participant.Participant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tournament_participants")
@Getter
@Setter
@NoArgsConstructor
public class TournamentParticipant {

    @EmbeddedId
    private TournamentParticipantId tournamentParticipantId;

    @ManyToOne
    @MapsId("tournamentId")
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @MapsId("participantId")
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Column(name = "team_name")
    private String teamName;

}
