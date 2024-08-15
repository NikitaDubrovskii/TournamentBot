package dev.dubrovsky.repository.tournament;

import dev.dubrovsky.model.tournament.TournamentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentHistoryRepository extends JpaRepository<TournamentHistory, Integer> {



}
