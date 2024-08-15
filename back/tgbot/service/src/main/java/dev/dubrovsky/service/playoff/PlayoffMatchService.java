package dev.dubrovsky.service.playoff;

import dev.dubrovsky.repository.playoff.PlayoffMatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayoffMatchService {

    private final PlayoffMatchRepository playoffMatchRepository;

}
