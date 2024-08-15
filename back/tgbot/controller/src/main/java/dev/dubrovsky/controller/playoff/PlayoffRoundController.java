package dev.dubrovsky.controller.playoff;

import dev.dubrovsky.service.playoff.PlayoffRoundService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/playoff/round")
@AllArgsConstructor
public class PlayoffRoundController {

    public final PlayoffRoundService playoffRoundService;

}
