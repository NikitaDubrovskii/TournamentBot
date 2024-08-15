package dev.dubrovsky.service.match;

import dev.dubrovsky.repository.match.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

}
