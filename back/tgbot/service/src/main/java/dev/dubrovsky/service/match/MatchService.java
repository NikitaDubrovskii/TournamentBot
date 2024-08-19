package dev.dubrovsky.service.match;

import dev.dubrovsky.model.match.Match;
import dev.dubrovsky.model.standing.Standing;
import dev.dubrovsky.payload.match.ScoreMatchPayload;
import dev.dubrovsky.repository.match.MatchRepository;
import dev.dubrovsky.repository.standing.StandingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final StandingRepository standingRepository;

    public List<Match> getAll() {
        return matchRepository.findByTournamentIsActiveTrue();
    }

    public void setScore(ScoreMatchPayload scoreMatchPayload) {
        Optional<Match> byId = matchRepository.findById(scoreMatchPayload.id());

        if (byId.isPresent()) {
            Match match = byId.get();
            match.setTeam1Score(scoreMatchPayload.team1Score());
            match.setTeam2Score(scoreMatchPayload.team2Score());
            matchRepository.save(match);

            updateStandings(match);
        }
    }

    private void updateStandings(Match match) {
        // Получить standings для обеих команд
        Standing team1Standing = standingRepository.findByParticipantAndTournament(match.getTeam1(), match.getTournament())
                .orElseThrow(() -> new IllegalArgumentException("Standing not found for participant: " + match.getTeam1().getId()));

        Standing team2Standing = standingRepository.findByParticipantAndTournament(match.getTeam2(), match.getTournament())
                .orElseThrow(() -> new IllegalArgumentException("Standing not found for participant: " + match.getTeam2().getId()));

        // Обновить количество сыгранных матчей
        team1Standing.setPlayed(team1Standing.getPlayed() + 1);
        team2Standing.setPlayed(team2Standing.getPlayed() + 1);

        // Обновить количество забитых и пропущенных голов
        team1Standing.setGoalsFor(team1Standing.getGoalsFor() + match.getTeam1Score());
        team1Standing.setGoalsAgainst(team1Standing.getGoalsAgainst() + match.getTeam2Score());

        team2Standing.setGoalsFor(team2Standing.getGoalsFor() + match.getTeam2Score());
        team2Standing.setGoalsAgainst(team2Standing.getGoalsAgainst() + match.getTeam1Score());

        // Обновить количество побед, ничьих и поражений, а также очки
        if (match.getTeam1Score() > match.getTeam2Score()) {
            // Победа для team1, поражение для team2
            team1Standing.setWins(team1Standing.getWins() + 1);
            team2Standing.setLosses(team2Standing.getLosses() + 1);
            team1Standing.setPoints(team1Standing.getPoints() + 3);
        } else if (match.getTeam1Score() < match.getTeam2Score()) {
            // Победа для team2, поражение для team1
            team2Standing.setWins(team2Standing.getWins() + 1);
            team1Standing.setLosses(team1Standing.getLosses() + 1);
            team2Standing.setPoints(team2Standing.getPoints() + 3);
        } else {
            // Ничья
            team1Standing.setDraws(team1Standing.getDraws() + 1);
            team2Standing.setDraws(team2Standing.getDraws() + 1);
            team1Standing.setPoints(team1Standing.getPoints() + 1);
            team2Standing.setPoints(team2Standing.getPoints() + 1);
        }

        // Сохранить обновленные standings
        standingRepository.save(team1Standing);
        standingRepository.save(team2Standing);
    }

}
