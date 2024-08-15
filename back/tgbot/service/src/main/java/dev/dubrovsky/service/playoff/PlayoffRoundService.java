package dev.dubrovsky.service.playoff;

import dev.dubrovsky.repository.playoff.PlayoffRoundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayoffRoundService {

    private final PlayoffRoundRepository playoffRoundRepository;

}
