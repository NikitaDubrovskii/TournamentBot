package dev.dubrovsky.payload.tournament;

import dev.dubrovsky.payload.participant.NewParticipantPayload;

import java.util.List;

public record NewTournamentPayload(

        String name,

        String format,

        List<NewParticipantPayload> participants

) {
}
