package dev.dubrovsky.controller.standing;

import dev.dubrovsky.dto.standing.StandingDTO;
import dev.dubrovsky.model.standing.Standing;
import dev.dubrovsky.service.standing.StandingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/standing")
@CrossOrigin(origins = "https://tournamentbot-b7tb.onrender.com")
@AllArgsConstructor
public class StandingController {

    private final StandingService standingService;

    @GetMapping("/get")
    public List<StandingDTO> getStandings() {
        List<Standing> all = standingService.getAll();
        return all.stream()
                .map(StandingDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public StandingDTO getStandingById(@PathVariable Long id) {
        Standing standing = standingService.getById(id);
        return new StandingDTO(standing);
    }

}
