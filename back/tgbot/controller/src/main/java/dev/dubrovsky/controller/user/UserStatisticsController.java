package dev.dubrovsky.controller.user;

import dev.dubrovsky.service.user.UserStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/statistics")
@AllArgsConstructor
public class UserStatisticsController {

    private final UserStatisticsService userStatisticsService;

}
