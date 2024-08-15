package dev.dubrovsky.service.user;

import dev.dubrovsky.repository.user.UserStatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserStatisticsService {

    private final UserStatisticsRepository userStatisticsRepository;

}
