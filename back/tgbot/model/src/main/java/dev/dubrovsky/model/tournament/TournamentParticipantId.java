package dev.dubrovsky.model.tournament;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TournamentParticipantId implements Serializable {

    private Long tournamentId;
    private Long participantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipantId that = (TournamentParticipantId) o;
        return Objects.equals(tournamentId, that.tournamentId) &&
                Objects.equals(participantId, that.participantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, participantId);
    }

}
