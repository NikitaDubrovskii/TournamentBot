package dev.dubrovsky.controller.playoff;

import dev.dubrovsky.service.playoff.PlayoffMatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/playoff/match")
@AllArgsConstructor
public class PlayoffMatchController {

    private final PlayoffMatchService playoffMatchService;

}
