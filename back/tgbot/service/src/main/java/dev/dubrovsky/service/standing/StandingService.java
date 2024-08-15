package dev.dubrovsky.service.standing;

import dev.dubrovsky.repository.standing.StandingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StandingService {

    public final StandingRepository standingRepository;

}
