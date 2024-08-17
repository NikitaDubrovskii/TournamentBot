package dev.dubrovsky.controller.standing;

import dev.dubrovsky.model.standing.Standing;
import dev.dubrovsky.service.standing.StandingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/standing")
@AllArgsConstructor
public class StandingController {

    private final StandingService standingService;

    @GetMapping("/get")
    public List<Standing> getStandings() {
        return standingService.getAll();
    }

}
