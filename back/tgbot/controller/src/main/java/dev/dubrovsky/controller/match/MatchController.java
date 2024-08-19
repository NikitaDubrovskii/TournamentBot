package dev.dubrovsky.controller.match;

import dev.dubrovsky.dto.match.MatchDTO;
import dev.dubrovsky.model.match.Match;
import dev.dubrovsky.payload.match.ScoreMatchPayload;
import dev.dubrovsky.service.match.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/match")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/get")
    public List<MatchDTO> getMatches() {
        List<Match> all = matchService.getAll();
        return all.stream()
                .map(MatchDTO::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/score")
    public ResponseEntity<?> scoreMatch(@RequestBody ScoreMatchPayload scoreMatchPayload,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body("Validation errors: " + errors);
        } else {
            matchService.setScore(scoreMatchPayload);
            return ResponseEntity.ok("Match score updated successfully.");
        }
    }

}
