package dev.dubrovsky.repository.participant;

import dev.dubrovsky.model.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {



}
