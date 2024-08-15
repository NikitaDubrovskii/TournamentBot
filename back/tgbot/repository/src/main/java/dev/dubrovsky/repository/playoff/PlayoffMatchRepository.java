package dev.dubrovsky.repository.playoff;

import dev.dubrovsky.model.playoff.PlayoffMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayoffMatchRepository extends JpaRepository<PlayoffMatch, Integer> {



}
